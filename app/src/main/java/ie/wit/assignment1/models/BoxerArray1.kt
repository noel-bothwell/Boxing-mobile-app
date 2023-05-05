package ie.wit.assignment1.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BoxerArray1 (
    var id: Long = 0,
    var name: String = "",
    var numberWins: Int =0,
    var numberlosses: Int =0,
    var weightClass: String ="" ,
    var birthDate: String = "" ,
    var image: Uri = Uri.EMPTY) : Parcelable


