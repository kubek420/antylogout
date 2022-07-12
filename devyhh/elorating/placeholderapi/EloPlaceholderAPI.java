package devyhh.elorating.placeholderapi;

import devyhh.elorating.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EloPlaceholderAPI extends PlaceholderExpansion {
    private final Main plugin;

    public EloPlaceholderAPI(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "devyhh";
    }

    @Override
    public String getIdentifier() {
        return "mclik";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if(params.equalsIgnoreCase("deaths")){
            return String.valueOf(plugin.getConfig().get("users."+player.getUniqueId()+".deaths"));
        }

        if(params.equalsIgnoreCase("kills")) {
            return String.valueOf(plugin.getConfig().get("users."+player.getUniqueId()+".kills"));
        }

        if(params.equalsIgnoreCase("score")) {
            return String.valueOf(plugin.getConfig().get("users."+player.getUniqueId()+".ranking"));
        }
        return null;
    }

}
