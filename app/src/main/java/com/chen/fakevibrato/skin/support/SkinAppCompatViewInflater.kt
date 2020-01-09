package com.chen.fakevibrato.skin.support

import android.content.Context
import android.content.ContextWrapper
import android.util.ArrayMap
import android.util.AttributeSet
import android.util.Log
import android.view.InflateException
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.*
import androidx.core.view.ViewCompat
import com.chen.fakevibrato.R
import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * This class is responsible for manually inflating our tinted widgets which are used on devices
 * running [KITKAT][android.os.Build.VERSION_CODES.KITKAT] or below. As such, this class
 * should only be used when running on those devices.
 *
 * This class two main responsibilities: the first is to 'inject' our tinted views in place of
 * the framework versions in layout inflation; the second is backport the `android:theme`
 * functionality for any inflated widgets. This include theme inheritance from it's parent.
 */
class SkinAppCompatViewInflater {

    private val mConstructorArgs = arrayOfNulls<Any>(2)

    fun createView(parent: View?, name: String, @NonNull context: Context,
                   @NonNull attrs: AttributeSet, inheritContext: Boolean,
                   readAndroidTheme: Boolean, readAppTheme: Boolean): View? {
        var context = context
        val originalContext = context

        // We can emulate Lollipop's android:theme attribute propagating down the view hierarchy
        // by using the parent's context
        if (inheritContext && parent != null) {
            context = parent!!.getContext()
        }
        if (readAndroidTheme || readAppTheme) {
            // We then apply the theme on the context, if specified
            context = themifyContext(context, attrs, readAndroidTheme, readAppTheme)
        }

        var view: View? = null

        // We need to 'inject' our tint aware Views in place of the standard framework versions
        when (name) {
            "TextView" -> view = AppCompatTextView(context, attrs)
            "ImageView" -> view = AppCompatImageView(context, attrs)
            "Button" -> view = AppCompatButton(context, attrs)
            "EditText" -> view = AppCompatEditText(context, attrs)
            "Spinner" -> view = AppCompatSpinner(context, attrs)
            "ImageButton" -> view = AppCompatImageButton(context, attrs)
            "CheckBox" -> view = AppCompatCheckBox(context, attrs)
            "RadioButton" -> view = AppCompatRadioButton(context, attrs)
            "CheckedTextView" -> view = AppCompatCheckedTextView(context, attrs)
            "AutoCompleteTextView" -> view = AppCompatAutoCompleteTextView(context, attrs)
            "MultiAutoCompleteTextView" -> view = AppCompatMultiAutoCompleteTextView(context, attrs)
            "RatingBar" -> view = AppCompatRatingBar(context, attrs)
            "SeekBar" -> view = AppCompatSeekBar(context, attrs)
        }

        if (view == null) {
            // If the original context does not equal our themed context, then we need to manually
            // inflate it using the name so that android:theme takes effect.
            view = createViewFromTag(context, name, attrs)
        }

        if (view != null) {
            // If we have created a view, check it's android:onClick
            checkOnClickListener(view!!, attrs)
        }

        return view
    }

    private fun createViewFromTag(context: Context, name: String, attrs: AttributeSet): View? {
        var name = name
        if (name == "view") {
            name = attrs.getAttributeValue(null, "class")
        }

        try {
            mConstructorArgs[0] = context
            mConstructorArgs[1] = attrs

            return if (-1 == name.indexOf('.')) {
                // try the android.widget prefix first...
                createView(context, name, "android.widget.")
            } else {
                createView(context, name, null)
            }
        } catch (e: Exception) {
            // We do not want to catch these, lets return null and let the actual LayoutInflater
            // try
            return null
        } finally {
            // Don't retain references on context.
            mConstructorArgs[0] = null
            mConstructorArgs[1] = null
        }
    }

    /**
     * android:onClick doesn't handle views with a ContextWrapper context. This method
     * backports new framework functionality to traverse the Context wrappers to find a
     * suitable target.
     */
    private fun checkOnClickListener(view: View, attrs: AttributeSet) {
        val context = view.getContext()

        if (!ViewCompat.hasOnClickListeners(view) || context !is ContextWrapper) {
            // Skip our compat functionality if: the view doesn't have an onClickListener,
            // or the Context isn't a ContextWrapper
            return
        }

        val a = context.obtainStyledAttributes(attrs, sOnClickAttrs)
        val handlerName = a.getString(0)
        if (handlerName != null) {
            view.setOnClickListener(DeclaredOnClickListener(view, handlerName))
        }
        a.recycle()
    }

    @Throws(ClassNotFoundException::class, InflateException::class)
    private fun createView(context: Context, name: String, prefix: String?): View? {
        var constructor = sConstructorMap.get(name)

        try {
            if (constructor == null) {
                // Class not found in the cache, see if it's real, and try to add it
                val clazz = context.getClassLoader().loadClass(
                        if (prefix != null) prefix!! + name else name).asSubclass(View::class.java)

                constructor = clazz.getConstructor(*sConstructorSignature)
                sConstructorMap.put(name, constructor!!)
            }
            constructor.setAccessible(true)
            var t = constructor.newInstance(mConstructorArgs)
            return return if(t is View){
                t
            }else{
                null
            }
        } catch (e: Exception) {
            // We do not want to catch these, lets return null and let the actual LayoutInflater
            // try
            return null
        }

    }

    /**
     * An implementation of OnClickListener that attempts to lazily load a
     * named click handling method from a parent or ancestor context.
     */
    private class DeclaredOnClickListener(@param:NonNull private val mHostView: View, @param:NonNull private val mMethodName: String) : View.OnClickListener {

        private var mResolvedMethod: Method? = null
        private var mResolvedContext: Context? = null

        override fun onClick(@NonNull v: View) {
            if (mResolvedMethod == null) {
                resolveMethod(mHostView.getContext(), mMethodName)
            }

            try {
                mResolvedMethod!!.invoke(mResolvedContext, v)
            } catch (e: IllegalAccessException) {
                throw IllegalStateException(
                        "Could not execute non-public method for android:onClick", e)
            } catch (e: InvocationTargetException) {
                throw IllegalStateException(
                        "Could not execute method for android:onClick", e)
            }

        }

        @NonNull
        private fun resolveMethod(@Nullable context: Context?, @NonNull name: String) {
            var context = context
            while (context != null) {
                try {
                    if (!context.isRestricted) {
                        val method = context.javaClass.getMethod(mMethodName, View::class.java)
                        mResolvedMethod = method
                        mResolvedContext = context
                        return
                    }
                } catch (e: NoSuchMethodException) {
                    // Failed to find method, keep searching up the hierarchy.
                }

                if (context is ContextWrapper) {
                    context = (context as ContextWrapper).getBaseContext()
                } else {
                    // Can't search up the hierarchy, null out and fail.
                    context = null
                }
            }

            val id = mHostView.getId()
            val idText = if (id == View.NO_ID)
                ""
            else
                " with id '"+ mHostView.getContext().getResources().getResourceEntryName(id) + "'"
            throw IllegalStateException(("Could not find method " + mMethodName
                    + "(View) in a parent or ancestor Context for android:onClick "
                    + "attribute defined on view " + mHostView.javaClass + idText))
        }
    }

    companion object {

        private val sConstructorSignature = arrayOf<Class<*>>(Context::class.java, AttributeSet::class.java)
        private val sOnClickAttrs = intArrayOf(android.R.attr.onClick)

        private val LOG_TAG = "AppCompatViewInflater"

        private val  sConstructorMap : MutableMap<String , Constructor<*>> = HashMap()


        /**
         * Allows us to emulate the `android:theme` attribute for devices before L.
         */
        private fun themifyContext(context: Context, attrs: AttributeSet,
                                   useAndroidTheme: Boolean, useAppTheme: Boolean): Context {
            var context = context
            val a = context.obtainStyledAttributes(attrs, R.styleable.View, 0, 0)
            var themeId = 0

            if (useAndroidTheme) {
                // First try reading android:theme if enabled
                themeId = a.getResourceId(R.styleable.View_android_theme, 0)
            }
            if (useAppTheme && themeId == 0) {
                // ...if that didn't work, try reading app:theme (for legacy reasons) if enabled
                themeId = a.getResourceId(R.styleable.View_theme, 0)

                if (themeId != 0) {
                    Log.i(LOG_TAG, ("app:theme is now deprecated. " + "Please move to using android:theme instead."))
                }
            }
            a.recycle()

            if ((themeId != 0 && ((context !is ContextThemeWrapper || context.getThemeResId() !== themeId)))) {
                // If the context isn't a ContextThemeWrapper, or it is but does not have
                // the same theme as we need, wrap it in a new wrapper
                context = ContextThemeWrapper(context, themeId)
            }
            return context
        }
    }
}