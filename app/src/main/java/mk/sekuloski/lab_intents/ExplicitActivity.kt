package mk.sekuloski.lab_intents

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ExplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        findViewById<Button>(R.id.btnOk).setOnClickListener{ _ ->
            Intent().let { intent ->
                intent.putExtra("result", findViewById<TextView>(R.id.tvText).text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }

        findViewById<Button>(R.id.btnCancel).setOnClickListener{
            finish()
            }

    }
}