package com.github.hongshinn.anticheatold

import org.bukkit.entity.Player

object CheakManager {
    fun MoveeventCheak(e: PlayerMoveEvent?) {
        val pd: PlayerDataOld? = PlayerDataOld()
        pd.load(e)
        if (MovementCheck.Cheak(MovementCheck.FALLINGSPEED, pd)) {
            Tool.AddSuspectLevel(Global.fslevel, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.HIGHJUMP, pd)) {
            Tool.AddSuspectLevel(Global.jhlevel, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.FLIGHT, pd)) {
            Tool.AddSuspectLevel(Global.fllevel, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.WALLCLIMB, pd)) {
            Tool.AddSuspectLevel(Global.wclevel, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.SPEED, pd)) {
            Tool.AddSuspectLevel(50, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.RISESPEED, pd)) {
            Tool.AddSuspectLevel(50, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.WATERSPEED, pd)) {
            Tool.AddSuspectLevel(Global.wslevel, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.SAFEWALK, pd)) {
            Tool.AddSuspectLevel(Global.sflevel, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.CRITICALS, pd)) {
            Tool.AddSuspectLevel(Global.ctlevel, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.LIQUIDWALK, pd)) {
            Tool.AddSuspectLevel(Global.lqlevel, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.TIMER, pd)) {
            Tool.AddSuspectLevel(Global.tmlevel, pd.player)
        }
        if (MovementCheck.Cheak(MovementCheck.FLEXIBLE, pd)) {
            Tool.AddSuspectLevel(Global.flxlevel, pd.player)
        }
        if (Ptlm.gettagvalueint(pd.player, "suspectlevel") > 500) {
            // Ptlm.tagvalueadd(player, "suspectlevel", 200);
            if ((Global.Cancelmin + Math.random() * (Global.Cancelmax - Global.Cancelmin + 1)) as Int == 1) {
                e.setCancelled(true)
            }
        }
    }

    fun ClickCheak(e: PlayerInteractEvent?) {
        val pd: PlayerDataOld? = PlayerDataOld()
        pd.loadce(e)
        if (ClickCheck.Cheak(ClickCheck.AUTOCLICK, pd)) {
            Tool.AddSuspectLevel(50, pd.player)
        }
        if (Ptlm.gettagvalueint(pd.player, "suspectlevel") > Global.cancellevel) {
            // Ptlm.tagvalueadd(player, "suspectlevel", 200);
            if ((Global.Cancelmin + Math.random() * (Global.Cancelmax - Global.Cancelmin + 1)) as Int == 1) {
                e.setCancelled(true)
            }
        }
    }

    fun DamageCheak(e: EntityDamageByEntityEvent?) {
        val pd: PlayerDataOld? = PlayerDataOld()
        pd.loadDamage(e)
        if (DamageCheck.Cheak(DamageCheck.REACH, pd)) {
            Tool.AddSuspectLevel(Global.rclevel, pd.Damager as Player?)
        }
        if (Ptlm.gettagvalueint(pd.Damager as Player?, "suspectlevel") > Global.cancellevel) {
            // Ptlm.tagvalueadd(player, "suspectlevel", 200);
            if ((Global.Cancelmin + Math.random() * (Global.Cancelmax - Global.Cancelmin + 1)) as Int == 1) {
                e.setCancelled(true)
            }
        }
    }
}