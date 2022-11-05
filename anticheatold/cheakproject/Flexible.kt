package com.github.hongshinn.anticheatold.cheakproject

import org.bukkit.GameMode

object Flexible {
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
        var rotation: Double = (-player.getLocation().getYaw()) % 360
        if (rotation < 0) {
            rotation += 360.0
        }
        var d2: Double = Math.toDegrees(Math.atan(aspeedx / aspeedz))
        if (d2 < 0) {
            d2 = 180 + d2
        }
        if (eb == 9) {
            //player.sendMessage(String.valueOf(Math.sqrt(Math.pow(aspeedx, 2)+Math.pow(aspeedz, 2))));
            //2.9
            //Math.abs(aspeedx)<
            val spd: Double =
                Math.sqrt(Math.pow(aspeedx, 2) + Math.pow(aspeedz, 2)) * Math.sin(Math.toRadians(d2 - rotation))
            if (Math.abs(
                    Math.sqrt(
                        Math.pow(aspeedx, 2) + Math.pow(
                            aspeedz,
                            2
                        )
                    ) * Math.sin(Math.toRadians(d2 - rotation))
                ) > 0.215
            ) {
                Ptlm.tagvalueadd(player, "flxtime", 4)
                if (Ptlm.gettagvalueint(player, "flxtime") > 50) {
                    return true
                }
            } else {
                Ptlm.tagvalueadd(player, "flxtime", -1)
            }


            //player.sendMessage(String.valueOf(Math.abs(2.9*Math.cos(Math.toRadians(rotation)))));
        }
        return false
    }
}