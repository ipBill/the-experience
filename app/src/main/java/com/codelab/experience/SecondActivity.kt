package com.codelab.experience

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codelab.experience.data.Food
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_second)
		val food = intent?.getParcelableExtra("FOOD") ?: Food()
		Glide.with(this)
				.load(food.imageUrl)
				.into(foodImage)
	}
}
