package world.icebear03.splendidenchants.listener.packet

import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.nms.MinecraftVersion
import taboolib.module.nms.PacketSendEvent
import taboolib.platform.util.isAir
import world.icebear03.splendidenchants.api.nms.NMS
import world.icebear03.splendidenchants.enchant.EnchantDisplayer

object PacketWindowItems {

    @SubscribeEvent(priority = EventPriority.MONITOR)
    fun e(e: PacketSendEvent) {
        if (e.packet.name == "PacketPlayOutWindowItems") {
            val field = when (MinecraftVersion.major) {
                8 -> "b" // 1.16 -> b
                in 9..12 -> "c" // 1.17, 1.18, 1.19, 1.20 -> c
                else -> error("Unsupported version.") // Unsupported
            }
            val slots = e.packet.read<List<Any>>(field)!!.toMutableList()
            for (i in slots.indices) {
                val bkItem = NMS.INSTANCE.toBukkitItemStack(slots[i])
                if (bkItem.isAir) continue
                val nmsItem = NMS.INSTANCE.toNMSItemStack(EnchantDisplayer.display(bkItem, e.player))
                slots[i] = nmsItem
            }
            e.packet.write(field, slots)
        }
    }
}