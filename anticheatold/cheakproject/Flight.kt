package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object Flight {
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


        //------dataloading------
        var eb2: Int = 0
        var eb3: Int = 0
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block? = world.getBlockAt(bx + i0 - 1, Math.floor(ato.getY() - 1) as Int, bz + i - 1)
                if (block.isEmpty()) {
                    eb2++
                    // player.sendMessage("empty");
                }
            }
        }
        for (i in 0..2) {
            for (i0 in 0..2) {
                val block: Block? = world.getBlockAt(bx + i0 - 1, Math.floor(ato.getY() - 2) as Int, bz + i - 1)
                if (block.isEmpty()) {
                    eb3++
                    // player.sendMessage("empty");
                }
            }
        }
        //-------------------------
        if (ishurt == 0) {
            /*
			 * --------------flight2--------------
			 */
            //player.sendMessage(String.valueOf(eb)+" "+String.valueOf(eb2)+" "+String.valueOf(eb3));
            if ((eb == 9) && (eb2 == 9) && (eb3 == 9) && (Ptlm.gettagvalueint(player, "falltime") === 0)) {
                if (Ptlm.gettagvalueint(player, "flytime") < 300) {
                    Ptlm.tagvalueadd(player, "flytime", 4)
                }
                if (Ptlm.gettagvalueint(player, "flytime") > 60) {
                    return true
                }
            } else {
                Ptlm.tagvalueadd(player, "flytime", -2)
            }
        } else if ((Ptlm.gettagvalueint(player, "lastyspeed") > 0) || (Ptlm.gettagvalueint(
                player,
                "lastyspeed"
            ) - aspeedy < 0.1)
        ) {
            Ptlm.tagvalueadd(player, "flytime2", 1)
            if (Ptlm.gettagvalueint(player, "flytime2") > 20) {
                return true
            }
        }
        /*
		 * --------------Horizontalflight--------------
		 */if ((Math.floor(ato.getY() * 3) === Math.floor(afrom.getY() * 3)) && (eb == 9) && (eb2 == 9) && (eb3 == 9) && Global.flight) {
            if (Ptlm.gettagvalueint(player, "lyp") > 10) {
                return true
            }
            Ptlm.tagvalueadd(player, "lyp", 1)
        } else {
            Ptlm.settagvalueint(player, "lyp", 0)
        }
        return false
    }
}