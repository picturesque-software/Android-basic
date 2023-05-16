package com.byted.camp.video

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import java.io.File
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

typealias LumaListener = (luma: Double) -> Unit

class TakeVideoActivity : AppCompatActivity() {
    private lateinit var cameraExecutor: ExecutorService

    private var cameraProvider: ProcessCameraProvider? = null
    private var preview: Preview? = null
    var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    var camera: Camera? = null
    private var imageCapture: ImageCapture? = null
    private var videoCapture: VideoCapture? = null

    lateinit var viewFinder: CameraXPreviewView
    private lateinit var btnSwitch: Button
    private lateinit var btnStartVideo: Button
    lateinit var focusView: FocusImageView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_video)

        viewFinder = findViewById<CameraXPreviewView>(R.id.viewFinder)
        btnSwitch = findViewById(R.id.btnChange)
        btnStartVideo = findViewById(R.id.btnStartVideo)
        focusView = findViewById(R.id.focusView)

        if (allPermissionsGranted()) {
            startCamera()
        }
        else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        btnStartVideo.setOnClickListener {
            btnStartVideo.setBackgroundResource(R.drawable.style_shooting)
            takeVideo()
        }
        btnSwitch.setOnClickListener {
            if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            }
            else {
                cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            }
            startCamera()
        }
    }

    @SuppressLint("RestrictedApi")
    private fun startCamera() {
        cameraExecutor = Executors.newSingleThreadExecutor()
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            cameraProvider = cameraProviderFuture.get()

            preview = Preview.Builder().build().also {
                it.setSurfaceProvider(viewFinder.createSurfaceProvider())
            }

            imageCapture = ImageCapture.Builder().build()

            ImageAnalysis.Builder().build().also {
                it.setAnalyzer(cameraExecutor, LuminosityAnalyzer { luma ->
                })
            }

            videoCapture = VideoCapture.Builder().setTargetAspectRatio(AspectRatio.RATIO_16_9).build()

            try {
                cameraProvider?.unbindAll()
                camera = cameraProvider?.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture,
                    videoCapture
                )
            } catch (exc: Exception) {
            }
            init()
        }, ContextCompat.getMainExecutor(this))
    }


    private fun init() {
        val zoomState = camera!!.cameraInfo.zoomState
        viewFinder.setCustomTouchListener(object : CameraXPreviewView.CustomTouchListener {
            override fun zoom(delta: Float) {
                zoomState.value?.let {
                    val currentZoomRatio = it.zoomRatio
                    camera!!.cameraControl.setZoomRatio(currentZoomRatio * delta)
                }
            }

            override fun click(x: Float, y: Float) {
                if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                    val factory = viewFinder.createMeteringPointFactory(cameraSelector)
                    val point = factory.createPoint(x, y)
                    val action = FocusMeteringAction.Builder(point, FocusMeteringAction.FLAG_AF)
                        .setAutoCancelDuration(3, TimeUnit.SECONDS).build()
                    focusView.startFocus(Point(x.toInt(), y.toInt()))
                    val future: ListenableFuture<*> =
                        camera!!.cameraControl.startFocusAndMetering(action)
                    future.addListener(Runnable {
                        try {
                            val result = future.get() as FocusMeteringResult
                            if (result.isFocusSuccessful) {
                                focusView.onFocusSuccess()
                            }
                            else {
                                focusView.onFocusFailed()
                            }
                        } catch (e: Exception) {
                        }
                    }, cameraExecutor)
                }
            }

            override fun doubleClick(x: Float, y: Float) {
                zoomState.value?.let {
                    val currentZoomRatio = it.zoomRatio
                    if (currentZoomRatio > it.minZoomRatio) {
                        camera!!.cameraControl.setLinearZoom(0f)
                    }
                    else {
                        camera!!.cameraControl.setLinearZoom(0.5f)
                    }
                }
            }

            override fun longClick(x: Float, y: Float) {}
        })
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            }
            else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    @SuppressLint("RestrictedApi", "ClickableViewAccessibility")
    private fun takeVideo() {

        val path = getExternalFilesDir(Environment.DIRECTORY_MOVIES)
        val file = File(
            path,
            SimpleDateFormat(
                FILENAME_FORMAT,
                Locale.CHINA
            ).format(System.currentTimeMillis()) + ".mp4"
        )

        videoCapture?.startRecording(
            file,
            Executors.newSingleThreadExecutor(),
            object : VideoCapture.OnVideoSavedCallback {
                override fun onVideoSaved(@NonNull file: File) {
                    val intent = Intent(this@TakeVideoActivity, MainActivity::class.java)
                    intent.data = Uri.parse(file.path)
                    startActivity(intent)
                }

                override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {
                }


            })

        btnStartVideo.setOnClickListener {
            btnStartVideo.setBackgroundResource(R.drawable.style_shoot)
            videoCapture?.stopRecording()
            preview?.clear()
            btnStartVideo.setOnClickListener {
                btnStartVideo.setBackgroundResource(R.drawable.style_shooting)
                takeVideo()
            }
            Toast.makeText(this, file.path, Toast.LENGTH_SHORT).show()

        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private class LuminosityAnalyzer(private val listener: LumaListener) : ImageAnalysis.Analyzer {

        private fun ByteBuffer.toByteArray(): ByteArray {
            rewind()
            val data = ByteArray(remaining())
            get(data)
            return data
        }

        override fun analyze(image: ImageProxy) {

            val buffer = image.planes[0].buffer
            val data = buffer.toByteArray()
            val pixels = data.map { it.toInt() and 0xFF }
            val luma = pixels.average()
            listener(luma)
            image.close()
        }
    }

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO
        )
    }
}