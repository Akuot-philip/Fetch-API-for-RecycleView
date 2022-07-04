package dev.akuot.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView=findViewById<RecyclerView>(R.id.myRecycleview)

        val serviceGenerator = serviceGenerator.buildService(ApiService::class.java)
        val call =serviceGenerator.getPosts()

//        val button = findViewById<Button>(R.id.btnClick)
//        button.setOnClickListener{
            call.enqueue(object: Callback<MutableList<postModel>>{
                override fun onResponse(
                    call: Call<MutableList<postModel>>, response: Response<MutableList<postModel>>) {
                    if(response.isSuccessful){
                        recyclerView.apply {
                            layoutManager=LinearLayoutManager(this@MainActivity)
                            adapter=PostAdapter(response.body()!!)
                        }
                    }

                }

                override fun onFailure(call: Call<MutableList<postModel>>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error",t.message.toString())

                }

            })
        }
    }
