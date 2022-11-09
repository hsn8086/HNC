package com.github.hsn8086.anticheat.detectionItems

import com.github.hsn8086.anticheat.DetectionItem
import com.github.hsn8086.data.PlayerData
import com.github.hsn8086.data.PluginConfig
import org.bukkit.GameMode
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import kotlin.math.floor

/**
 * @author hsn
 * @version 1.0
 * @since 22-07-13 4:01
 */
class HighJump : DetectionItem() {
    init {
        suspicionLevel = PluginConfig.detectionItemJumpSuspicionLevel
        if (PluginConfig.detectionItemJumpCheckHeight) {
            enable()
        } else {
            disable()
        }
        type = "movement"
    }

    override fun run(player: Player, data: Any): Boolean {

            if ((player.gameMode == GameMode.CREATIVE)) {
                return false
            }

        var emptyBlock = 0
        val e: PlayerMoveEvent = (data as PlayerMoveEvent?)!!
        val world: World = player.world
        val fromX: Double = e.from.x
        val fromY: Double = e.from.y
        val fromZ: Double = e.from.z
        val toY: Double = e.to.y

        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block? = world.getBlockAt(
                    (fromX.plus((i0 - 1) * 0.26)).toInt(),
                    floor(fromY - 0.1).toInt(),
                    (fromZ.plus((i - 1) * 0.26)).toInt()
                )
                if (block!!.isEmpty) {
                    emptyBlock++
                }
            }
        }
        PlayerData.isJumping.putIfAbsent(player.name, false)
        if (!PlayerData.isJumping[player.name]!! && (emptyBlock == 9) && (fromY - toY < 0)) {
            PlayerData.isJumping[player.name] = true
            PlayerData.takeOffPosition[player.name] = fromY
        }
        if (fromY - toY >= 0 && PlayerData.isJumping[player.name] == true) {
            PlayerData.isJumping[player.name] = false
            PlayerData.takeOffPosition.putIfAbsent(player.name, fromY)
            val jumpHeight: Double = fromY - PlayerData.takeOffPosition[player.name]!!
            return jumpHeight * 100 > PluginConfig.detectionItemJumpMaxHeight
        }
        return false
    }
}