package app

import dagger.Lazy
import di.app.AppComponent
import di.app.AppModule
import presentation.login.LoginActivity
import di.app.DaggerAppComponent
import di.event.BotComponent
import presentation.bot.BotActivity
import javax.inject.Inject
import javax.inject.Provider

class App : Phase {
    @Suppress("JoinDeclarationAndAssignment")
    val appComponent: AppComponent

    @Inject lateinit var loginActivity: LoginActivity

    init {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }

    override fun start() {
        loginActivity.start()
    }

    override fun close() {

    }

    fun startBotActivity() {
        BotActivity(this).start()
    }
}