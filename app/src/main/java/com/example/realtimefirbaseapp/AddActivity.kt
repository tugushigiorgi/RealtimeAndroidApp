package com.example.realtimefirbaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimefirbaseapp.Database.Models.Student
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {

    lateinit var studentNameInputText:EditText
    lateinit var studentSurnameInputText:EditText
    lateinit var studentEmailInputText:EditText
    lateinit var studenpicutureinputtext:EditText
    lateinit var studentidinputtext:EditText
    lateinit var AddBTN:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
            init()

        AddBTN.setOnClickListener {

            if(!studentEmailInputText.text.contains("@") && !studentEmailInputText.text.contains(".") ){

                Toast.makeText(this,"შეიყვანეთ ვალიდური ელ-ფოსტა !",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(studentidinputtext.text.length!=13){

                Toast.makeText(this,"შეიყვანეთ ვალიდური ID!",Toast.LENGTH_SHORT).show()

            return@setOnClickListener
            }
            if(studentNameInputText.text.isNullOrEmpty() ||   studentSurnameInputText.text.isNullOrEmpty() || studenpicutureinputtext.text.isNullOrEmpty() ){
                Toast.makeText(this,"შეავსეთ ყველა ველი ! ",Toast.LENGTH_SHORT).show()

                return@setOnClickListener

            }

             var usermodel=Student(studentidinputtext.text.toString(),
                 studentNameInputText.text.toString(),
                 studentSurnameInputText.text.toString(),
                         studentEmailInputText.text.toString(),
                                 studenpicutureinputtext.text.toString()

             )

            FirebaseDatabase.getInstance().getReference("School")
                .child("Students").push().setValue( usermodel).addOnSuccessListener {

                Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                }.addOnFailureListener {
                Toast.makeText(this, "Failed to save data:  ", Toast.LENGTH_SHORT).show()
            }



        }




    }
    fun init(){
        studentNameInputText=findViewById(R.id.StudentNameInput)
        studentSurnameInputText=findViewById(R.id.studentsurnameinput)
        studentEmailInputText=findViewById(R.id.studentemailinput)
        studenpicutureinputtext=findViewById(R.id.studentpciturelinkinput)

        studentidinputtext=findViewById(R.id.studentidinput)
        AddBTN=findViewById(R.id.addbutton)




    }
}