package app

import di.app.AppComponent
import activity.LoginActivity
import di.app.DaggerAppComponent
import javax.inject.Inject

class App : Phase {
    val appComponent: AppComponent = DaggerAppComponent.builder().build()

    @Inject lateinit var loginActivity: LoginActivity

    init {
        appComponent.inject(this)
    }

    override fun start() {
        loginActivity.start()
    }

    override fun close() {

    }
}