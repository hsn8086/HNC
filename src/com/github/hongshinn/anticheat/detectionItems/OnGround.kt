package com.github.hongshinn.anticheat.detectionItems

import com.github.hongshinn.anticheat.DetectionItem
import com.github.hongshinn.data.PlayerData
import com.github.hongshinn.data.PluginConfig
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import java.util.function.BiFunction
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * @author hsn
 * @version 1.0
 * @since 7/14/2022 5:47 下午
 */
class OnGround : DetectionItem() {
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
        val e: PlayerMoveEvent? = data as PlayerMoveEvent?
        val world: World? = player?.world
        val fromX: Double? = e?.from?.x
        val fromY: Double? = e?.from?.y
        val fromZ: Double ? = e?.from?.z
        val toX: Double? = e?.to?.x
        val toZ: Double ? = e?.to?.z
        PlayerData.onGroundCount?.putIfAbsent(player!!.getName(), 0)
        PlayerData.onGroundCount?.compute(player!!.getName(), BiFunction<String?, Int?, Int?> compute@{ _: String?, v: Int? ->
            if (v == null) {
                return@compute 0
            } else {
                return@compute v - 1
            }
        })
        var emptyBlock: Int = 0
        val range: Double = sqrt((fromX!! - toX!!).pow(2.0) + (fromZ!! - toZ!!).pow(2.0)) * 6 / 5 + 0.31
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block? = world?.getBlockAt(
                    (fromX + (i0 - 1) * range).toInt(),
                    floor(fromY!! - 0.001).toInt(),
                    (fromZ + (i - 1) * range).toInt()
                )
                if (block!!.isEmpty) {
                    emptyBlock++
                }
            }
        }
        if (emptyBlock == 9 && player!!.isOnGround()) {
            PlayerData.onGroundCount?.compute(player.name, BiFunction<String?, Int?, Int?> compute@{ k: String?, v: Int? ->
                if (v == null) {
                    return@compute 1
                } else {
                    return@compute v + 5
                }
            })
        }
        return PlayerData.onGroundCount?.get(player!!.name)!! >= 5
    }
}