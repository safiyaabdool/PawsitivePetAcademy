package com.example.pawsitivepetacademy

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu

open class BaseActivity : AppCompatActivity() {
    protected fun setupMenu(id: Int) {
        findViewById<View>(id).setOnClickListener { view ->
            val menu = PopupMenu(this, view)
            menu.menu.add("Home")
            menu.menu.add("Six-Month Courses")
            menu.menu.add("Six-Week Courses")
            menu.menu.add("Calculate Total Fees")
            menu.menu.add("Contact Details")
            menu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.title.toString()) {
                    "Home" -> open(MainActivity::class.java)
                    "Six-Month Courses" -> open(SixMonthActivity::class.java)
                    "Six-Week Courses" -> open(SixWeekActivity::class.java)
                    "Calculate Total Fees" -> open(CalculatorActivity::class.java)
                    "Contact Details" -> open(ContactActivity::class.java)
                }
                true
            }
            menu.show()
        }
    }
    protected fun open(clazz: Class<*>) = startActivity(Intent(this, clazz))
}

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupMenu(R.id.menuButton)
        findViewById<Button>(R.id.sixMonthButton).setOnClickListener { open(SixMonthActivity::class.java) }
        findViewById<Button>(R.id.sixWeekButton).setOnClickListener { open(SixWeekActivity::class.java) }
        findViewById<Button>(R.id.quoteButton).setOnClickListener { open(CalculatorActivity::class.java) }
        findViewById<Button>(R.id.contactButton).setOnClickListener { open(ContactActivity::class.java) }
    }
}
