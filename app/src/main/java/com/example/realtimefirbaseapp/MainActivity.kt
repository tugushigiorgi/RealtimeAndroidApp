package com.example.realtimefirbaseapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimefirbaseapp.Adapters.RecyclerAdapter
import com.example.realtimefirbaseapp.Database.Models.School
import com.example.realtimefirbaseapp.Database.Models.Student
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase

    lateinit var RecyclerViewXML:RecyclerView
    var RecyclerData= ArrayList<Student>()
    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RecyclerViewXML=findViewById(R.id.recyclerxml)

        var Addbt=findViewById<Button>(R.id.button)
        FirebaseApp.initializeApp(this)

        RecyclerViewXML.layoutManager=LinearLayoutManager(this)

        Addbt.setOnClickListener {
            startActivity(Intent(this,AddActivity::class.java))
        }

        var recycleradapter=RecyclerAdapter(RecyclerData)
        RecyclerViewXML.adapter=recycleradapter
        FirebaseDatabase.getInstance().getReference("School").child("Students")
            .addValueEventListener(object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    RecyclerData.clear()


                    for (i in snapshot.children){


                        val studentarray=Student(
                            i.child("user_ID").getValue().toString(),
                            i.child("name").getValue().toString(),
                                    i.child("surname").getValue().toString(),
                            i.child("email").getValue().toString(),
                            i.child("profilePhoto").getValue().toString(),
                        )



                        RecyclerData.add(studentarray)



                    }

                    recycleradapter.notifyDataSetChanged()






                }

                override fun onCancelled(error: DatabaseError) {
                 }
            })






    }
}