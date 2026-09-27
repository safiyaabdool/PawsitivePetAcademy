package com.example.pawsitivepetacademy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

data class Course(val name: String, val fee: Int, val duration: String, val purpose: String, val topics: String)

class CourseDetailActivity : BaseActivity() {
    private val courses = listOf(
        Course("Canine Obedience Training",1500,"6 months","Build practical skills for safe, effective dog training.","• Basic obedience\n• Positive training methods\n• Commands and routines\n• Safe handling"),
        Course("Pet Grooming",1500,"6 months","Develop practical grooming and pet-care skills.","• Grooming equipment\n• Coat care\n• Hygiene\n• Safe grooming routines"),
        Course("Animal Behaviour",1500,"6 months","Understand animal behaviour and improve human-animal interactions.","• Body language\n• Behaviour patterns\n• Communication\n• Welfare"),
        Course("Pet Business Management",1500,"6 months","Explore the skills needed to develop a pet-related business.","• Business planning\n• Customer service\n• Pet-service operations\n• Entrepreneurship"),
        Course("Puppy Care",750,"6 weeks","Learn essential routines for healthy, confident puppies.","• Feeding\n• Basic routines\n• Socialisation\n• Safety"),
        Course("Pet First Aid",750,"6 weeks","Learn practical first-aid awareness for common pet emergencies.","• Emergency response\n• First-aid kit\n• Basic wound care\n• When to seek veterinary help"),
        Course("Basic Dog Walking",750,"6 weeks","Develop safe and responsible dog-walking skills.","• Lead handling\n• Safe routes\n• Dog behaviour\n• Responsible walking")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)
        setupMenu(R.id.menuButton)

        val selected = intent.getStringExtra("course") ?: courses.first().name
        val course = courses.first { it.name == selected }

        findViewById<TextView>(R.id.courseTitle).text = course.name
        findViewById<TextView>(R.id.courseFee).text = "R${course.fee} • ${course.duration}"
        findViewById<TextView>(R.id.coursePurpose).text = course.purpose
        findViewById<TextView>(R.id.courseTopics).text = course.topics

        findViewById<Button>(R.id.addToQuoteButton).setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java).putExtra("preselect", course.name))
        }
        findViewById<Button>(R.id.backButton).setOnClickListener { finish() }
    }
}
