package com.example.pawsitivepetacademy

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class SixWeekActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_six_week)
        setupMenu(R.id.menuButton)
        listOf(
            R.id.puppyButton to "Puppy Care",
            R.id.firstAidButton to "Pet First Aid",
            R.id.walkingButton to "Basic Dog Walking"
        ).forEach { (id, course) ->
            findViewById<Button>(id).setOnClickListener {
                startActivity(Intent(this, CourseDetailActivity::class.java).putExtra("course", course))
            }
        }
    }
}
