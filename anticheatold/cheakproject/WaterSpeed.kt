package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object WaterSpeed {
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
		 * --------------waterspeed--------------
		 */if (Global.waterspeed && !player.isSneaking() && (gm.getValue() == 1 == false) && (gm.getValue() == 3 == false)) { // waterspeed
            if ((Math.abs(aspeedx) > 0.13) || (Math.abs(aspeedz) > 0.13) || (Math.abs(aspeedy) > 0.23)) {
                if (((world.getBlockAt(bx, by + 1, bz).isLiquid() || world.getBlockAt(bx, by, bz).isLiquid())
                            && ishurt == 0)
                ) {
                    Ptlm.tagvalueadd(player, "wtstime", 2)
                    if (Ptlm.gettagvalueint(player, "wtstime") > 20) {
                        return true
                    }
                }
            }
        }
        return false
    }
}