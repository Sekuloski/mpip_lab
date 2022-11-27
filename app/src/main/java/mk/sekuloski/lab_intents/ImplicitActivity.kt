package mk.sekuloski.lab_intents

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet


class ImplicitActivity : AppCompatActivity() {
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)
        val layout = findViewById<ConstraintLayout>(R.id.implicitLayout)
        val set = ConstraintSet()
        var margin = 0

        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList = packageManager.queryIntentActivities(mainIntent, 0)
        for (i in pkgAppsList.indices) {
            val newTextView = TextView(this);
            newTextView.text = pkgAppsList[i].activityInfo.name
            newTextView.id = TextView.generateViewId();
            layout.addView(newTextView);
            set.clone(layout);
            set.connect(newTextView.id, ConstraintSet.TOP, layout.id, ConstraintSet.TOP, margin);
            set.applyTo(layout);
            margin += 60
        }

    }
}