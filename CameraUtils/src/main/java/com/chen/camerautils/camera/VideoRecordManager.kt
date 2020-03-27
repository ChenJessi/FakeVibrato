package com.caiyan.cloud.livebodysdk


import android.content.Context
import android.hardware.Camera
import android.media.MediaRecorder
import android.os.Environment
import android.view.Surface

class VideoRecordManager(var mContext : Context, private val camera: Camera?, var surface : Surface ) {
    private var mRecorder: MediaRecorder? = null
    /**
     * 开始录制
     */
    fun start() {
        if (mRecorder == null) {
            mRecorder = MediaRecorder()
        }
        camera?.unlock()
        mRecorder?.setCamera(camera)
        // 这两项需要放在setOutputFormat之前
//        mRecorder?.setAudioSource(MediaRecorder.AudioSource.CAMCORDER)//声音源
        mRecorder?.setVideoSource(MediaRecorder.VideoSource.CAMERA)//视频源

        // 设置输出格式
        mRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)

        // 这两项需要放在setOutputFormat之后
//        mRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)//声音编码格式
        mRecorder?.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP)//视频编码格式
        //设置视频的长宽
        mRecorder?.setVideoSize(640, 480)
        mRecorder?.setVideoFrameRate(30)
        //设置比特率（比特率越高质量越高同样也越大）
        mRecorder?.setVideoEncodingBitRate(3 * 1024 * 1024)
        //这里是调整旋转角度（前置和后置的角度不一样）
        mRecorder?.setOrientationHint(270)
        //设置记录会话的最大持续时间（毫秒）
        mRecorder?.setMaxDuration(10 * 1000)
        //设置预览对象
        mRecorder?.setPreviewDisplay(surface)
        //设置输出的文件路径
//        mRecorder?.setOutputFile(mContext.getCacheDir().getAbsolutePath()+ "test.mp4")
        mRecorder?.setOutputFile(Environment.getExternalStorageDirectory().absolutePath+ "/test.mp4")
        //预处理
        mRecorder?.prepare()
        //开始录制
        mRecorder?.start()
    }
    /**
     * 停止录制
     */
    fun stop() {
        mRecorder?.stop()
        mRecorder?.reset()
        mRecorder?.release()
        mRecorder = null
    }
}