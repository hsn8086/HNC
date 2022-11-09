package com.github.hsn8086.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatEvent : Listener {
    @EventHandler
    fun onPlayerChat(e: AsyncPlayerChatEvent) {
        e.player.sendMessage(e.message)
    }
}