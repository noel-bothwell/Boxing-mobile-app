package ie.wit.assignment1.adapters



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.assignment1.databinding.CardBoxerBinding
import ie.wit.assignment1.models.BoxerArray1
import ie.wit.assignment1.models.BoxerMemStore
import kotlin.collections.List

interface BoxerListener {
    fun onBoxerClick(boxer: BoxerArray1)
    fun onDelete(boxer:BoxerArray1)
}



class BoxerAdapter(private var boxers: List<BoxerArray1>,
                   private val listener: BoxerListener) :
    RecyclerView.Adapter<BoxerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardBoxerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val boxer = boxers[holder.adapterPosition]
        holder.bind(boxer, listener)
    }

    override fun getItemCount(): Int = boxers.size

    class MainHolder(private val binding: CardBoxerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(boxer: BoxerArray1, listener: BoxerListener) {
            binding.boxerName.text = boxer.name
            binding.weightClass.text = boxer.weightClass
            binding.NumWins.text =  boxer.numberWins.toString()
            binding.NumLoss.text = boxer.numberlosses.toString()
            binding.dateOfBirth.text = boxer.birthDate

            binding.deleteButton.setOnClickListener{(listener.onDelete(boxer))}


        }
    }
}
