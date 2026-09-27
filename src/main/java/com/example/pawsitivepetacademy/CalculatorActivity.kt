package com.example.pawsitivepetacademy

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.NumberFormat
import java.util.Locale

class CalculatorActivity : BaseActivity() {
    data class SelectedCourse(val box: CheckBox, val fee: Int, val name: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setupMenu(R.id.menuButton)

        val courses = listOf(
            SelectedCourse(findViewById(R.id.canineCheck),1500,"Canine Obedience Training"),
            SelectedCourse(findViewById(R.id.groomingCheck),1500,"Pet Grooming"),
            SelectedCourse(findViewById(R.id.behaviourCheck),1500,"Animal Behaviour"),
            SelectedCourse(findViewById(R.id.businessCheck),1500,"Pet Business Management"),
            SelectedCourse(findViewById(R.id.puppyCheck),750,"Puppy Care"),
            SelectedCourse(findViewById(R.id.firstAidCheck),750,"Pet First Aid"),
            SelectedCourse(findViewById(R.id.walkingCheck),750,"Basic Dog Walking")
        )

        intent.getStringExtra("preselect")?.let { name ->
            courses.firstOrNull { it.name == name }?.box?.isChecked = true
        }

        findViewById<Button>(R.id.calculateButton).setOnClickListener { calculate(courses) }
    }

    private fun calculate(courses: List<SelectedCourse>) {
        val name = findViewById<EditText>(R.id.nameInput)
        val phone = findViewById<EditText>(R.id.phoneInput)
        val email = findViewById<EditText>(R.id.emailInput)

        if (name.text.toString().trim().isEmpty()) { name.error = "Please enter your full name."; return }
        if (!phone.text.toString().trim().matches(Regex("\\d{7,15}"))) { phone.error = "Please enter a valid phone number."; return }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString().trim()).matches()) { email.error = "Please enter a valid email address."; return }

        val selected = courses.filter { it.box.isChecked }
        if (selected.isEmpty()) {
            Toast.makeText(this, "Please select at least one course.", Toast.LENGTH_SHORT).show()
            return
        }

        val subtotal = selected.sumOf { it.fee }
        val discountRate = when (selected.size) {
            1 -> 0.0
            2 -> 0.05
            3 -> 0.10
            else -> 0.15
        }
        val discount = subtotal * discountRate
        val discounted = subtotal - discount
        val vat = discounted * 0.15
        val total = discounted + vat
        val money = NumberFormat.getCurrencyInstance(Locale("en","ZA"))

        findViewById<TextView>(R.id.resultText).text = """
            Quote for ${name.text}

            Courses selected: ${selected.size}
            Subtotal: ${money.format(subtotal)}
            Discount (${(discountRate * 100).toInt()}%): -${money.format(discount)}
            After discount: ${money.format(discounted)}
            VAT (15%): ${money.format(vat)}

            FINAL QUOTE: ${money.format(total)}
        """.trimIndent()
    }
}
