package com.github.hongshinn.anticheatold

import com.github.hongshinn.anticheatold.cheakproject.Reach

object DamageCheck {
    val REACH: Int = 0
    fun Cheak(type: Int, pd: PlayerDataOld?): Boolean {
        var adds: Boolean = false
        when (type) {
            0 -> if (Global.reach) {
                //pd.player.sendMessage("1");
                adds = Reach.main(pd)
            }

            else -> adds = false
        }
        return adds
    }
}