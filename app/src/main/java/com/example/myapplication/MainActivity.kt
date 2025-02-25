package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val languages = listOf("English", "Português", "Español", "Deutsch")
    private val languageCodes = listOf("en", "pt", "es", "de")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applySavedLanguage()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botão de Toast
        findViewById<Button>(R.id.buttonAction).setOnClickListener {
            showToast()
        }

        // Dropdown para troca de idioma
        val languageDropdown = findViewById<Spinner>(R.id.languageDropdown)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
        languageDropdown.adapter = adapter

        // Define o idioma atual selecionado
        val sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val currentLang = sharedPreferences.getString("My_Lang", "en") ?: "en"
        languageDropdown.setSelection(languageCodes.indexOf(currentLang))

        languageDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLanguage = languageCodes[position]
                if (selectedLanguage != currentLang) {
                    setLocale(selectedLanguage)
                    recreate()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun showToast() {
        Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show()
    }

    private fun setLocale(language: String) {
        Locale.setDefault(Locale(language))
        val config = resources.configuration
        config.setLocale(Locale(language))
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        getSharedPreferences("Settings", Context.MODE_PRIVATE)
            .edit()
            .putString("My_Lang", language)
            .apply()
    }

    private fun applySavedLanguage() {
        val sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "en") ?: "en"
        setLocale(language)
    }
}
