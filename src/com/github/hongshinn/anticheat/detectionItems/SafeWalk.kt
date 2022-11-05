package com.github.hongshinn.anticheat.detectionItems

import com.github.hongshinn.anticheat.DetectionItem
import com.github.hongshinn.data.PluginConfig
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import kotlin.math.floor

/**
 * @author hsn
 * @version 1.0
 * @since 7/25/2022 10:24 下午
 */
class SafeWalk : DetectionItem() {
    init {
        suspicionLevel = PluginConfig.detectionItemWalkSuspicionLevel
        if (PluginConfig.detectionItemWalkCheckSafeWalk) {
            enable()
        } else {
            disable()
        }
        type = "movement"
    }

    override fun run(player: Player?, data: Any?): Boolean {
        val e: PlayerMoveEvent= (data as PlayerMoveEvent)
        val fromX: Double = e.from.x
        val fromY: Double = e.from.y
        val fromZ: Double = e.from.z
        val toX: Double = e.to.x
        val toY: Double = e.to.y
        val toZ: Double = e.to.z
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block = e.player.world.getBlockAt(
                    (fromX + (i0 - 1) * 0.301).toInt(),
                    floor(fromY + 2.1).toInt(),
                    (fromZ + (i - 1) * 0.301).toInt()
                )
                if (block.isEmpty) {
                    //overheadEmptyBlock++;
                }
            }
        }
        return false
    }
}