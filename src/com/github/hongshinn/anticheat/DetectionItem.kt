package com.github.hongshinn.anticheat

import com.github.hongshinn.data.PlayerData
import org.bukkit.entity.Player

/**
 * @author hsn
 * @version 1.0
 * @since 22-07-12 2:16
 */
open class DetectionItem {
    var isEnable: Boolean = false
    var whiteList: MutableList<String?>? = ArrayList()
    var suspicionLevel: Int = 0
    var type: String? = null
    fun enable(): Boolean {
        if (!isEnable) {
            isEnable = true
            return true
        }
        return false
    }

    fun disable(): Boolean {
        if (isEnable) {
            isEnable = false
            return true
        }
        return false
    }

    fun addToWhiteList(player: String?): Boolean {
        return if (whiteList?.contains(player) == true) {
            false
        } else {
            whiteList?.add(player)
            true
        }
    }

    fun removeFromWhiteList(player: Player?): Boolean {
        val playerName: String? = player!!.name
        return if (whiteList?.contains(playerName) == true) {
            whiteList!!.remove(playerName)
            true
        } else {
            false
        }
    }

    open fun run(player: Player?, data: Any?): Boolean {
        return false
    }

    fun call(player: Player?, data: Any?): Boolean {
        if (isEnable) {
            if (whiteList!!.contains(player!!.getName())) {
                return false
            } else {
                if (run(player, data)) {
                    PlayerData.addSuspiciousLevel(player, suspicionLevel)
                    return true
                }
            }
        }
        return false
    }
}