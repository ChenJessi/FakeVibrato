package com.chen.fakevibrato.tangram

import android.view.View
import androidx.annotation.NonNull
import com.chen.fakevibrato.utils.MyLog
import com.tmall.wireless.tangram.dataparser.concrete.Card
import com.tmall.wireless.tangram.structure.BaseCell
import com.tmall.wireless.tangram.support.ExposureSupport

/**
 * 自定义曝光事件
 */
class CustomExposureSupport : ExposureSupport() {

    init {
        setOptimizedMode(true)
    }

    override fun onExposure(@NonNull card: Card, offset: Int, position: Int) {
        MyLog.d( "onExposure: card=" + card.javaClass.simpleName + ", offset=" + offset + ", position=" + position)
    }

    override fun defaultExposureCell(@NonNull targetView: View, @NonNull cell: BaseCell<View>, type: Int) {
        MyLog.d( "defaultExposureCell: targetView=" + targetView.javaClass.simpleName + ", pos=" + cell.pos + ", type=" + type)
    }

    override fun defaultTrace(@NonNull targetView: View, @NonNull cell: BaseCell<View>, type: Int) {
        MyLog.d("defaultTrace: targetView=" + targetView.javaClass.simpleName + ", pos=" + cell.pos + ", type=" + type)
    }

}