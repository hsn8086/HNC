package com.github.hsn8086.anticheat.detectionItems

import com.github.hsn8086.Utils
import com.github.hsn8086.anticheat.DetectionItem
import com.github.hsn8086.data.PluginConfig
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.util.Vector
import kotlin.math.abs

/**
 * @author hsn
 * @since 11/12/2022 1:24 下午
 * @version 1.0
 */
class KillAuraSimple : DetectionItem() {
    var count = HashMap<String, Int>()

    init {
        suspicionLevel = PluginConfig.detectionItemCombatKillAuraSuspicionLevel
        if (PluginConfig.detectionItemCombatKillAuraSimple) {
            enable()
        } else {
            disable()
        }
        type = "damage"
    }

    override fun run(player: Player, data: Any): Boolean {
        val e: EntityDamageByEntityEvent = (data as EntityDamageByEntityEvent)
        val world: World = player.world

        val damageVector = Vector(
            e.entity.location.x - e.damager.location.x,
            e.entity.location.y - e.damager.location.y,
            e.entity.location.z - e.damager.location.z
        )

        count.putIfAbsent(player.name, 0)
        if (count[player.name]!! > 75){
            e.isCancelled=true
        }
        if (abs(Utils.getPlaneNormalVector(e.damager.location.direction).dot(damageVector)) > 2.6) {
            if (count[player.name]!! < 100) {
                count[player.name] = count[player.name]!! + 10
            }

            return count[player.name]!! > 50
        } else {
            if (count[player.name]!! > 0) {
                count[player.name] = count[player.name]!! - 1
            }
        }

        return false
    }

}


