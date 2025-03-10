package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.ActivityManager.TaskDescription
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Atualiza o ícone conforme o idioma do sistema
        val currentSystemLanguage = Locale.getDefault().language
        updateAppIconDuringRuntime(currentSystemLanguage)

        // Botão de Toast
        findViewById<Button>(R.id.buttonAction).setOnClickListener {
            showToast()
        }
    }

    /**
     * Atualiza o ícone do App Switcher conforme o idioma do sistema.
     */
    private fun updateAppIconDuringRuntime(language: String) {
        val iconResId = when (language) {
            "en" -> R.drawable.ic_launcher_en
            "pt" -> R.drawable.ic_launcher_pt
            "es" -> R.drawable.ic_launcher_es
            "de" -> R.drawable.ic_launcher_de
            else -> R.drawable.ic_launcher  // Ícone padrão
        }

        val options = BitmapFactory.Options().apply {
            inScaled = true
            inPreferredConfig = Bitmap.Config.ARGB_8888
        }

        val iconBitmap = BitmapFactory.decodeResource(resources, iconResId, options)
        if (iconBitmap != null) {
            val taskDescription = TaskDescription(getString(R.string.app_name), iconBitmap)
            setTaskDescription(taskDescription)
        }
    }

    private fun showToast() {
        Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show()
    }
}
