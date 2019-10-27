package com.codelab.experience

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codelab.experience.data.Food
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_second)
		initView()
	}

	@SuppressLint("SetTextI18n")
	private fun initView() {
		val food = intent?.getParcelableExtra("FOOD") ?: Food()
		Glide.with(this)
				.load(food.imageUrl)
				.into(foodImage)

		tvFoodName.text = food.foodName
		tvCalories.text = food.calories.toString() + " " + getString(R.string.calories)

		exitButton.setOnClickListener {
			finish()
		}
		val percentCalories = calculateCalories(food.calories)
		progressView.progress = percentCalories
		if (percentCalories <= 100) {
			percentText.text = "$percentCalories%"
		} else {
			percentText.text = "100%"
		}
	}

	private fun calculateCalories(foodCalories: Int): Int {
		return (foodCalories * 100) / 3000
	}
}
