package mk.sekuloski.lab_intents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private val pickImage = 100

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            tvResult.text = data?.getStringExtra("result")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResult = findViewById(R.id.tvResult)

        findViewById<Button>(R.id.btnExplicit).setOnClickListener{ _ ->
            Intent(this, ExplicitActivity::class.java).let { intent ->
                resultLauncher.launch(intent)
            }
        }

        findViewById<Button>(R.id.btnImplicit).setOnClickListener{
            Intent().apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
                type = "text/plain"
            }.let { intent ->
                startActivity(Intent.createChooser(intent,"Choose the app for your intent"))
                }

        }

        findViewById<Button>(R.id.btnImage).setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        findViewById<Button>(R.id.btnActionSend).setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "MPIP Send Title")
            startActivity(shareIntent)
        }


    }
}