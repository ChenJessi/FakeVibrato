package com.chen.fakevibrato.skin.support

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import com.chen.fakevibrato.skin.attr.SkinAttr
import com.chen.fakevibrato.skin.attr.SkinType


/**
 *
 */
object SkinAttrSupport {
    private val TAG = this.javaClass.simpleName

    /**
     * 获取SkinAttr的属性
     */
    fun getSkinAttrs(context: Context, attrs : AttributeSet) : ArrayList<SkinAttr>{
        // background   src  textColor
        val skinAttrs = ArrayList<SkinAttr>()
        val attrLength = attrs.attributeCount

        for (index in 0 until attrLength) {
            //获取名称，值
            var attrName = attrs.getAttributeName(index)
            var attrValue = attrs.getAttributeValue(index)
            //
            var skinType = getSkinType(attrName)
            if (skinType!= null){
                // 资源名称  目前只有attrValue  @int 类型
                var resName = getResName(context, attrValue)
                if (TextUtils.isEmpty(resName)){
                    continue
                }
                if (resName != null){
                    var skinAttr = SkinAttr(resName, skinType)
                    skinAttrs.add(skinAttr)
                }
            }
        }
        return skinAttrs
    }

    /**
     * 获取资源名称
     */
    fun getResName(context: Context, attrValue : String) : String? {
        var value = attrValue
        if (attrValue.startsWith("@")){
            value = value.substring(1)
            var resId = value.toInt()
            return  context.resources.getResourceEntryName(resId)
        }
        return null
    }
    /**
     * 通过名称获取skintype
     */
    fun getSkinType(attrName : String) : SkinType?{
        var skinTypes = SkinType.values()
        for (skinType in skinTypes){
            if (skinType.getResName().equals(attrName)){
                return skinType
            }
        }
        return null
    }
}