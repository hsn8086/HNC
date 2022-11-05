package com.github.hongshinn.anticheatold

import com.github.hongshinn.anticheatold.cheakproject.AutoClick

object ClickCheck {
    val AUTOCLICK: Int = 0
    fun Cheak(type: Int, pd: PlayerDataOld?): Boolean {
        var adds: Boolean = false
        when (type) {
            0 -> if (true) {
                adds = AutoClick.main(pd)
            }
        }
        return false
    }
}