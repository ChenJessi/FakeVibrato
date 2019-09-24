package com.chen.fakevibrato.tangram

import androidx.annotation.NonNull
import com.tmall.wireless.tangram.MVHelper
import com.tmall.wireless.tangram.structure.BaseCell
import com.tmall.wireless.tangram.support.ExposureSupport
import org.json.JSONException
import org.json.JSONObject

/**
 * 自定义model
 */
class CustomCell : BaseCell<CustomCellView>() {
    private var imageUrl: String? = null
    private var text: String? = null

    override fun parseWith(@NonNull data: JSONObject, @NonNull resolver: MVHelper) {
        try {
            if (data.has("imageUrl")) {
                imageUrl = data.getString("imageUrl")
            }
            if (data.has("text")) {
                text = data.getString("text")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    override fun bindView(@NonNull view: CustomCellView) {
        if (pos % 2 == 0) {
            view.setBackgroundColor(-0xff01)
        } else {
            view.setBackgroundColor(-0x100)
        }
        view.setImageUrl(imageUrl!!)
        view.setText(view.javaClass.simpleName + pos + ": " + text)
        view.setOnClickListener(this)
        if (serviceManager != null) {
            val exposureSupport = serviceManager!!.getService(ExposureSupport::class.java)
            if (exposureSupport != null) {
                exposureSupport!!.onTrace(view, this, type)
            }
        }
    }
}