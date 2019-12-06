package com.chen.baselibrary

import android.content.Context

class ContextProvider {

    companion object{
        val mInstance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            ContextProvider()
        }
    }


    fun getContext() : Context{
        return AppContextProvider.mContext
    }
}