package devyhh.elorating.commands;

import devyhh.elorating.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

public class ResetPlayerStatsCommand implements CommandExecutor {
    Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if((int)timestamp.getTime()/1000 - (int)config.getLong("users."+p.getUniqueId()+".timestamp") > 60 * 15){
            config.set("users."+p.getUniqueId()+".ranking", 1000);
            config.set("users."+p.getUniqueId()+".kills", 0);
            config.set("users."+p.getUniqueId()+".deaths", 0);
            config.set("users."+p.getUniqueId()+".timestamp", Math.round(timestamp.getTime() / 1000));
            plugin.saveConfig();
            p.sendMessage("\n§7Twoj ranking zostal §3zresetowany\n ");
        } else {
            p.sendMessage("\n§7Ranking mozesz resetowac raz na §c15 minut\n ");
        }
        return true;
    }
}
