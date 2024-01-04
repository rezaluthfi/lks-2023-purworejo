package com.example.purworejoapik

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.purworejoapik.adapter.UserAdapter
import com.example.purworejoapik.data.AppDatabase
import com.example.purworejoapik.data.entity.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var  recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<User>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabase

    private lateinit var editSearch: EditText
    private lateinit var btnSearch: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)

        editSearch = findViewById(R.id.edit_search)
        btnSearch = findViewById(R.id.btn_search)

        database = AppDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog{
            override fun onClick(position: Int) {
                //membuat dialog view
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle(list[position].fullName)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog, which ->
                    if (which == 0 ){
                        //coding ubah
                        val intent = Intent(this@MainActivity, EditorActivity::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    } else if (which == 1){
                        //coding hapus
                        database.userDao().delete(list[position])
                        getData()
                        Toast.makeText(applicationContext, "Berhasil hapus data", Toast.LENGTH_SHORT).show()
                    } else {
                        //coding batal
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }

        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener {
            startActivity(Intent(this, EditorActivity::class.java))
        }
        btnSearch.setOnClickListener{
            if (editSearch.text.isNotEmpty()) {
                searchData(editSearch.text.toString())
            } else {
                getData()
                Toast.makeText(applicationContext, "Isi pencarian", Toast.LENGTH_SHORT).show()
            }
        }
        editSearch.setOnKeyListener { v, keyCode, event ->
            if (editSearch.text.isNotEmpty()) {
                searchData(editSearch.text.toString())
            } else {
                getData()
            }
            false
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.userDao().getAll())
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun searchData(search: String){
        list.clear()
        list.addAll(database.userDao().searchByName(search))
        adapter.notifyDataSetChanged()
    }
}