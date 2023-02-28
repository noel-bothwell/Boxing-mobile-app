package ie.wit.assignment1.activities


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import ie.wit.assignment1.databinding.ListBinding;
import ie.wit.assignment1.main.MainApp;
import ie.wit.assignment1.adapters.BoxerAdapter
import ie.wit.assignment1.adapters.BoxerListener
import ie.wit.assignment1.models.BoxerArray1


abstract class List : AppCompatActivity(), BoxerListener {
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

    override fun onDelete(boxer: BoxerArray1) {

    }
}

