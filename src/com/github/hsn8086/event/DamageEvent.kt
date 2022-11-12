package com.github.hsn8086.event

import com.github.hsn8086.anticheat.AntiCheatManager
import com.github.hsn8086.data.PlayerData
import com.github.hsn8086.data.PluginConfig
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class DamageEvent : Listener {
    @EventHandler
    fun onPlayerClick(e: EntityDamageByEntityEvent) {
        if (e.damager is Player) {
            val determination = AntiCheatManager.check(e.damager as Player, e as Any, "damage")
            PlayerData.suspiciousLevel.putIfAbsent(e.damager.name,0)
            if (PlayerData.suspiciousLevel[e.damager.name]!! > PluginConfig.mainSuspicionLevelBacktracking) {
                if (determination) {
                    e.isCancelled = true
                }
            }
        }

    }
}