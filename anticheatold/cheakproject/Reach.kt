package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object Reach {
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
        val Damager: Entity? = pd.Damager
        val Entity: Entity? = pd.entity
        val XRange: Double? = Math.abs(Damager.getLocation().getX() - Entity.getLocation().getX())
        val YRange: Double? = Math.abs(Damager.getLocation().getY() - Entity.getLocation().getY())
        val ZRange: Double? = Math.abs(Damager.getLocation().getZ() - Entity.getLocation().getZ())

        //Damager.sendMessage(String.valueOf(XRange) + " " + String.valueOf(YRange) + " " + String.valueOf(ZRange));
        if (Entity is Player) {
            Ptlm.settagvalueint(Entity as Player?, "ishurt", 1)
        }
        if (Damager is Player) {
            //Ptlm.settagvalueint((Player) e.getEntity(), "ishurt", 0);
        }
        if (Math.sqrt(
                Math.pow(XRange, 2) + Math.pow(
                    ZRange,
                    2
                )
            ) > Global.rcrange + Math.sqrt(
                Math.pow(Ptlm.gettagvalueint(player, "lastspeedx") / 10000, 2) + Math.pow(
                    Ptlm.gettagvalueint(player, "lastspeedy") / 10000,
                    2
                ) + Math.pow(Ptlm.gettagvalueint(player, "lastspeedz") / 10000, 2)
            ) * 0.16
        ) {
            if (Damager is Player) {
                // Tool.AddSuspectLevel((int)((Tool.ammin(XRange-3.1)+Tool.ammin(YRange-3.1)+Tool.ammin(ZRange-3.1))*100),
                // (Player)e.getDamager());
                Ptlm.tagvalueadd(player, "reachcf", 7)
                if (Ptlm.gettagvalueint(player, "reachcf") > 30) {
                    return true
                }
            }
        }
        Ptlm.tagvalueadd(player, "reachcf", -2)
        return false
    }
}