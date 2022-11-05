package com.github.hongshinn.event

import com.github.hongshinn.data.PlayerData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class QuitEvent : Listener {
    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent?) {
        PlayerData.removePlayer(e!!.player)
    }
}