package com.github.hsn8086.anticheat.detectionItems

import com.github.hsn8086.anticheat.DetectionItem
import com.github.hsn8086.data.PlayerData
import com.github.hsn8086.data.PluginConfig
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import java.util.function.BiFunction

/**
 * @author hsn
 * @version 1.0
 * @since 7/14/2022 6:35 下午
 */
class DropSpeed : DetectionItem() {
    init {
        suspicionLevel = PluginConfig.detectionItemFlightSuspicionLevel
        if (PluginConfig.detectionItemFlightCheckFastFlight) {
            enable()
        } else {
            disable()
        }
        type = "movement"
    }

    override fun run(player: Player, data: Any): Boolean {
        val e: PlayerMoveEvent = data as PlayerMoveEvent
        val fromY: Double = e.from.y
        val toY: Double = e.to.y
        val speed: Double = toY - fromY
        PlayerData.isDropping.putIfAbsent(player.name, false)
        if (PlayerData.isDropping[player.name] == true) {
            PlayerData.dropTime.compute(player.name, BiFunction<String?, Int?, Int?> compute@{ _: String?, v: Int? ->
                if (v == null) {
                    return@compute 1
                } else {
                    return@compute v + 1
                }
            })
        } else {
            PlayerData.dropTime.compute(player.name) { _: String?, _: Int? -> 0 }
        }
        val dropTime: Int = PlayerData.dropTime.getOrDefault(player.name, 0)
        if (PlayerData.isDropping[player.name] == true) {
            return if (dropTime < 200) {
                speed < 136.0 / (dropTime + 33) - 4.52 || speed > 136.0 / (dropTime + 33) - 3.82
            } else {
                speed < 12.0 / (dropTime + 3) - 4.3 || speed > 12.0 / (dropTime + 3) - 3.7
            }
        }
        return false
    }
}