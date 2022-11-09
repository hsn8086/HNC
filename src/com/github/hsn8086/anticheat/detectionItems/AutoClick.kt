package com.github.hsn8086.anticheat.detectionItems

import com.github.hsn8086.anticheat.DetectionItem
import com.github.hsn8086.data.PluginConfig
import org.bukkit.entity.Player
import java.util.function.Consumer
import kotlin.math.max

/**
 * @author hsn
 * @version 1.0
 * @since 22-07-13 7:23
 */
class AutoClick : DetectionItem() {
    private var cpsCount: HashMap<String, ArrayList<Long>> = HashMap()
    private var cpsRecord: HashMap<String, MutableList<Int>> = HashMap()
    init {
        suspicionLevel = PluginConfig.detectionItemFlightSuspicionLevel
        if (PluginConfig.detectionItemFlightCheckFastFlight) {
            enable()
        } else {
            disable()
        }
        type = "click"
    }

    override fun run(player: Player, data: Any): Boolean {

        cpsCount.putIfAbsent(player.name, ArrayList())

        val nowTime: Long = System.currentTimeMillis()

        cpsCount.computeIfPresent(player.name) { _: String, v: ArrayList<Long> ->
            val removeList: ArrayList<Long> = ArrayList()
            v.forEach(Consumer { a: Long ->

                if (a <= nowTime - 1000) {
                    removeList.add(a)
                }
            })
            removeList.forEach(Consumer { o: Long ->
                v.remove(o)
            })
            v.add(nowTime)
            v
        }

        val cps: Int = cpsCount[player.name]!!.size

        cpsRecord.putIfAbsent(player.name, ArrayList())


        cpsRecord.computeIfPresent(player.name) { _: String, v: MutableList<Int> ->
            v.add(cps)

            val v2 = v.subList(max(0, v.size - 20), v.size)

            v2
        }


        return cps / (cpsRecord[player.name]!!.size + 0.75) > PluginConfig.detectionItemCombatMaxCPS

    }
}