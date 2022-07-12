package devyhh.elorating.listeners;

import devyhh.elorating.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {
    Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(config.getString("users."+p.getUniqueId()+".timestamp") == null){
            config.set("users."+p.getUniqueId()+".ranking", 1000);
            config.set("users."+p.getUniqueId()+".kills", 0);
            config.set("users."+p.getUniqueId()+".deaths", 0);
            config.set("users."+p.getUniqueId()+".timestamp", 1657489649);
        }
        plugin.saveConfig();
    }
}
