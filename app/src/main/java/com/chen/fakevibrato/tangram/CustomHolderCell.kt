package com.chen.fakevibrato.tangram

import android.widget.TextView
import androidx.annotation.NonNull
import com.tmall.wireless.tangram.structure.BaseCell
import com.tmall.wireless.tangram.support.ExposureSupport
import java.util.*

class CustomHolderCell : BaseCell<TextView>() {
    override fun bindView(@NonNull view: TextView) {
        if (pos % 2 == 0) {
            view.setBackgroundColor(-0xfff001)
        } else {
            view.setBackgroundColor(-0x1000)
        }
        view.text = String.format(Locale.CHINA, "%s%d: %s", javaClass.simpleName, pos,
                optParam("text"))
        view.setOnClickListener(this)
        if (serviceManager != null) {
            val exposureSupport = serviceManager!!.getService(ExposureSupport::class.java)
            if (exposureSupport != null) {
                exposureSupport!!.onTrace(view, this, type)
            }
        }
    }
}