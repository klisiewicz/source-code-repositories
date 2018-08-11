package pl.karollisiewicz.reposistory

import android.app.Application
import org.koin.android.ext.android.startKoin
import pl.karollisiewicz.reposistory.di.modules

class RepoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, modules)
    }
}