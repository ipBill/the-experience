package com.codelab.experience.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codelab.experience.Food
import com.codelab.experience.FoodAdapter
import com.codelab.experience.R
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
	}

	private fun initFirebaseRealTimeDatabase() {
		val database = FirebaseDatabase.getInstance()
		val foodsReference = database.getReference("foods")
		foodsReference.addListenerForSingleValueEvent(object : ValueEventListener {
			override fun onCancelled(exception: DatabaseError) {
				exception.toException()
						.printStackTrace()
			}

			override fun onDataChange(dataSnapshot: DataSnapshot) {
				val foods = dataSnapshot.children.map { it.getValue(Food::class.java) ?: Food() }
				foodAdapter.updateFoods(foods = foods)
			}
		})
	}
}