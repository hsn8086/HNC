package com.github.hongshinn.data

import com.github.hongshinn.Utils
import org.bukkit.BanList
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * @author hsn
 * @version 1.0
 * @since 22-07-12 3:28
 */
object PlayerData {
    var suspiciousLevel: HashMap<String?, Int?>? = HashMap()
    var flightTime: HashMap<String?, Int?>? = HashMap()
    var dropTime: HashMap<String?, Int?>? = HashMap()
    var onGroundCount: HashMap<String?, Int?>? = HashMap()
    var groundPosition: HashMap<String?, Double?>? = HashMap()
    var takeOffPosition: HashMap<String?, Double?>? = HashMap()
    var isDropping: HashMap<String?, Boolean?>? = HashMap()
    var isJumping: HashMap<String?, Boolean?>? = HashMap()
    var cpsCount: HashMap<String?, ArrayList<Long?>?>? = HashMap()
    var cpsRecord: HashMap<String?, MutableList<Int?>?>? = HashMap()
    private var banList: BanList? = Bukkit.getBanList(BanList.Type.NAME)
    fun addSuspiciousLevel(player: Player?, level: Int) {
        val playerName: String? = player!!.name
        suspiciousLevel!!.putIfAbsent(playerName, 0)
        suspiciousLevel!![playerName] = suspiciousLevel!![playerName]!! + level
        /*
        if (level > 0) {
            player.sendMessage("§c你的嫌疑等级已经提高到" + suspiciousLevel!![playerName]);
        }
         */
        if (suspiciousLevel!!.get(playerName)!! > PluginConfig.mainSuspicionLevelMax) {
            if (("kick" == PluginConfig.mainMode)) {
                player?.kickPlayer("You are kicked for cheating!!!")
            } else if (("ban" == PluginConfig.mainMode)) {
                player?.kickPlayer("You are kicked for cheating!!!")
                banList?.addBan(playerName, "You are banned for cheating!!!", Utils.getBanDate(7), null)
            }
        }
    }

    fun removePlayer(player: Player?) {
        suspiciousLevel!!.remove(player!!.getName())
        flightTime!!.remove(player.getName())
        dropTime!!.remove(player.getName())
        isDropping!!.remove(player.getName())
        isJumping!!.remove(player.getName())
    }
}