package com.example.realtimefirbaseapp.Database.Models

data class School (
    var id:Int ,
    var Location:String ,
    var Name:String ,
    var Email:String,
    var Students :ArrayList<Student>


        )