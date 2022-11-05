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
    private var determination: Boolean = false
    @EventHandler
    fun onPlayerMove(e: PlayerMoveEvent?) {
        determination = AntiCheatManager.check(e!!.player, e as Any, "movement")
        if (abs(e.to.x - e.from.x) > 0.1 || abs(
                e.to.z - e.from.z
            ) > 0.1
        ) {
            PlayerData.suspiciousLevel.putIfAbsent(e.player.name, 0)
            if (PlayerData.suspiciousLevel[e.player.name]!! > 0) {
                PlayerData.addSuspiciousLevel(e.player, -PluginConfig.mainSuspicionLevelDecreaseRate)
            }
            if (PlayerData.suspiciousLevel[e.player.name]!! > PluginConfig.mainSuspicionLevelBacktracking) {
                if (determination) {
                    e.isCancelled = true
                }
            }
        }
    }
}