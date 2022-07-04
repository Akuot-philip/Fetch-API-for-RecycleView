package dev.akuot.recyclerview

//import okhttp3.Call
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    fun getPosts():Call<MutableList<postModel>>
}