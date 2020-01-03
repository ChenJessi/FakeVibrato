package com.chen.fakevibrato.skin

import android.content.Context

class SkinManager {
    private lateinit var mContext: Context
    private lateinit var mSkinResource : SkinResource
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
    fun loadSkin(skinPath: String) {
        // 校验签名  增量更新再说

        // 最好把他复制走，用户不能轻易删除的地方  cache目录下面

        // 初始化资源管理
        mSkinResource = SkinResource(mContext, skinPath)

        //改变皮肤
//        var keys = ms
    }

}