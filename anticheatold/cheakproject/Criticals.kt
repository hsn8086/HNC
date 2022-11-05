package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object Criticals {
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
		 * --------------����--------------
		 */if (Global.criticals && !player.isSneaking() && (gm.getValue() == 1 == false) && (gm.getValue() == 3 == false)) {
            if (((Math.abs(aspeedy) > 0.01 && Math.abs(aspeedy) < 0.10)) && (wb == 0) && (eb < 9)) {
                Ptlm.tagvalueadd(player, "ctltime", 2)
            }
            if (Ptlm.gettagvalueint(player, "ctltime") > 20) {
                return true
            }
        }
        if (Ptlm.gettagvalueint(player, "ctltime") > 0) {
            Ptlm.settagvalueint(player, "ctltime", Ptlm.gettagvalueint(player, "ctltime") - 1)
        }
        return false
    }

    fun jumpcriticals(pd: PlayerDataOld?, jumphigh: Int): Boolean {
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
		 * --------------����--------------
		 */
        /*if (ishurt == 0) {
			if (Global.criticals && !player.isSneaking() && gm.getValue() == 1 == false
					&& gm.getValue() == 3 == false) {
				if (Math.abs(aspeedx) < 0.05 && Math.abs(aspeedz) < 0.05 && jumphigh < 60 && jumphigh > 0) {
					Ptlm.tagvalueadd(player, "ctltime", 2);
				}
				if (Ptlm.gettagvalueint(player, "ctltime") > 3) {
					return true;
				}

			}
			if (Ptlm.gettagvalueint(player, "ctltime") > 0) {
				Ptlm.settagvalueint(player, "ctltime", Ptlm.gettagvalueint(player, "ctltime") - 1);
			}
		}*/return false
    }
}