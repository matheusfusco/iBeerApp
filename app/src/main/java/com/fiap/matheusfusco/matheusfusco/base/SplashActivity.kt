package com.fiap.matheusfusco.matheusfusco.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.auth.login.LoginActivity
import com.iamhabib.easy_preference.EasyPreference
import android.R.attr.defaultValue
import com.fiap.matheusfusco.matheusfusco.webservice.Constants


class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val logged = EasyPreference.with(this)
                    .getBoolean(Constants.LOGGED,  false)
            var intent: Intent
            intent = if (logged){
                Intent(applicationContext, BottomNavigationActivity::class.java)
            } else {
                Intent(applicationContext, LoginActivity::class.java)
            }

            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        EasyPreference.with(this)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
