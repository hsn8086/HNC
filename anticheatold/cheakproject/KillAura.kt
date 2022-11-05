package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object KillAura {
    fun main(pd: PlayerDataOld?): Boolean {
        val player: Player? = pd.player
        val gm: GameMode? = pd.gm
        val world: World? = pd.world
        val aspeedy: Double = pd.aspeedy
        val aspeedx: Double = pd.aspeedx
        val aspeedz: Double = pd.aspeedz
        val bx: Int = pd.bx
        val by: Int = pd.by
        val bz: Int = pd.bz
        val wb: Int = pd.wb
        val eb: Int = pd.eb
        val eb1: Int = pd.eb1
        val eb2: Int = pd.eb2
        val eb3: Int = pd.eb3
        val ishurt: Int = pd.ishurt
        val ato: Location? = pd.ato
        val afrom: Location? = pd.afrom
        /*
		 * --------------liquidwalk--------------
		 */if (Global.liquidwalk && (wb == 9) && (eb1 == 9)) {
            return true
        }
        return false
    }
}