package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object WallClimb {
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
            if (Global.wallclimb && !player.isSneaking() && (gm.getValue() == 1 == false
                        ) && (gm.getValue() == 3 == false)
            ) { // wallclimb���
                val block: Block? = world.getBlockAt(bx, by - 1, bz)
                val block2: Block? = world.getBlockAt(bx, by - 2, bz)
                if (block.isEmpty() && block2.isEmpty() && (eb < 9) && (aspeedy > 0.15) && (aspeedy < 0.3)) {
                    Ptlm.tagvalueadd(player, "climbtime", 2)
                    // player.sendMessage(Ptlm.gettagvalue(player, "emptytime"));
                } else if (Ptlm.gettagvalueint(player, "climbtime") > 0) {
                    Ptlm.settagvalueint(player, "climbtime", Ptlm.gettagvalueint(player, "climbtime") - 1)
                }
                if (Ptlm.gettagvalueint(player, "climbtime") > 30) {
                    return true
                }
            } else if (Ptlm.gettagvalueint(player, "climbtime") > 0) {
                Ptlm.settagvalueint(player, "climbtime", Ptlm.gettagvalueint(player, "climbtime") - 5)
            }
        }
        return false
    }
}