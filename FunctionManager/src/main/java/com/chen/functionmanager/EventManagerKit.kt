package com.chen.functionmanager

import android.util.Log
import androidx.lifecycle.LifecycleOwner

/**
 *  Created by zzw on 2019-07-22
 *  Des: EventManager的一个全局管理类，一般使用这个
 */


val mEventManager: EventManager by lazy {
    EventManager("EventManagerKit").apply {
        addEventManager()
    }
}

fun Any.postAction(action: String = DEFAULT_ACTION, obj: Any? = null) {
    mEventManager.post(action, obj, this::class.java.name)
}

fun LifecycleOwner.bindAction(actions: Array<String>, callBack: (action: String, obj: Any?) -> Unit = { _, _ -> }) {
    mEventManager.bind(this, callBack = callBack, actions = actions)
}

inline fun <reified T> LifecycleOwner.bindAction(action: String, crossinline callBack: (data: T?) -> Unit = { _ -> }) {
    mEventManager.bind(this, callBack = { a, obj ->
        if (a == action) {
            if (obj is T?) {
                callBack(obj as T?)
            } else {
                Log.e("EventManagerKit", "post action obj class not is bind obj class,post obj class is ${obj?.javaClass?.simpleName},bind action obj is ${T::class.java.simpleName}")
            }
        }
    }, actions = arrayOf(action))
}


fun clearAllAction() {
    mEventManager.clearAll()
}






