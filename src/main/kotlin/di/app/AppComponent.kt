package di.app

import app.App
import dagger.Component
import di.event.BotComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ConfigModule::class,
    LoginApiModule::class,
    AppModule::class
])
interface AppComponent {
    fun inject(app: App)

    fun plus(): BotComponent
}