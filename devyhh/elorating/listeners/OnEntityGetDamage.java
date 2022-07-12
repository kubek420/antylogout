package devyhh.elorating.listeners;


import devyhh.elorating.utils.AntyLogout;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnEntityGetDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e){
        if(e.isCancelled()) return;
        if(e.getDamage() == 0) return;
        Entity victim = e.getEntity();
        Entity attacker = e.getDamager();
        if(victim instanceof Player && attacker instanceof Player){
            AntyLogout.createAntyLogout((Player) victim, (Player) attacker);
        }
    }
}
