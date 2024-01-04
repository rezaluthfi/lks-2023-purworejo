package com.example.purworejoapik

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {

    private lateinit var daftarkegiatan: CardView
    private lateinit var kalendar: CardView
    private lateinit var infoaplikasi: CardView

    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        daftarkegiatan = findViewById(R.id.daftar_kegiatan)
        kalendar = findViewById(R.id.kalendar)
        infoaplikasi = findViewById(R.id.infoaplikasi)

        daftarkegiatan.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        kalendar.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }

        builder = AlertDialog.Builder(this)

        infoaplikasi.setOnClickListener{
            builder.setTitle("Info Aplikasi")
                .setMessage("Version 3.0 \nDeveloped by Reza Luthfi Akbar")
                .setCancelable(true)
                .setNegativeButton("OK") {dialogInterface, it ->
                    dialogInterface.cancel()
                }
                .show()
        }

    }
}

