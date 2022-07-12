package devyhh.elorating.utils;

import devyhh.elorating.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class AntyLogout {
    public static final HashMap<UUID, Boolean> alList = new HashMap<>();
    public static final HashMap<UUID, Player> attackerList = new HashMap<>();
    public static final HashMap<UUID, Integer> time = new HashMap<>();
    public static final HashMap<UUID, BukkitTask> tasks = new HashMap<>();
    public static final HashMap<UUID, BossBar> bossbars = new HashMap<>();
    static Main plugin = Main.getPlugin();
    public static void createAntyLogout(Player victim, Player attacker) {
        addAntyLogout(victim);
        addAntyLogout(attacker);
        attackerList.put(victim.getUniqueId(), attacker);
        attackerList.put(attacker.getUniqueId(), victim);
    }
    public static boolean isPlayerHasAntyLogout(UUID uuid){
        return alList.get(uuid) == null ? false : alList.get(uuid);
    }
    public static Player getPlayerAttacker(UUID uuid){
        return attackerList.get(uuid);
    }
    public static void removePlayerAntyLogout(UUID uuid){
        alList.put(uuid, false);
        bossbars.get(uuid).removeAll();
        tasks.get(uuid).cancel();
    }

    private static void addAntyLogout(Player player){
        final BossBar bar = Bukkit.createBossBar("§cAntyLogout - Pozostalo 30 sekund!", BarColor.RED, BarStyle.SOLID);
        bar.addPlayer(player);
        if(alList.get(player.getUniqueId()) == null || !alList.get(player.getUniqueId())) {
            alList.put(player.getUniqueId(), true);
        }
        else {
            tasks.get(player.getUniqueId()).cancel();
            bossbars.get(player.getUniqueId()).removePlayer(player);
        }
        bossbars.put(player.getUniqueId(), bar);
        time.put(player.getUniqueId(), 30);

        final double[] progress = {1.0};
        BukkitTask task = new BukkitRunnable()
        {
            public void run()
            {
                int timeLeft = time.get(player.getUniqueId());
                if (timeLeft == 0) {
                    player.sendMessage("§cAntylogout skonczyl sie!");
                    bar.removePlayer(player);
                    alList.put(player.getUniqueId(), false);
                    this.cancel();
                } else {
                    progress[0] = progress[0] - 1.0/30;
                    bar.setProgress(progress[0]);
                    bar.setTitle("§cAntyLogout - Pozostalo "+timeLeft+" sekund!");
                    time.put(player.getUniqueId(), timeLeft - 1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
        tasks.put(player.getUniqueId(), task);
    }
}
