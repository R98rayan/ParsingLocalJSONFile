package com.example.parsinglocaljsonfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    var list = ArrayList<String>()

    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        OurClass.main = this

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(list)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        rvAdapter.update(getJsonImagesURL())

    }

    fun getJsonImagesURL(): ArrayList<String>{

        var list2 = ArrayList<String>()

        var jsonFile = assets.open("data.json")
        val jsonData = jsonFile.bufferedReader().use { it.readText() }
        var jsonArray = JSONArray(jsonData)

        for (i in 0 until jsonArray.length()) {
            var url = jsonArray.getJSONObject(i).getString("hdurl")
            list2.add(url)
        }

        return list2
    }

    fun updateImageByURL(url: String, imageView: ImageView){
        Glide.with(this).load(url).into(imageView)
    }

}