package com.example.pawsitivepetacademy

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class SixMonthActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_six_month)
        setupMenu(R.id.menuButton)
        listOf(
            R.id.canineButton to "Canine Obedience Training",
            R.id.groomingButton to "Pet Grooming",
            R.id.behaviourButton to "Animal Behaviour",
            R.id.businessButton to "Pet Business Management"
        ).forEach { (id, course) ->
            findViewById<Button>(id).setOnClickListener {
                startActivity(Intent(this, CourseDetailActivity::class.java).putExtra("course", course))
            }
        }
    }
}
