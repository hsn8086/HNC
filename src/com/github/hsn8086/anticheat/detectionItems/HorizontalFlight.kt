package com.github.hsn8086.anticheat.detectionItems

import com.github.hsn8086.anticheat.DetectionItem
import com.github.hsn8086.data.PlayerData
import com.github.hsn8086.data.PluginConfig
import org.bukkit.GameMode
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import kotlin.math.abs
import kotlin.math.floor

/**
 * @author hsn
 * @version 1.0
 * @since 22-07-12 3:55
 */
class HorizontalFlight : DetectionItem() {
    init {
        suspicionLevel = PluginConfig.detectionItemFlightSuspicionLevel
        if (PluginConfig.detectionItemFlightCheckHorizontalFlight) {
            enable()
        } else {
            disable()
        }
        type = "movement"
    }

    override fun run(player: Player, data: Any): Boolean {
        if (player.gameMode == GameMode.CREATIVE) {
            return false
        }
        val e: PlayerMoveEvent = (data as PlayerMoveEvent?)!!
        val fromX: Double = e.from.x
        val fromY: Double = e.from.y
        val fromZ: Double = e.from.z
        val toX: Double = e.to.x
        val toY: Double = e.to.y
        val toZ: Double = e.to.z
        if (abs(toX - fromX) < 0.1 && abs(toZ - fromY) < 0.1) {
            return false
        }
        val world: World = player.world
        var emptyBlock= 0
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block =
                    world.getBlockAt((fromX.toInt()) + i0 - 1, floor(fromY - 1).toInt(), (fromZ+i- 1).toInt())
                if (block.isEmpty) {
                    emptyBlock++
                }
            }
        }
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block =
                    world.getBlockAt((fromX.toInt()) + i0 - 1, floor(fromY - 2).toInt(), (fromZ.toInt()) + i - 1)
                if (block.isEmpty) {
                    emptyBlock++
                }
            }
        }
        PlayerData.flightTime.putIfAbsent(player.name, 0)
        if (PlayerData.flightTime[player.name]!! > 0) {
            PlayerData.flightTime[player.name] = PlayerData.flightTime[player.name]!! - 1
        }
        if (emptyBlock == 18) {
            return if (abs(toY - fromY) < 0.1) {
                PlayerData.flightTime[player.name] = PlayerData.flightTime[player.name]!! + 3
                PlayerData.flightTime[player.name]!! > 30
            } else {
                false
            }
        }
        return false
    }
}