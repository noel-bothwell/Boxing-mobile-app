package ie.wit.assignment1.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.i
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ajalt.timberkt.Timber
import ie.wit.assignment1.R
import ie.wit.assignment1.adapters.BoxerAdapter
import ie.wit.assignment1.adapters.BoxerListener
import ie.wit.assignment1.databinding.ListBoxersBinding
import ie.wit.assignment1.main.MainApp
import ie.wit.assignment1.models.BoxerArray1


class BoxerListing : AppCompatActivity(), BoxerListener {
    lateinit var app: MainApp

    private lateinit var binding: ListBoxersBinding
    override fun onCreate(savedInstanceState: Bundle?){
        i("BOX","onCreate() BoxerListing")
        super.onCreate(savedInstanceState)
        binding=ListBoxersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app=application as MainApp

        val layoutManager= LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager
        binding.recyclerView.adapter= BoxerAdapter(app.boxerList.findAll(),this)

    }

     override fun onCreateOptionsMenu(menu: Menu): Boolean {
         i("BOX","onCreateOptionsMenu() BoxerListing")
         menuInflater.inflate(R.menu.menu_main, menu)
         return super.onCreateOptionsMenu(menu)
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         i("BOX","onOptionsItemSelected() BoxerListing")
         when (item.itemId) {
             R.id.item_addboxer-> {
                 i("BOX","menu icon ADD selected")

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

         val position = app.boxerList.findAll().indexOf(boxer)
         app.boxerList.delete(boxer)
         binding.recyclerView.adapter?.notifyItemRemoved(position)
         Log.i("Info", "DELETE $boxer")

         }



     }


