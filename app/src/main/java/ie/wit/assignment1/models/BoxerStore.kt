package ie.wit.assignment1.models

interface BoxerStore {
    fun findAll(): List<BoxerArray1>
    fun create(boxer: BoxerArray1)
    fun update(boxer: BoxerArray1)
    fun delete(boxer: BoxerArray1)
}