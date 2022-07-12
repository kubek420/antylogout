package devyhh.elorating.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetPlayerStatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if(args.length > 0){
            Player player = Bukkit.getPlayer(args[0]);
            if(player != null)
                p.sendMessage("\n§7Gracz: §3"+player.getDisplayName()+"\n§7Ranking: §f"+PlaceholderAPI.setPlaceholders(player, "%mclik_score%")+"\n§7Zabojstwa: §a"+PlaceholderAPI.setPlaceholders(player, "%mclik_kills%")+"\n§7Smierci: §c"+PlaceholderAPI.setPlaceholders(player, "%mclik_deaths%")+"\n ");
            else
                p.sendMessage("§cGracz jest offline!");
        } else {
            p.sendMessage("\n§7Gracz: §3"+p.getDisplayName()+"\n§7Ranking: §f"+PlaceholderAPI.setPlaceholders(p, "%mclik_score%")+"\n§7Zabojstwa: §a"+PlaceholderAPI.setPlaceholders(p, "%mclik_kills%")+"\n§7Smierci: §c"+PlaceholderAPI.setPlaceholders(p, "%mclik_deaths%")+"\n ");
        }

        return true;
    }
}
