package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val languages = listOf(
        Pair("English", R.drawable.flag_en),
        Pair("Português", R.drawable.flag_pt),
        Pair("Español", R.drawable.flag_es),
        Pair("Deutsch", R.drawable.flag_de)
    )
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

        // Dropdown para troca de idioma com bandeiras
        val languageDropdown = findViewById<Spinner>(R.id.languageDropdown)
        val adapter = object : ArrayAdapter<Pair<String, Int>>(this, R.layout.item_language, languages) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return createLanguageItemView(position, parent)
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                return createLanguageItemView(position, parent)
            }

            private fun createLanguageItemView(position: Int, parent: ViewGroup): View {
                val view = layoutInflater.inflate(R.layout.item_language, parent, false)
                val flagIcon = view.findViewById<ImageView>(R.id.flagIcon)
                val languageText = view.findViewById<TextView>(R.id.languageText)

                val (text, icon) = getItem(position)!!
                flagIcon.setImageResource(icon)
                languageText.text = text

                return view
            }
        }
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

