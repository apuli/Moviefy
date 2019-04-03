package com.pvbapps.moviefy.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.pvbapps.moviefy.R
import com.pvbapps.moviefy.ui.main.MainActivity
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : DaggerActivity(), SplashContract.View {

    @Inject
    lateinit var splashPresenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initUI()
    }

    private fun initUI() {
        // Fullscreen mode
        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE or View.SYSTEM_UI_FLAG_IMMERSIVE
            or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                // The system bars are visible.
            } else {
                // The system bars are NOT visible.
            }
        }

        splash_animation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animator: Animator?) {
            }

            override fun onAnimationCancel(animator: Animator?) {
            }

            override fun onAnimationStart(animator: Animator?) {
            }

            override fun onAnimationEnd(animator: Animator?) {
                splashPresenter.onLogoAnimationEnd()
            }
        })

        // Enable ConstraintLayout to fit system windows
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun showMoviesScreen() {
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        startActivity(MainActivity.newInstance(this))
    }
}