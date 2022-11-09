package com.github.hsn8086.anticheat

import org.bukkit.entity.Player

/**
 * @author hsn
 * @version 1.0
 * @since 22-07-12 2:13
 */
object AntiCheatManager {
    var detectionItems: ArrayList<DetectionItem> = ArrayList()
    fun registerDetectionItem(detectionItem: DetectionItem) {
        detectionItems.add(detectionItem)
    }

    private var isSuspicion: Boolean = false
    fun clearDetectionItems() {
        detectionItems.clear()
    }

    fun check(player: Player, data: Any, type: String): Boolean {
        var determination = false
        for (detectionItem: DetectionItem in detectionItems) {
            if ((detectionItem.type == type)) {
                if (detectionItem.isEnable) {
                    if (detectionItem.whiteList?.contains(player.name) == true) {
                        continue
                    }
                    isSuspicion = detectionItem.call(player, data)
                    determination = determination || isSuspicion
                }
            }
        }
        return determination
    }
}