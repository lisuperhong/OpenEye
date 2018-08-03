package com.lisuperhong.openeye.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    private val PERMISSION_REQUEST = 1
    private var scaleAnimation: ScaleAnimation? = null

    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        val fontType: Typeface = Typeface.createFromAsset(assets, "fonts/Lobster-1.4.otf")
        englishIntroTv.typeface = fontType
    }

    override fun initData(savedInstanceState: Bundle?) {
        scaleAnimation = ScaleAnimation(
            1.0f,
            1.2f,
            1.0f,
            1.2f,
            ScaleAnimation.RELATIVE_TO_SELF,
            0.5f,
            ScaleAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        scaleAnimation?.duration = 2000
        scaleAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(MainActivity::class.java)
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })

        checkPermission()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE
                ),
                PERMISSION_REQUEST
            )
        } else {
            background.startAnimation(scaleAnimation)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "初始化完成", Toast.LENGTH_SHORT).show()
                    background.startAnimation(scaleAnimation)
                } else {
                    Toast.makeText(this, "拒绝权限请求", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            else -> {

            }
        }
    }
}
