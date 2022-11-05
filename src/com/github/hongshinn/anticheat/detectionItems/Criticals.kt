package com.github.hongshinn.anticheat.detectionItems

import com.github.hongshinn.anticheat.DetectionItem
import com.github.hongshinn.data.PlayerData
import com.github.hongshinn.data.PluginConfig
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import kotlin.math.abs
import kotlin.math.floor

/**
 * @author hsn
 * @version 1.0
 * @since 7/14/2022 2:34 下午
 */
class Criticals : DetectionItem() {
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
        val fromZ: Double? = e?.from?.z
        val toY: Double? = e?.to?.y
        var emptyBlock: Int = 0
        var bodyEmptyBlock: Int = 0
        var overheadEmptyBlock: Int = 0
        if (world != null) {
            if (fromX?.let {
                    fromY?.let { it1 -> floor(it1).toInt() }
                        ?.let { it2 -> fromZ?.let { it1 -> world.getBlockAt(it.toInt(), it2, it1.toInt()).isLiquid } }
                } == true) {
                return false
            }
        }
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block? = fromX?.plus((i0 - 1) * 0.301)?.let {
                    fromY?.minus(0.001)?.let { it1 -> floor(it1).toInt() }?.let { it2 ->
                        fromZ?.plus((i - 1) * 0.301)?.let { it1 ->
                            world?.getBlockAt(
                                it.toInt(),
                                it2,
                                it1.toInt()
                            )
                        }
                    }
                }
                if (block != null) {
                    if (block.isEmpty) {
                        emptyBlock++
                    }
                }
            }
        }
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block? = fromX?.plus((i0 - 1) * 0.301)?.let {
                    fromY?.let { floor(it).toInt() }?.let { it1 ->
                        fromZ?.plus((i - 1) * 0.301)?.let { it2 ->
                            world?.getBlockAt(
                                it.toInt(),
                                it1,
                                it2.toInt()
                            )
                        }
                    }
                }
                if (block != null) {
                    if (block.isEmpty) {
                        bodyEmptyBlock++
                    }
                }
            }
        }
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block? = fromX?.plus((i0 - 1) * 0.301)?.let {
                    fromY?.plus(2.1)?.let { it1 -> floor(it1).toInt() }?.let { it2 ->
                        fromZ?.plus((i - 1) * 0.301)?.let { it1 ->
                            world?.getBlockAt(
                                it.toInt(),
                                it2,
                                it1.toInt()
                            )
                        }
                    }
                }
                if (block != null) {
                    if (block.isEmpty) {
                        overheadEmptyBlock++
                    }
                }
            }
        }
        if (player != null) {
            PlayerData.groundPosition?.putIfAbsent(player.name, fromY)
        }
        if (emptyBlock == 0 && bodyEmptyBlock == 9) {
            if (player != null) {
                PlayerData.groundPosition?.set(player.name, fromY)
            }
        }
        var jumpHeight: Double = fromY?: - PlayerData.groundPosition?.get(player?.name)!!
        if (jumpHeight < 0) {

            PlayerData.groundPosition?.set(player?.name, fromY)

            jumpHeight = 0.0
        }
        //player.sendMessage("jumpHeight: " + jumpHeight + " body: " + bodyEmptyBlock + " emptyBlock: " + emptyBlock + " overhead: " + overheadEmptyBlock+" fromY: "+fromY+" toY: "+toY);
        if ((jumpHeight > 0.0548 && jumpHeight < 0.0551) || (jumpHeight > 0.1999 && jumpHeight < 0.2001)) {
            //我已经被这个整无语了,为什么这里检测不到头顶有方块啊?为什么下落之后有0.0548格的高度啊?
            return false
        }
        if ((jumpHeight < 0.8) && (jumpHeight > 0) && (overheadEmptyBlock == 9) && (abs(fromY!! -toY!!) < 0.1) && (abs(
                fromY - toY
            ) > 0)
        ) {
            return true
        }
        return false
    }
}


