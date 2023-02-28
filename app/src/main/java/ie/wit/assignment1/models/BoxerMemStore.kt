package ie.wit.assignment1.models


import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class BoxerMemStore : BoxerStore {

    val boxers = ArrayList<BoxerArray1>()

    override fun findAll(): List<BoxerArray1> {
        return boxers
    }

    override fun create(boxer: BoxerArray1) {
        boxer.id = getId()
        boxers.add(boxer)
        logAll()
    }

    override fun delete(boxer: BoxerArray1) {
        var foundBoxer: BoxerArray1? = boxers.find { p -> p.id == boxer.id }
        boxers.remove(foundBoxer)
    }

    override fun update(boxer: BoxerArray1) {
        var foundBoxer: BoxerArray1? = boxers.find { p -> p.id == boxer.id }
        if (foundBoxer != null) {
            foundBoxer.name = boxer.name
            foundBoxer.numberWins = boxer.numberWins
            foundBoxer.numberlosses = boxer.numberlosses
            foundBoxer.weightClass = boxer.weightClass
            foundBoxer.isRetired = boxer.isRetired
            foundBoxer.birthDate = boxer.birthDate
            logAll()
        }
    }



    private fun logAll() {
        boxers.forEach { i("$it") }
    }
}