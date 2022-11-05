package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object FallingSpeed {
    fun main(pd: PlayerDataOld?): Boolean? {
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
        val ato: Location? = pd.ato
        val ft: Double = Ptlm.gettagvalueint(player, "falltime")
        val fsbc: String? = Gtc.gtc(Global.fslevel)
        if (!player.isSneaking() && (gm.getValue() == 1 == false) && (gm.getValue() == 3 == false)) {
            if (Global.fallingspeed && world.getBlockAt(bx, by, bz).isEmpty() && (wb == 0)) {
                if (aspeedy < -Global.maxfallingspeed) { // ������
                    return true
                    // Tool.ban(player, "�����ٶȹ���", Global.fsgrade);
                } else if (aspeedy < 135 / (ft + 30) - 5 && ato.getY() > 0) {
                    // Ptlm.settagvalueint(player, "falll",Ptlm.gettagvalueint(player, "falll")+20);
                    Ptlm.tagvalueadd(player, "fjtime", 6)
                    if (Ptlm.gettagvalueint(player, "fjtime") > 42) {
                        return true
                    }
                    Ptlm.settagvalueint(player, "falltime", 0)
                } else if ((ft > 8) && (aspeedy > 135 / (ft + 30) - 2) && (ato.getY() > 0)) {
                    if (wb == 0) {
                        // Ptlm.settagvalueint(player, "falll",Ptlm.gettagvalueint(player, "falll")+20);
                        Ptlm.settagvalueint(player, "falltime", 0)
                        return true
                    }
                }
            }
        }
        if (Ptlm.gettagvalueint(player, "falll") > 0) {
            Ptlm.settagvalueint(player, "falll", Ptlm.gettagvalueint(player, "falll") - 1)
        }
        if (Ptlm.gettagvalueint(player, "falll") > 80) {
        }
        return false
    }
}