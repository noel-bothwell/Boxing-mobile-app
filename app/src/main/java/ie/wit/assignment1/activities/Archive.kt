package ie.wit.assignment1.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import ie.wit.assignment1.R
import ie.wit.assignment1.databinding.ArchiveBinding
import ie.wit.assignment1.models.BoxerArray1
import ie.wit.assignment1.main.MainApp
import timber.log.Timber
import com.squareup.picasso.Picasso
import ie.wit.assignment1.adapters.BoxerAdapter
import ie.wit.assignment1.adapters.BoxerListener
import ie.wit.assignment1.helpers.showImagePicker
import ie.wit.assignment1.models.BoxerMemStore
import ie.wit.assignment1.models.BoxerStore
import timber.log.Timber.i

class Archive  : AppCompatActivity() {

    private lateinit var binding : ArchiveBinding

    lateinit var app: MainApp


    lateinit var boxer:BoxerArray1
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    val IMAGE_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArchiveBinding.inflate(layoutInflater)
        var edit = false

        boxer = BoxerArray1()
        val view = binding.root

        app = application as MainApp

        var button1 = binding.Save



        if (intent.hasExtra("boxer_edit")) {
            var edit = true
            boxer = intent.extras?.getParcelable("boxer_edit")!!
            binding.editTextTextPersonName.setText(boxer.name)
            binding.numberWins.setText(boxer.numberWins.toString())
            binding.numberLosses.setText(boxer.numberlosses.toString())
            val adapter = binding.spinner.adapter as? ArrayAdapter<String>
            if (adapter != null) {
                binding.spinner.setSelection(adapter.getPosition(boxer.weightClass))
            }


            binding.date.setText(boxer.birthDate)
            binding.Save.setText(R.string.updateBoxer)

            Picasso.get()
                .load(boxer.image)
                .into(binding.boxerImage)
            if (boxer.image != Uri.EMPTY) {
                binding.selectImage.setText(R.string.changeimage)
            }

        }

        button1.setOnClickListener() {
            val boxername = binding.editTextTextPersonName.text.toString()
            val wins = binding.numberWins.text.toString().toInt()
            val losses = binding.numberLosses.text.toString().toInt()
            val weightClass = binding.spinner.selectedItem.toString()

            val birthDate = binding.date.text.toString()
            val boxerNew = BoxerArray1(boxer.id, boxername, wins, losses, weightClass, birthDate,boxer.image)




            if (intent.hasExtra("boxer_edit")) {

                app.boxerList.update(boxerNew.copy())
            } else {
                app.boxerList.create(boxerNew.copy())
            }
             for (box in app.boxerList.findAll()){
                println(box)
             }
            //setResult(RESULT_OK)
            //finish()
        }

        binding.selectImage.setOnClickListener {
            showImagePicker(imageIntentLauncher,this)
        }

        registerImagePickerCallback()





        val button = binding.goList
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

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")

                            val image = result.data!!.data!!
                            contentResolver.takePersistableUriPermission(image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            boxer.image = image

                            Picasso.get()
                                .load(boxer.image)
                                .into(binding.boxerImage)
                            binding.selectImage.setText(R.string.select_boxer_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}