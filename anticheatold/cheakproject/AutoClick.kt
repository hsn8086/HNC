package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object AutoClick {
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
        Ptlm.settagvalueint(player, "cps", Ptlm.gettagvalueint(player, "cps") + 1)
        if (Ptlm.gettagvalueint(player, "cps") > 26) {
            return true
        }
        if (Ptlm.gettagvalueint(player, "cpscount") > 50) {
            return true
        }
        return false
    }
}