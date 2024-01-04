package com.example.purworejoapik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.purworejoapik.data.AppDatabase
import com.example.purworejoapik.data.entity.User

class EditorActivity : AppCompatActivity() {
    private lateinit var fullName: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase
    private lateinit var date: EditText
    private lateinit var time: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        fullName = findViewById(R.id.full_name)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        btnSave = findViewById(R.id.btn_save)
        date = findViewById(R.id.date)
        time = findViewById(R.id.time)

        database = AppDatabase.getInstance(applicationContext)

        val intent = intent.extras
        if (intent!=null){
            val id = intent.getInt("id",0)
            val user = database.userDao().get(id)

            fullName.setText(user.fullName)
            email.setText(user.email)
            phone.setText(user.phone)
            date.setText(user.date)
            time.setText(user.time)
        }

        btnSave.setOnClickListener {
            if (fullName.text.isNotEmpty() && email.text.isNotEmpty() && phone.text.isNotEmpty() && date.text.isNotEmpty() && time.text.isNotEmpty()) {
                if (intent!=null) {
                    //coding edit data
                    database.userDao().update(
                        User(
                            intent.getInt("id",0),
                            fullName.text.toString(),
                            email.text.toString(),
                            phone.text.toString(),
                            date.text.toString(),
                            time.text.toString()
                        )
                    )
                    Toast.makeText(this, "Berhasil update data", Toast.LENGTH_SHORT).show()
                } else {
                    //coding tambah data
                    database.userDao().insertAll(
                        User(
                            null,
                            fullName.text.toString(),
                            email.text.toString(),
                            phone.text.toString(),
                            date.text.toString(),
                            time.text.toString()
                        )
                    )
                    Toast.makeText(this, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()
                }

                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Data tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}