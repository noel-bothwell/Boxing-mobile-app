package ie.wit.assignment1.main

import android.app.Application
import ie.wit.assignment1.models.BoxerArray1
import ie.wit.assignment1.models.BoxerMemStore
import ie.wit.assignment1.models.BoxerStore
import ie.wit.assignment1.models.JSONStore
import timber.log.Timber


class MainApp : Application() {

    lateinit var boxerList : BoxerStore



    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        boxerList = JSONStore(applicationContext)
        Timber.i("Boxer App started")

    }
}