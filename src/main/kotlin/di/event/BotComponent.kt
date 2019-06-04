package di.event

import dagger.Subcomponent
import di.PerLogin
import presentation.bot.BotActivity

@PerLogin
@Subcomponent(
    modules = [
        ApiModule::class,
        DataModule::class
    ]
)
interface BotComponent {
    fun inject(botActivity: BotActivity)
}