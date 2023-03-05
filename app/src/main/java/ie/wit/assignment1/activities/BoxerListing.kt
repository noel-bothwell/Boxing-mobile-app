package ie.wit.assignment1.activities


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
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


        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app=application as MainApp

        val layoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager
        binding.recyclerView.adapter=BoxerAdapter(app.boxerList.findAll(),this)


    }

     override fun onCreateOptionsMenu(menu: Menu): Boolean {
         menuInflater.inflate(R.menu.menu_main, menu)
         return super.onCreateOptionsMenu(menu)
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             R.id.item_add -> {
                 val launcherIntent = Intent(this, Archive::class.java)
                 getResult.launch(launcherIntent)
             }
         }
         return super.onOptionsItemSelected(item)
     }

     private val getResult =
         registerForActivityResult(
             ActivityResultContracts.StartActivityForResult()
         ) {
             if (it.resultCode == Activity.RESULT_OK) {
                 (binding.recyclerView.adapter)?.
                 notifyItemRangeChanged(0,app.boxerList.findAll().size)
             }
         }

     override fun onBoxerClick(boxer: BoxerArray1) {
         val launcherIntent = Intent(this, Archive::class.java)
         launcherIntent.putExtra("boxer_edit", boxer)
         getClickResult.launch(launcherIntent)
     }
     private val getClickResult =
         registerForActivityResult(
             ActivityResultContracts.StartActivityForResult()
         ) {
             if (it.resultCode == Activity.RESULT_OK) {
                 (binding.recyclerView.adapter)?.
                 notifyItemRangeChanged(0,app.boxerList.findAll().size)
             }
         }
     override fun onDelete(boxer: BoxerArray1) {

         app.boxerList.delete(boxer)
         Log.i("Info", "DELETE $boxer")

         }



     }


