package devyhh.elorating.listeners;

import devyhh.elorating.utils.AntyLogout;
import devyhh.elorating.utils.EloSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayerLeave implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(AntyLogout.isPlayerHasAntyLogout(p.getUniqueId())){
            for(Player player : Bukkit.getOnlinePlayers())
                player.sendMessage("§7Gracz §c"+p.getDisplayName()+" §7wylogowal sie podczas walki!");
            EloSystem.calculateRanking(p, AntyLogout.getPlayerAttacker(p.getUniqueId()));
            for(ItemStack item : p.getInventory().getContents())
                if(item != null)
                    Bukkit.getWorld("world").dropItemNaturally(p.getLocation(), item);
            p.getInventory().clear();
            AntyLogout.removePlayerAntyLogout(p.getUniqueId());
            p.setHealth(p.getMaxHealth());
            p.setFoodLevel(20);
        }
    }
}
