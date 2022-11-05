package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object Speed {
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
        val ishurt: Int = pd.ishurt
        val ato: Location? = pd.ato
        val afrom: Location? = pd.afrom
        if (ishurt == 0) {
            /*
			 * --------------speed--------------
			 */
            if (Math.abs(aspeedx) > 0.7 || Math.abs(aspeedz) > 0.7) { // �ٶȼ��
                if ((gm.getValue() == 1 == false) && (gm.getValue() == 3 == false) && (Ptlm.gettagvalueint(
                        player,
                        "ishurt"
                    ) == 1 == false)
                ) {
                    // player.setBanned(true);
                    Ptlm.tagvalueadd(player, "hzspeed", 6)
                    if (Ptlm.gettagvalueint(player, "hzspeed") > 60) {
                        return true
                    }
                }
            }
        }
        return false
    }
}