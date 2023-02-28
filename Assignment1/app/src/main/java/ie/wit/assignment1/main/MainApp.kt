package ie.wit.assignment1.main

import android.app.Application
import ie.wit.assignment1.models.BoxerArray1


class MainApp : Application() {

    val boxerList = ArrayList<BoxerArray1>()

    override fun onCreate() {
        super.onCreate()
       //// Timber.plant(Timber.DebugTree())
        println("boxer started")

    }
}