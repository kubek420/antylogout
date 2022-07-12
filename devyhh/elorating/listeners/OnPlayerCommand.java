package devyhh.elorating.listeners;

import devyhh.elorating.utils.AntyLogout;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OnPlayerCommand implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        String[] args = e.getMessage().split(" ");
        if(!args[0].equalsIgnoreCase("/msg") &&
                !args[0].equalsIgnoreCase("/r") &&
                !args[0].equalsIgnoreCase("/zglos") &&
                !args[0].equalsIgnoreCase("/gracz") &&
                !args[0].equalsIgnoreCase("/report"))
        {
            Player p = e.getPlayer();
            if(AntyLogout.isPlayerHasAntyLogout(p.getUniqueId())){
                e.setCancelled(true);
                p.sendMessage("§cNie mozesz uzywac tej komendy podczas walki!");
            }
        }
    }
}
