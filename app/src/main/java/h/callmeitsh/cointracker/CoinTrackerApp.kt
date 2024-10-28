package h.callmeitsh.cointracker

import android.app.Application
import h.callmeitsh.cointracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoinTrackerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoinTrackerApp)
            androidLogger()
            modules(appModule)
        }
    }
}