package com.example.e_commerceapp

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun initSplashScreen() {
        // التحقق مما إذا كان إصدار نظام التشغيل يدعم Android 12 أو أحدث
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // تثبيت شاشة البداية
            installSplashScreen()
            // Add a callback that's called when the splash screen is animating to the
            // app content.
            // إضافة استماع لحدث الخروج من شاشة البداية عندما تبدأ بالتحول إلى محتوى التطبيق
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                // Create your custom animation.
                // إنشاء الرسم المتحرك الخاص بك
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView, View.TRANSLATION_Y, 0f, -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 1000L

                // Call SplashScreenView.remove at the end of your custom animation.
                // استدعاء الدالة SplashScreenView.remove عند انتهاء الرسم المتحرك الخاص بك
                slideUp.doOnEnd { splashScreenView.remove() }

                // Run your animation.
                // تشغيل الرسم المتحرك
                slideUp.start()
            }
        } else {
            setTheme(R.style.Theme_ECommerceApp)
        }
    }


}