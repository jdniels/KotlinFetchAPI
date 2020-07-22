 package com.example.myapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.UiThread
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://jsonplaceholder.typicode.com/users"

        AsyncTaskHandleJson().execute(url)
    }

    inner class AsyncTaskHandleJson : AsyncTask<String, String, String>(){
        override fun doInBackground(vararg url: String?): String {
            val text : String

            val connection = URL(url[0]).openConnection() as HttpURLConnection

            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use{ reader ->  reader.readText()} }
            }finally {
                connection.disconnect()
            }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

     private fun handleJson(result: String?){
         val jsonArray = JSONArray(result)

         val list = ArrayList<UserEntity>()

         var x  = 0

         while (x < jsonArray.length()){
             val jsonObject = jsonArray.getJSONObject(x)

             list.add(
                 UserEntity(
                 jsonObject.getInt("id"),
                     jsonObject.getString("name"),
                     jsonObject.getString("username"),
                     jsonObject.getString("email")
                 ))
             x++
         }

         val adapter = ListAdapter(this, list)
         users.adapter = adapter
     }

}