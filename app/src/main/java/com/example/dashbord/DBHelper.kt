package com.example.dashbord

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Core1", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE USERS (USERID INTEGER PRIMARY KEY AUTOINCREMENT, UNAME TEXT, PWD TEXT,DOB TEXT, SKILL TEXT,LANGUAGE TEXT, EMAIL TEXT, PHONE TEXT,ADDRESS TEXT, HISTORY TEXT )")
        db?.execSQL("CREATE TABLE POST(PID INTEGER PRIMARY KEY AUTOINCREMENT, USERID INTEGER, TITLE TEXT, DES TEXT, LOCATION TEXT, APPLICATION TEXT, CONTRACT TEXT, DATE TEXT, FOREIGN KEY (USERID) REFERENCES USERS(USERID))")
        db?.execSQL("INSERT INTO USERS (UNAME,PWD,DOB,SKILL,LANGUAGE,EMAIL,PHONE,ADDRESS, HISTORY) VALUES ('CHINGSIEN LY','CHINGSIEN12','26/05/2001','IT MANAGER','KHMER, ENGLISH','Chingsien09@gmail.com','0449219024','85 peel str, kew VIC 3101','Work at Smart company')")
        db?.execSQL("INSERT INTO USERS (UNAME,PWD,DOB,SKILL,LANGUAGE,EMAIL,PHONE,ADDRESS, HISTORY) VALUES ('LyLY','CHINGSIEN12','26/05/2001','IT MANAGER','KHMER, ENGLISH, THAI','lyly@gmail.com','0449219024','85 peel str, kew VIC 3101','Work at Smart company')")

        db?.execSQL("INSERT INTO post(TITLE,USERID,DES,LOCATION,APPLICATION,CONTRACT,DATE) VALUES ('IT manager',1,'manage the group','VIC','fulltime','on-going','12/12/22')")
        db?.execSQL("INSERT INTO post(TITLE,USERID,DES,LOCATION,APPLICATION,CONTRACT,DATE) VALUES ('IT support',1,'manage the group','VIC','parttime','on-going','12/12/23')")
        db?.execSQL("INSERT INTO post(TITLE,USERID,DES,LOCATION,APPLICATION,CONTRACT,DATE) VALUES ('Retail',2,'manage the group of Rewtail','VIC','fulltime','on-going','12/12/24')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun getPost(context: Context): ArrayList<Post>//return the expression
    {

        val qry = "SELECT * FROM POST"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        //create the array to hold the element form the database
        //array must be a  array list
        val postArrayList = ArrayList<Post>()
        if (cursor.count == 0) {
            Toast.makeText(context, "0 result found!", Toast.LENGTH_SHORT).show()
        } else {

            while (cursor.moveToNext()) {
                //create the copy of the model
                val eachLineHolder = Post()
                eachLineHolder.pid = cursor.getInt(0)
                eachLineHolder.title = cursor.getString(2)
                eachLineHolder.description = cursor.getString(3)
                eachLineHolder.location = cursor.getString(4)
                eachLineHolder.application = cursor.getString(5)
                eachLineHolder.contract = cursor.getString(6)
                eachLineHolder.date = cursor.getString(7)
                postArrayList.add(eachLineHolder)
            }
        }
        db.close()
        cursor.close()
        return postArrayList
    }
}