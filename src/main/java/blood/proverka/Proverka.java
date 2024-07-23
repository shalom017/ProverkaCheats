package blood.proverka;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class Proverka extends JavaPlugin implements Listener {

    private Map<String, ProverkaSession> activeSessions = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("proverka").setExecutor(new ProverkaCommand(this));
        getCommand("okproverka").setExecutor(new OkProverkaCommand(this));
        getCommand("ds").setExecutor(new DsCommand(this));
        getCommand("proverkaaccepted").setExecutor(new ProverkaAcceptedCommand(this));
        getCommand("provermenu").setExecutor(new ProverMenuCommand(this));

        getServer().getPluginManager().registerEvents(new ProverkaEventListener(this), this);
    }

    public Map<String, ProverkaSession> getActiveSessions() {
        return activeSessions;
    }

    public boolean hasPermission(Player player) {
        return player.hasPermission("proverka.use") ||
                player.hasPermission("group.admin1") ||
                player.hasPermission("group.admin2") ||
                player.hasPermission("group.admin3") ||
                player.hasPermission("group.admin4") ||
                player.hasPermission("group.admin5");
    }
}