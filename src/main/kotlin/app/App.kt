package app

import di.app.AppComponent
import activity.LoginActivity
import di.app.DaggerLoginComponent
import sun.tools.jstat.Token
import javax.inject.Inject

class App : Phase {
    val appComponent: AppComponent = DaggerLoginComponent.builder().build()

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