package blood.proverka;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DsCommand implements CommandExecutor {
    private final Proverka plugin;

    public DsCommand(Proverka plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        ProverkaSession session = plugin.getActiveSessions().get(player.getName());

        if (session == null) {
            player.sendMessage(ChatColor.RED + "You don't have an active check.");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /ds <discord>");
            return true;
        }

        String discord = String.join(" ", args);
        session.getChecker().sendMessage(ChatColor.GREEN + player.getName() + " provided by Discord: " + discord);
        player.sendMessage(ChatColor.GREEN + "You have successfully submitted your Discord.");

        return true;
    }
}