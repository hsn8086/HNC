package com.github.hongshinn.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatEvent : Listener {
    @EventHandler
    fun onPlayerChat(e: AsyncPlayerChatEvent?) {
        e?.player?.sendMessage(e.getMessage())

        // double cps=Ptlm.gettagvalueint(player, "cps");
        // player.sendMessage("cps:"+Double.toString(cps*5));
    }
}