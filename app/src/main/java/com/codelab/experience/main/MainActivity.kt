package com.codelab.experience.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.codelab.experience.R
import com.codelab.experience.SecondActivity
import com.codelab.experience.adapter.FoodAdapter
import com.codelab.experience.data.Food
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private lateinit var foodAdapter: FoodAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		initRecyclerView()
		initFirebaseRealTimeDatabase()
	}

	private fun initRecyclerView() {
		foodAdapter = FoodAdapter()
		recyclerViewFood.adapter = foodAdapter
		foodAdapter.onItemClick = { foodSelected: Food ->
			val intent = Intent(this, SecondActivity::class.java)
			intent.putExtra("FOOD", foodSelected)
			startActivity(intent)
		}
	}

	private fun initFirebaseRealTimeDatabase() {
		val database = FirebaseDatabase.getInstance("https://the-experience-c4c15.firebaseio.com/")
		val foodsReference = database.getReference("foods")
		foodsReference.addListenerForSingleValueEvent(object : ValueEventListener {
			override fun onCancelled(exception: DatabaseError) {
				exception.toException()
						.printStackTrace()
			}

			override fun onDataChange(dataSnapshot: DataSnapshot) {
				val foods = dataSnapshot.children.map {
					it.getValue(Food::class.java) ?: Food()
				}
				foodAdapter.updateFoods(foods = foods)
				recyclerViewFood.visibility = View.VISIBLE
				progressView.visibility = View.GONE
			}
		})
	}
}
