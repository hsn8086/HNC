package com.github.hongshinn.anticheat.detectionItems

import com.github.hongshinn.Utils
import com.github.hongshinn.anticheat.DetectionItem
import com.github.hongshinn.data.PlayerData
import com.github.hongshinn.data.PluginConfig
import org.bukkit.entity.Player
import java.util.function.BiFunction
import java.util.function.Consumer
import kotlin.math.max

/**
 * @author hsn
 * @version 1.0
 * @since 22-07-13 7:23
 */
class AutoClick : DetectionItem() {
    init {
        suspicionLevel = PluginConfig.detectionItemFlightSuspicionLevel
        if (PluginConfig.detectionItemFlightCheckFastFlight) {
            enable()
        } else {
            disable()
        }
        type = "click"
    }

    override fun run(player: Player?, data: Any?): Boolean {
        if (player != null) {
            PlayerData.cpsCount?.putIfAbsent(player.name, ArrayList())
        }
        val nowTime: Long = System.currentTimeMillis()
        if (player != null) {
            PlayerData.cpsCount?.computeIfPresent(player.name) { _: String?, v: ArrayList<Long?>? ->
                val removeList: ArrayList<Long?> = ArrayList()
                v?.forEach(Consumer { a: Long? ->
                    if (a != null) {
                        if (a <= nowTime - 1000) {
                            removeList.add(a)
                        }
                    }
                })
                removeList.forEach(Consumer { o: Long? ->
                    v?.remove(o)
                })
                v?.add(nowTime)
                v
            }
        }
        val cps: Int? = PlayerData.cpsCount?.get(player?.name)?.size
        if (player != null) {
            PlayerData.cpsRecord?.putIfAbsent(player.name, ArrayList())
        }
        if (player != null) {
            PlayerData.cpsRecord?.computeIfPresent(player.name) { _: String?, v: MutableList<Int?>? ->
                v?.add(cps)

                val v2 = v?.subList(max(0, v.size - 20), v.size)

                v2
            }
        }
        if (cps != null) {
            if (player != null) {
                return cps / (Utils.varianceInt(PlayerData.cpsRecord?.get(player.name)) + 0.75) > PluginConfig.detectionItemCombatMaxCPS
            }
        }

        return false
    }
}