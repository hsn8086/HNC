package com.github.hsn8086


import com.github.hsn8086.anticheat.AntiCheatManager
import com.github.hsn8086.anticheat.detectionItems.*
import com.github.hsn8086.data.PluginConfig
import com.github.hsn8086.event.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author hsn
 */
class Main : JavaPlugin(), Listener {
    private fun loadConfig() {
        saveDefaultConfig()
        reloadConfig()
        val config: FileConfiguration? = config
        if (config != null) {
            PluginConfig.mainSuspicionLevelDecreaseRate = config.getInt("Main.SuspicionLevel.DecreaseRate")
            PluginConfig.mainMode = config.getString("Main.Mode")
            PluginConfig.mainSuspicionLevelMax = config.getInt("Main.SuspicionLevel.Max")
            PluginConfig.mainSuspicionLevelBacktracking = config.getInt("Main.SuspicionLevel.Backtracking")
            PluginConfig.detectionItemDropSuspicionLevel = config.getInt("DetectionItem.Drop.SuspicionLevel")
            PluginConfig.detectionItemDropCheckSpeed = config.getBoolean("DetectionItem.Drop.CheckSpeed")
            PluginConfig.detectionItemDropMaxSpeed = config.getDouble("DetectionItem.Drop.MaxSpeed")
            PluginConfig.detectionItemJumpSuspicionLevel = config.getInt("DetectionItem.Jump.SuspicionLevel")
            PluginConfig.detectionItemJumpMaxHeight = config.getInt("DetectionItem.Jump.MaxHeight")
            PluginConfig.detectionItemJumpCheckHeight = config.getBoolean("DetectionItem.Jump.CheckHeight")
            PluginConfig.detectionItemFlightSuspicionLevel = config.getInt("DetectionItem.Flight.SuspicionLevel")
            PluginConfig.detectionItemFlightCheckHorizontalFlight =
                config.getBoolean("DetectionItem.Flight.CheckHorizontalFlight")
            PluginConfig.detectionItemFlightCheckFastFlight = config.getBoolean("DetectionItem.Flight.CheckFastFlight")
            PluginConfig.detectionItemFlightCheckFlexibleFlight =
                config.getBoolean("DetectionItem.Flight.CheckFlexibleFlight")
            PluginConfig.detectionItemWalkSuspicionLevel = config.getInt("DetectionItem.Walk.SuspicionLevel")
            PluginConfig.detectionItemWalkCheckSafeWalk = config.getBoolean("DetectionItem.Walk.CheckSafeWalk")
            PluginConfig.detectionItemWalkCheckLiquidWalk = config.getBoolean("DetectionItem.Walk.CheckLiquidWalk")
            PluginConfig.detectionItemClimbSuspicionLevel = config.getInt("DetectionItem.Climb.SuspicionLevel")
            PluginConfig.detectionItemClimbFastClimb = config.getBoolean("DetectionItem.Climb.FastClimb")
            PluginConfig.detectionItemClimbCheckWallClimb = config.getBoolean("DetectionItem.Climb.CheckWallClimb")
            PluginConfig.detectionItemSwimSuspicionLevel = config.getInt("DetectionItem.Swim.SuspicionLevel")
            PluginConfig.detectionItemSwimCheckWaterSpeed = config.getBoolean("DetectionItem.Swim.CheckWaterSpeed")
            PluginConfig.detectionItemTimeSuspicionLevel = config.getInt("DetectionItem.Time.SuspicionLevel")
            PluginConfig.detectionItemTimeMaxPacket = config.getInt("DetectionItem.Time.MaxPacket")
            PluginConfig.detectionItemTimeCheckTimer = config.getBoolean("DetectionItem.Time.CheckTimer")
            PluginConfig.detectionItemCombatCriticalSuspicionLevel =
                config.getInt("DetectionItem.Combat.Critical.SuspicionLevel")
            PluginConfig.detectionItemCombatCheckCritical =
                config.getBoolean("DetectionItem.Combat.Critical.CheckCritical")
            PluginConfig.detectionItemCombatReachSuspicionLevel =
                config.getInt("DetectionItem.Combat.Reach.SuspicionLevel")
            PluginConfig.detectionItemCombatCheckReach = config.getBoolean("DetectionItem.Combat.CheckReach")
            PluginConfig.detectionItemCombatReach = config.getDouble("DetectionItem.Combat.Reach.Distance")
            PluginConfig.detectionItemCombatCheckCPS = config.getBoolean("DetectionItem.Combat.CPS.CheckCPS")
            PluginConfig.detectionItemCombatMaxCPS = config.getInt("DetectionItem.Combat.CPS.MaxCPS")
            PluginConfig.detectionItemCombatCPSSuspicionLevel = config.getInt("DetectionItem.Combat.CPS.SuspicionLevel")

            PluginConfig.detectionItemCombatKillAuraSimple=config.getBoolean("DetectionItem.Combat.KillAura.Simple")
            PluginConfig.detectionItemCombatKillAuraSuspicionLevel=config.getInt("DetectionItem.Combat.KillAura.SuspicionLevel")
        }
        AntiCheatManager.clearDetectionItems()
        AntiCheatManager.registerDetectionItem(AutoClick())
        AntiCheatManager.registerDetectionItem(HorizontalFlight())
        AntiCheatManager.registerDetectionItem(FastFlight())
        AntiCheatManager.registerDetectionItem(HighJump())
        AntiCheatManager.registerDetectionItem(Critical())
        AntiCheatManager.registerDetectionItem(OnGround())
        AntiCheatManager.registerDetectionItem(DropSpeed())
        //AntiCheatManager.registerDetectionItem(SafeWalk())
        AntiCheatManager.registerDetectionItem(KillAuraSimple())
        AntiCheatManager.registerDetectionItem(Reach())
    }

    override fun onCommand(
        sender: CommandSender?,
        cmd: Command?,
        label: String?,
        args: Array<String?>?
    ): Boolean {
        if ("hnc_reload".equals(cmd!!.name, ignoreCase = true) && sender!!.isOp) {
            loadConfig()
            sender.sendMessage("Reload complete")
            return true
        }
        return "hnc_debug".equals(cmd.name, ignoreCase = true) && sender!!.isOp
    }

    override fun onEnable() {

        loadConfig()
        Bukkit.getPluginManager().registerEvents(ChatEvent(), this)
        Bukkit.getPluginManager().registerEvents(MoveEvent(), this)
        Bukkit.getPluginManager().registerEvents(ClickEvent(), this)
        Bukkit.getPluginManager().registerEvents(JoinEvent(), this)
        Bukkit.getPluginManager().registerEvents(QuitEvent(), this)
        Bukkit.getPluginManager().registerEvents(DamageEvent(), this)

    }

    override fun onDisable() {}
}