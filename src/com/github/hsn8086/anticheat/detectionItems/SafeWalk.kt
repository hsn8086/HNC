package com.github.hsn8086.anticheat.detectionItems

import com.github.hsn8086.anticheat.DetectionItem
import com.github.hsn8086.data.PluginConfig
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.util.Vector
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * @author hsn
 * @version 1.0
 * @since 7/25/2022 10:24 下午
 */
class SafeWalk : DetectionItem() {
    private var lastSpeed: HashMap<String, List<Double>> = HashMap()

    init {
        suspicionLevel = PluginConfig.detectionItemWalkSuspicionLevel
        if (PluginConfig.detectionItemWalkCheckSafeWalk) {
            enable()
        } else {
            disable()
        }
        type = "movement"
    }

    override fun run(player: Player, data: Any): Boolean {
        val e: PlayerMoveEvent = data as PlayerMoveEvent
        val fromX: Double = e.from.x
        val fromY: Double = e.from.y
        val fromZ: Double = e.from.z
        val toX: Double = e.to.x
        val toY: Double = e.to.y
        val toZ: Double = e.to.z
        var overheadEmptyBlock = 0
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block = e.player.world.getBlockAt(
                    (fromX + (i0 - 1) * 0.301).toInt(),
                    floor(fromY + 2.1).toInt(),
                    (fromZ + (i - 1) * 0.301).toInt()
                )
                if (block.isEmpty) {
                    overheadEmptyBlock++
                }
            }
        }

        lastSpeed.putIfAbsent(player.name, listOf<Double>(0.0, 0.0))
        //e.player.sendMessage(e.player.location.direction.toString())
        val block: Block = e.player.world.getBlockAt(
            (fromX).toInt(),
            floor(fromY-0.01).toInt(),
            (fromZ).toInt()
        )
        if (block.isEmpty) {
            e.player.sendMessage(

                sqrt(
                    (toX - fromX - lastSpeed[player.name]!![0]).pow(2) +
                            (toZ - fromZ - lastSpeed[player.name]!![1]).pow(2)
                ).toString()

            )



        }

        lastSpeed[player.name] = listOf(toX - fromX, toZ - fromZ)
        return false
    }
}