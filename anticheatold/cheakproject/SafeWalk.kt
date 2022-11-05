package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object SafeWalk {
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
		 * --------------safewalk--------------
		 */if (Global.safewalk && !player.isSneaking() && (gm.getValue() == 1 == false) && (gm.getValue() == 3 == false
                    ) && (eb2 == 9)
        ) { // safewalk���
            val block: Block? = world.getBlockAt(bx, by - 1, bz)
            if ((block.isEmpty() && (eb < 9) && (aspeedx == 0.0 || aspeedy == 0.0) && (aspeedx > 0.1 || aspeedy > 0.1)
                        && (Ptlm.gettagvalueint(player, "jumptime") === 0))
            ) {
                Ptlm.tagvalueadd(player, "emptytime", 2)
                // player.sendMessage(Ptlm.gettagvalue(player, "emptytime"));
            } else if (Ptlm.gettagvalueint(player, "emptytime") > 0) {
                Ptlm.settagvalueint(player, "emptytime", Ptlm.gettagvalueint(player, "emptytime") - 1)
            }
            if (Ptlm.gettagvalueint(player, "emptytime") > 10) {
                return true
                // Tool.ban(player, "safewalk", 3);
            }
        } else if (Ptlm.gettagvalueint(player, "emptytime") > 0.2) {
            Ptlm.settagvalueint(player, "emptytime", Ptlm.gettagvalueint(player, "emptytime") - 5)
        }
        return false
    }
}