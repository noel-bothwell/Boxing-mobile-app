package ie.wit.assignment1.activities


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.assignment1.R
import ie.wit.assignment1.databinding.ListBinding
import ie.wit.assignment1.main.MainApp
import ie.wit.assignment1.adapters.BoxerAdapter
import ie.wit.assignment1.adapters.BoxerListener
import ie.wit.assignment1.models.BoxerArray1


 class BoxerListing : AppCompatActivity(), BoxerListener {
    lateinit var app:MainApp

    private lateinit var binding:ListBinding
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        binding=ListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val view=binding.root

        app=application as MainApp

        val layoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager
        binding.recyclerView.adapter=BoxerAdapter(app.boxerList.findAll(),this)


    }

     override fun onBoxerClick(boxer: BoxerArray1) {
         TODO("Not yet implemented")
     }

     override fun onDelete(boxer: BoxerArray1) {

         app.boxerList.delete(boxer)
         Log.i("Info", "DELETE $boxer")

         }



     }

