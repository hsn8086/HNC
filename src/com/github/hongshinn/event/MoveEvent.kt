package com.github.hongshinn.event

import com.github.hongshinn.anticheat.AntiCheatManager
import com.github.hongshinn.data.PlayerData
import com.github.hongshinn.data.PluginConfig
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import kotlin.math.abs

/**
 * @author hsn
 */
class MoveEvent : Listener {
    var tempHashMap: HashMap<String?, Any?>? = HashMap()
    private var determination: Boolean = false
    @EventHandler
    fun onPlayerMove(e: PlayerMoveEvent?) {
        determination = AntiCheatManager.check(e!!.getPlayer(), e as Any?, "movement")
        if (abs(e.getTo().x - e.getFrom().x) > 0.1 || abs(
                e.getTo().z - e.getFrom().z
            ) > 0.1
        ) {
            PlayerData.suspiciousLevel!!.putIfAbsent(e.getPlayer().name, 0)
            if (PlayerData.suspiciousLevel!![e.player.name]!! > 0) {
                PlayerData.addSuspiciousLevel(e.getPlayer(), -PluginConfig.mainSuspicionLevelDecreaseRate)
            }
            if (PlayerData.suspiciousLevel!![e.getPlayer().name]!! > PluginConfig.mainSuspicionLevelBacktracking) {
                if (determination) {
                    e.setCancelled(true)
                }
            }
        }
    }
}