package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object HighJump {
    fun main(pd: PlayerDataOld?): Boolean {
        /*
		 * --------------hightjump--------------
		 */
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
        var jumphigh: Int
        if (ishurt == 0) {
            if (aspeedy > 0 && wb == 0) {
                Ptlm.settagvalueint(player, "jumptime", Ptlm.gettagvalueint(player, "jumptime") + 1)
                if (Ptlm.gettagvalueint(player, "jumpy") === 0) {
                    val jy: Int = (afrom.getY() * 100) as Int
                    Ptlm.settagvalueint(player, "jumpy", jy)
                }
            } else {
                Ptlm.settagvalueint(player, "jumptime", 0)
                jumphigh = (afrom.getY() * 100 - Ptlm.gettagvalueint(player, "jumpy")) as Int
                if (Ptlm.gettagvalueint(player, "jumpy") === 0) {
                    jumphigh = 0
                }
                if (jumphigh > 0) {
                    if (Criticals.jumpcriticals(pd, jumphigh)) {
                        return true
                    }
                    // player.sendMessage(String.valueOf(jumphigh));
                }
                Ptlm.settagvalueint(player, "jumpy", 0)
                if (jumphigh > 130) {
                    if (Global.jumphight) {
                        Ptlm.tagvalueadd(player, "hjumpc", 1)
                        if (Ptlm.gettagvalueint(player, "hjumpc") > 2) {
                            return true
                        }
                    }
                } else if (Ptlm.gettagvalueint(player, "hjumpc") > 0) {
                    Ptlm.tagvalueadd(player, "hjumpc", -1)
                }
                if (jumphigh > Global.maxjumphight) {
                    if (Global.jumphight) {
                        return true
                    }
                }
            }
        }
        return false
    }
}