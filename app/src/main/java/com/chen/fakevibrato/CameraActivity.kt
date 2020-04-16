package com.chen.fakevibrato

import android.Manifest
import androidx.core.app.ActivityCompat
import com.chen.baselibrary.base.DBasePresenter
import com.chen.camerautils.camera.*
import com.chen.fakevibrato.base.DBaseSupportActivity
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : DBaseSupportActivity<DBasePresenter<*>>() {
    private val PERMISSIONS_REQUEST_CAMERA = 800
    private val permissionCallback = PermissionCallback {
        ActivityCompat.requestPermissions(
                this@CameraActivity,
                arrayOf(Manifest.permission.CAMERA),
                PERMISSIONS_REQUEST_CAMERA
        )
        false
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_camera
    }

    override fun initPresenter(): DBasePresenter<*>? {
        return null
    }

    override fun initView() {
        cameraView.getCameraControl().setPermissionCallback(permissionCallback)
    }

    override fun initListener() {

    }

    override fun initData() {
        cameraView.setMaskType(MaskView.MASK_TYPE_ID_CARD_BACK, this)
    }

    override fun onResume() {
        super.onResume()
        cameraView.start()
    }

    override fun onPause() {
        super.onPause()
        cameraView.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        CameraThreadPool.cancelAutoFocusTimer()
    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        setOrientation(newConfig)
//    }
//
//    private fun setOrientation(newConfig: Configuration) {
//        val rotation = windowManager.defaultDisplay.rotation
//        var cameraViewOrientation = CameraView.ORIENTATION_PORTRAIT
//        when (newConfig.orientation) {
//            Configuration.ORIENTATION_PORTRAIT -> {
//                cameraViewOrientation = CameraView.ORIENTATION_PORTRAIT
//            }
//            Configuration.ORIENTATION_LANDSCAPE -> {
//                if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_90) {
//                    cameraViewOrientation = CameraView.ORIENTATION_HORIZONTAL
//                } else {
//                    cameraViewOrientation = CameraView.ORIENTATION_INVERT
//                }
//            }
//            else -> {
//                cameraView.setOrientation(CameraView.ORIENTATION_PORTRAIT)
//            }
//        }
//        cameraView.setOrientation(cameraViewOrientation)
//    }

}