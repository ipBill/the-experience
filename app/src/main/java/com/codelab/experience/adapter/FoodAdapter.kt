package com.codelab.experience.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codelab.experience.R
import com.codelab.experience.data.Food
import kotlinx.android.synthetic.main.item_food.view.*

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

	private var foods: List<Food> = listOf()
	var onItemClick: ((food: Food) -> Unit)? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
		val itemView = LayoutInflater.from(parent.context)
				.inflate(R.layout.item_food, parent, false)
		return FoodViewHolder(itemView = itemView)
	}

	override fun getItemCount(): Int {
		return foods.size
	}

	override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
		holder.bind(food = foods[position])
		holder.itemView.setOnClickListener {
			onItemClick?.invoke(foods[position])
		}
	}

	fun updateFoods(foods: List<Food>) {
		this.foods = foods
		notifyDataSetChanged()
	}

	class FoodViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(food: Food) {
			itemView.tvFoodName.text = food.foodName
			itemView.tvCalories.text = food.calories.toString()
			Glide.with(itemView.context)
					.load(food.imageUrl)
					.into(itemView.foodImage)
		}
	}
}

