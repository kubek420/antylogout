package devyhh.elorating.listeners;

import devyhh.elorating.Main;
import devyhh.elorating.utils.AntyLogout;
import devyhh.elorating.utils.EloSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerDeath implements Listener {
    static Main plugin = Main.getPlugin();
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.setDeathMessage(null);
        Player p = e.getEntity();
        if(p.getKiller() != null){
            Player killer = p.getKiller();
            EloSystem.calculateRanking(p, killer);
            AntyLogout.removePlayerAntyLogout(p.getUniqueId());
        }
        plugin.saveConfig();
    }
}
