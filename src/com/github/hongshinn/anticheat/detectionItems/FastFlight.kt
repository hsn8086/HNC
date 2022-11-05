package com.github.hongshinn.anticheat.detectionItems

import com.github.hongshinn.anticheat.DetectionItem
import com.github.hongshinn.data.PlayerData
import com.github.hongshinn.data.PluginConfig
import org.bukkit.GameMode
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import kotlin.math.floor

/**
 * @author hsn
 * @version 1.0
 * @since 22-07-12 8:16
 */
class FastFlight : DetectionItem() {
    init {
        suspicionLevel = PluginConfig.detectionItemFlightSuspicionLevel
        if (PluginConfig.detectionItemFlightCheckFastFlight) {
            enable()
        } else {
            disable()
        }
        type = "movement"
    }

    override fun run(player: Player?, data: Any?): Boolean {
        if ((player?.gameMode == GameMode.CREATIVE)) {
            return false
        }
        val e: PlayerMoveEvent? = data as PlayerMoveEvent?
        val fromX: Double = e?.from?.x!!
        val fromY: Double = e.from.y
        val fromZ: Double = e.from.z
        val toY: Double = e.to.y
        //TODO:鞘翅
        val world: World? = player?.world
        var emptyBlock = 0
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block? =
                    world?.getBlockAt((fromX.toInt()) + i0 - 1, floor(fromY - 1).toInt(), (fromZ.toInt()) + i - 1)
                if (block != null) {
                    if (block.isEmpty) {
                        emptyBlock++
                    }
                }
            }
        }
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block? =
                    world?.getBlockAt((fromX.toInt()) + i0 - 1, floor(fromY - 2).toInt(), (fromZ.toInt()) + i - 1)
                if (block != null) {
                    if (block.isEmpty) {
                        emptyBlock++
                    }
                }
            }
        }
        if (player != null) {
            PlayerData.isDropping?.putIfAbsent(player.name, false)
        }
        if (emptyBlock != 18) {
            if (player != null) {
                PlayerData.isDropping?.put(player.name, false)
            }
            return false
        }

        //掉落过程中上升检测
        if (player != null) {
            if (PlayerData.isDropping?.get(player.name) == true) {
                return fromY - toY < 0
            } else {
                if (fromY - toY > 0) {
                    PlayerData.isDropping?.put(player.name, true)
                }
            }
        }
        return false
    }
}