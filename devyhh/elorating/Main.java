package devyhh.elorating;

import devyhh.elorating.commands.GetPlayerStatsCommand;
import devyhh.elorating.commands.ResetPlayerStatsCommand;
import devyhh.elorating.listeners.*;
import devyhh.elorating.placeholderapi.EloPlaceholderAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    static Main plugin;
    public static Main getPlugin() {
        return plugin;
    }
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnEntityGetDamage(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerCommand(), this);
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new EloPlaceholderAPI(this).register();
        }
        this.getCommand("gracz").setExecutor(new GetPlayerStatsCommand());
        this.getCommand("resetujranking").setExecutor(new ResetPlayerStatsCommand());
    }

}
