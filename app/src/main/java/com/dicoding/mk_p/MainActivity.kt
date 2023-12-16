package com.dicoding.mk_p

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFood: RecyclerView
    private val list = ArrayList<Food>()
    private lateinit var listFoodAdapter: ListFoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFood = findViewById(R.id.rv_food)
        rvFood.setHasFixedSize(true)

        list.addAll(getListFood())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvFood.layoutManager = LinearLayoutManager(this)
                listFoodAdapter.notifyDataSetChanged()
            }

            R.id.action_grid -> {
                rvFood.layoutManager = GridLayoutManager(this, 2)
                listFoodAdapter.notifyDataSetChanged()
            }

            R.id.action_about -> {

                showAboutDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAboutDialog() {
        intent = Intent(this@MainActivity, AboutActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("Recycle")
    private fun getListFood(): ArrayList<Food> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listFood = ArrayList<Food>()
        for (i in dataName.indices) {
            val hero = Food(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listFood.add(hero)
        }
        return listFood
    }

    private fun showRecyclerList() {
        rvFood.layoutManager = LinearLayoutManager(this)
        listFoodAdapter = ListFoodAdapter(list, object : ListFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Food) {
                showSelectedFood(data)
            }
        })
        rvFood.adapter = listFoodAdapter
    }

    private fun showSelectedFood(food: Food) {
        val intent = Intent(this, Detail::class.java)
        intent.putExtra("imageView", food.photo)
        intent.putExtra("titleTextView", food.name)
        intent.putExtra("contentTextView", food.description)
        startActivity(intent)
    }
}
