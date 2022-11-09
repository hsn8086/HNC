package com.github.hsn8086.event

import com.github.hsn8086.data.PlayerData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class QuitEvent : Listener {
    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent?) {
        PlayerData.removePlayer(e!!.player)
    }
}