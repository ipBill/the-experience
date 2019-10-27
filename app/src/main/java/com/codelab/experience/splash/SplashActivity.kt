package com.codelab.experience.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codelab.experience.R
import com.codelab.experience.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)
		startButton.setOnClickListener {
			val intent = Intent(this, MainActivity::class.java)
			startActivity(intent)
			finish()
		}
	}
}
