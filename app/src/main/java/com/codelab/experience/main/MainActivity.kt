package com.codelab.experience.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codelab.experience.Food
import com.codelab.experience.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val database = FirebaseDatabase.getInstance()
		val foodsReference = database.getReference("foods")
		foodsReference.addListenerForSingleValueEvent(object : ValueEventListener {
			override fun onCancelled(exception: DatabaseError) {
				exception.toException()
						.printStackTrace()
			}

			override fun onDataChange(dataSnapshot: DataSnapshot) {
				val foods = dataSnapshot.children.map { it.getValue(Food::class.java) }
				Log.d(MainActivity::class.java.simpleName, foods.toString())
			}
		})
	}
}
