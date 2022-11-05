package com.github.hongshinn.data

import org.bukkit.plugin.java.JavaPlugin

/**
 * @author hsn
 * @version 1.0
 * @since 22-07-12 3:39
 */
object PluginConfig {
    var mainSuspicionLevelDecreaseRate: Int = 0
    var mainSuspicionLevelMax: Int = 0
    var mainSuspicionLevelBacktracking: Int = 0
    var detectionItemDropSuspicionLevel: Int = 0
    var detectionItemJumpMaxHeight: Int = 0
    var detectionItemJumpSuspicionLevel: Int = 0
    var detectionItemFlightSuspicionLevel: Int = 0
    var detectionItemWalkSuspicionLevel: Int = 0
    var detectionItemClimbSuspicionLevel: Int = 0
    var detectionItemSwimSuspicionLevel: Int = 0
    var detectionItemTimeMaxPacket: Int = 0
    var detectionItemTimeSuspicionLevel: Int = 0
    var detectionItemCombatCriticalSuspicionLevel: Int = 0
    var detectionItemCombatReachSuspicionLevel: Int = 0
    var detectionItemCombatMaxCPS: Int = 0
    var detectionItemCombatCPSSuspicionLevel: Int = 0
    var detectionItemDropCheckSpeed: Boolean = false
    var detectionItemJumpCheckHeight: Boolean = false
    var detectionItemFlightCheckHorizontalFlight: Boolean = false
    var detectionItemFlightCheckFastFlight: Boolean = false
    var detectionItemFlightCheckFlexibleFlight: Boolean = false
    var detectionItemWalkCheckSafeWalk: Boolean = false
    var detectionItemWalkCheckLiquidWalk: Boolean = false
    var detectionItemClimbCheckWallClimb: Boolean = false
    var detectionItemClimbFastClimb: Boolean = false
    var detectionItemSwimCheckWaterSpeed: Boolean = false
    var detectionItemTimeCheckTimer: Boolean = false
    var detectionItemCombatCheckCritical: Boolean = false
    var detectionItemCombatCheckReach: Boolean = false
    var detectionItemCombatCheckCPS: Boolean = false
    var detectionItemDropMaxSpeed: Double = 0.0
    var detectionItemCombatReach: Double = 0.0
    var mainMode: String? = null
}