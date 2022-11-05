package com.github.hongshinn.event

import com.github.hongshinn.anticheat.AntiCheatManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

/**
 * @author ASUS
 */
class ClickEvent : Listener {
    @EventHandler
    fun onPlayerClick(e: PlayerInteractEvent?) {
        if (e != null) {
            AntiCheatManager.check(e.player, e as Any?, "click")
        }
    }
}