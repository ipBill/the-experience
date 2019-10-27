package com.codelab.experience

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_food.view.*

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

	private var foods: List<Food> = listOf()

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
	}

	fun updateFoods(foods: List<Food>) {
		this.foods = foods
		notifyDataSetChanged()
	}

	class FoodViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(food: Food) {
			itemView.tvFoodName.text = food.foodName
		}
	}
}

