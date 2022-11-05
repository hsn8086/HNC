package com.github.hongshinn.event

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class JoinEvent : Listener {
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent?) {
        val player: Player = e!!.player
        object : BukkitRunnable(
        ) {
            override fun run() {}
        }.runTaskTimer(JavaPlugin.getProvidingPlugin(javaClass), 0L, 4L)
    }
}