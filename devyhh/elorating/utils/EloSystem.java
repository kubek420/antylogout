package devyhh.elorating.utils;

import devyhh.elorating.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class EloSystem {
    static Main plugin = Main.getPlugin();
    static FileConfiguration config = plugin.getConfig();
    public static void calculateRanking(Player victim, Player killer){
        int victimRanking = config.getInt("users."+victim.getUniqueId()+".ranking");
        int killerRanking = config.getInt("users."+killer.getUniqueId()+".ranking");
        float calculatedKillerDifference = calculateDiff(victimRanking, killerRanking);
        float calculatedVictimDifference = calculateDiff(killerRanking, victimRanking);
        int howManyPointsToKiller = (int)(30 * (1 - calculatedKillerDifference));
        int howManyPointsToVictim = (int)(30 * (0 - calculatedVictimDifference));
        config.set("users."+killer.getUniqueId()+".kills", config.getInt("users."+killer.getUniqueId()+".kills") + 1);
        config.set("users."+victim.getUniqueId()+".deaths", config.getInt("users."+victim.getUniqueId()+".deaths") + 1);
        config.set("users."+victim.getUniqueId()+".ranking", victimRanking+howManyPointsToVictim);
        config.set("users."+killer.getUniqueId()+".ranking", killerRanking+howManyPointsToKiller);
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage("§c"+victim.getDisplayName()+"("+howManyPointsToVictim+") §7zostal zabity przez §a"+killer.getDisplayName()+"(+"+howManyPointsToKiller+")");
        }
    }
    private static float calculateDiff(float rating1, float rating2){
        return 1.0f * 1.0f / (1 + 1.0f * (float)(Math.pow(10, 1.0f * (rating1 - rating2) / 400)));
    }
}
