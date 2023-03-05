package ie.wit.assignment1.main

import android.app.Application
import ie.wit.assignment1.models.BoxerArray1
import ie.wit.assignment1.models.BoxerMemStore
import timber.log.Timber


class MainApp : Application() {

    val boxerList = BoxerMemStore()



    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Boxer App started")

    }
}