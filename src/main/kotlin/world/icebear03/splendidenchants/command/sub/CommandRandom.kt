package world.icebear03.splendidenchants.command.sub

import taboolib.common.platform.command.SimpleCommandBody
import taboolib.common.platform.command.subCommand
import world.icebear03.splendidenchants.command.CommandExecutor
import world.icebear03.splendidenchants.command.CommandHandler

/**
 * SplendidEnchants
 * world.icebear03.splendidenchants.command.sub.CommandRandom
 *
 * @author Mical
 * @since 2023/7/12 00:42
 */
object CommandRandom : CommandExecutor {

    override val command: SimpleCommandBody = subCommand {

    }

    override val name: String = "random"

    init {
        CommandHandler.sub[name] = this
    }
}