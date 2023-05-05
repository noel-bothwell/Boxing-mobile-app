package ie.wit.assignment1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import ie.wit.assignment1.databinding.ActivityMainBinding
import ie.wit.assignment1.main.MainApp


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var app: MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
       app = application as MainApp
        setContentView(binding.root)



        var button = binding.EnterButton
        button.setOnClickListener() {
            openArchive()
        }
    }

    private fun openArchive(){
    val intent = Intent(this, Archive::class.java)
        startActivity(intent)


}
}
