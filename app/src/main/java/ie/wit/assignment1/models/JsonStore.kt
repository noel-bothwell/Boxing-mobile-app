package ie.wit.assignment1.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.wit.assignment1.helpers.exists
import ie.wit.assignment1.helpers.read
import ie.wit.assignment1.helpers.write
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "boxers.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<BoxerArray1>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class JSONStore(private val context: Context) : BoxerStore {

    var boxers = mutableListOf<BoxerArray1>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<BoxerArray1> {
        logAll()
        return boxers
    }

    override fun create(boxer: BoxerArray1) {
        boxer.id = generateRandomId()
        boxers.add(boxer)
        serialize()
    }

    override fun update(boxer: BoxerArray1) {
        val allBoxers = findAll() as ArrayList<BoxerArray1>
        var foundBoxer: BoxerArray1? = allBoxers.find { p -> p.id == boxer.id }
        if (foundBoxer != null) {
            foundBoxer.name = boxer.name
            foundBoxer.numberWins = boxer.numberWins
            foundBoxer.numberlosses = boxer.numberlosses
            foundBoxer.weightClass = boxer.weightClass
            foundBoxer.birthDate = boxer.birthDate
            foundBoxer.image = boxer.image
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(boxers, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        boxers = gsonBuilder.fromJson(jsonString, listType)
    }

    override fun delete(boxer: BoxerArray1) {
        boxers.remove(boxer)
        serialize()
    }

    private fun logAll() {
        boxers.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}