package com.example.pawsitivepetacademy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button

class ContactActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        setupMenu(R.id.menuButton)

        findViewById<Button>(R.id.umhlangaButton).setOnClickListener { directions("Umhlanga Training Centre, Durban, South Africa") }
        findViewById<Button>(R.id.bereaButton).setOnClickListener { directions("Berea Learning Hub, Durban, South Africa") }
        findViewById<Button>(R.id.pinetownButton).setOnClickListener { directions("Pinetown Skills Studio, Durban, South Africa") }
    }

    private fun directions(place: String) {
        val uri = Uri.parse("geo:0,0?q=${Uri.encode(place)}")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}
