package com.chen.fakevibrato.skin

import android.app.Activity
import android.content.Context
import com.chen.fakevibrato.skin.attr.SkinView


class SkinManager {
    private lateinit var mContext: Context
    private lateinit var mSkinResource : SkinResource
    private val mSkinViews = HashMap<Activity, List<SkinView>>()
    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SkinManager()
        }
    }

    fun init(context: Context) {
        mContext = context.applicationContext
    }

    /**
     * 加载皮肤
     */
    fun loadSkin(skinPath: String) : Int{
        // 校验签名  增量更新再说

        // 最好把他复制走，用户不能轻易删除的地方  cache目录下面

        // 初始化资源管理
        mSkinResource = SkinResource(mContext, skinPath)

        //改变皮肤
        var keys = mSkinViews.keys
        for (key in keys){
            var skinViews = mSkinViews[key]
            if (skinViews != null) {
                for (skinView in skinViews) {
                    skinView.skin()
                }
            }
        }
        return 0
    }

    /**
     * 恢复默认
     */
    fun restoreDefault() : Int{
        return 0
    }

    /**
     * 通过activity获取skinView
     */
    fun getSkinViews(activity: Activity) : List<SkinView>?{
        return mSkinViews[activity]
    }

    /**
     * 注册
     */
    fun register (activity: Activity, skinviews : List<SkinView>){
        mSkinViews[activity] = skinviews
    }

    /**
     * 获取当前皮肤资源
     */
    fun getSkinResource() : SkinResource{
        return mSkinResource
    }

}