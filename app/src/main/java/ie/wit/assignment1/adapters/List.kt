package ie.wit.assignment1.activities


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.wit.assignment1.R
import ie.wit.assignment1.databinding.ArchiveBinding
import ie.wit.assignment1.databinding.CardBoxerBinding
import ie.wit.assignment1.databinding.ListBinding
import ie.wit.assignment1.main.MainApp
import ie.wit.assignment1.models.BoxerArray1
import kotlin.collections.List

class List : AppCompatActivity() {

    lateinit var app: MainApp

    private lateinit var binding : ListBinding
  //  val boxersList = arrayListOf<BoxerArray1>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val view = binding.root

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = BoxerAdapter(app.boxerList)



    }

}

class BoxerAdapter constructor(private var boxers: List<BoxerArray1>) :
    RecyclerView.Adapter<BoxerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardBoxerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val boxer = boxers[holder.adapterPosition]
        holder.bind(boxer, boxers)
    }

    override fun getItemCount(): Int = boxers.size

    class MainHolder(private val binding: CardBoxerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(boxer: BoxerArray1, listener: List<BoxerArray1>) {
            binding.boxerName.text = boxer.name
            binding.weightClass.text = boxer.weightClass
            binding.NumWins.text =  boxer.numberWins.toString()
            binding.NumLoss.text = boxer.numberlosses.toString()
            binding.dateOfBirth.text = boxer.birthDate
            binding.deleteButton.setOnClickListener({Listener.Delete(boxer)})


        }
    }
}
