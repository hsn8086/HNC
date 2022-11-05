package com.github.hongshinn.anticheatold

import com.github.hongshinn.anticheatold.cheakproject.*

object MovementCheck {
    val FALLINGSPEED: Int = 0
    val HIGHJUMP: Int = 1
    val FLIGHT: Int = 2
    val WALLCLIMB: Int = 3
    val SPEED: Int = 4
    val RISESPEED: Int = 5
    val WATERSPEED: Int = 6
    val SAFEWALK: Int = 7
    val CRITICALS: Int = 8
    val LIQUIDWALK: Int = 9
    val TIMER: Int = 10
    val FLEXIBLE: Int = 11
    fun Cheak(type: Int, pd: PlayerDataOld?): Boolean {
        var adds: Boolean = false
        when (type) {
            0 -> if (Global.fallingspeed) {
                adds = FallingSpeed.main(pd)
            }

            1 -> if (Global.jumphight) {
                adds = HighJump.main(pd)
            }

            2 -> if (Global.flight) {
                adds = Flight.main(pd)
            }

            3 -> if (Global.wallclimb) {
                adds = WallClimb.main(pd)
            }

            4 -> adds = Speed.main(pd)
            5 -> adds = RiseSpeed.main(pd)
            6 -> if (Global.waterspeed) {
                adds = WaterSpeed.main(pd)
            }

            7 -> if (Global.safewalk) {
                adds = SafeWalk.main(pd)
            }

            8 -> if (Global.criticals) {
                adds = Criticals.main(pd)
            }

            9 -> if (Global.liquidwalk) {
                adds = LiquidWalk.main(pd)
            }

            10 -> if (Global.timer) {
                adds = Timer.main(pd)
            }

            11 -> if (Global.flexible) {
                adds = Flexible.main(pd)
            }

            else -> adds = false
        }
        return adds
    }
}