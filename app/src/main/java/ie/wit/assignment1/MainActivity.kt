package ie.wit.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById (R.id.EnterButton) as Button
        button.setOnClickListener() {
            openArchive()


        }
    }

    private fun openArchive(){
    val intent = Intent(this, Archive::class.java)
            startActivity(intent)

}
}
