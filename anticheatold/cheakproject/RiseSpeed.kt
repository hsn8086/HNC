package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object RiseSpeed {
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
        /*
		 * --------------�����ٶ�--------------
		 */if (aspeedy > 0.45) { // �����ٶ�
            val block: Block? = world.getBlockAt(bx, by - 1, bz)
            if ((gm.getValue() == 1 == false) && (gm.getValue() == 3 == false) && (Ptlm.gettagvalueint(
                    player,
                    "ishurt"
                ) == 1 == false)
            ) {
                // player.setBanned(true);
                // player.kickPlayer("[hnc������]\n[ma-2]�ٶȹ���");
                val ys: Int = aspeedy.toInt() * 100
                if (Ptlm.gettagvalueint(player, "lastyspeed") === ys && Ptlm.gettagvalueint(player, "llspeed") === ys) {
                    Ptlm.tagvalueadd(player, "guspeed", 6)
                    if (Ptlm.gettagvalueint(player, "guspeed") > 60) {
                        return true
                    }
                } else {
                    Ptlm.tagvalueadd(player, "guspeed", -1)
                }
                Ptlm.settagvalueint(player, "llspeed", Ptlm.gettagvalueint(player, "lastyspeed"))
                Ptlm.settagvalueint(player, "lastyspeed", ys)
            }
        }
        return false
    }
}