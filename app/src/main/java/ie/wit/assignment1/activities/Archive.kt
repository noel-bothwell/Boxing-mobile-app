package ie.wit.assignment1.activities

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ie.wit.assignment1.R
import ie.wit.assignment1.databinding.ArchiveBinding
import ie.wit.assignment1.models.BoxerArray1
import ie.wit.assignment1.main.MainApp

class Archive  : AppCompatActivity() {

    private lateinit var binding : ArchiveBinding

    lateinit var app: MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArchiveBinding.inflate(layoutInflater)
        var edit = false


        val view = binding.root

        app = application as MainApp

        var button1 = binding.Save





        button1.setOnClickListener() {
            val boxername = binding.editTextTextPersonName.text.toString()
            val wins = binding.numberWins.text.toString().toInt()
            val losses = binding.numberLosses.text.toString().toInt()
            val weightClass = binding.spinner.selectedItem.toString()
            val isRetired = binding.isRetired.isChecked
            val birthDate = binding.date.text.toString()
            var boxer = BoxerArray1(0, boxername, wins, losses, weightClass, isRetired, birthDate)


            if (intent.hasExtra("boxer_edit")) {
                var edit = true
                boxer = intent.extras?.getParcelable("boxer_edit")!!
                binding.editTextTextPersonName.setText(boxername)
                binding.numberWins.setText(wins.toString())
                binding.numberLosses.setText(losses.toString())
                binding.spinner.setSelection((binding.spinner.adapter as ArrayAdapter<String>).getPosition(weightClass))
                binding.isRetired.isChecked = isRetired
                binding.date.setText(birthDate)
                binding.Save.setText(R.string.save)
            }

            if (intent.hasExtra("boxer_edit")) {
                app.boxerList.update(boxer.copy())
            } else {
                app.boxerList.create(boxer)
            }
             for (box in app.boxerList.findAll()){
                println(box)
             }
        }




        var button = binding.goList
        button.setOnClickListener {
            openList()
        }



        val spinner: Spinner = binding.spinner
        // Creating an array adapter using the string array and a spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.weight_class,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                spinner.adapter = adapter
            }

        setContentView(view)
    }

    private fun openList(){
        val intent = Intent(this, BoxerListing::class.java)
        startActivity(intent)

    }


}