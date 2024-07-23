package blood.proverka;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class ProverkaAcceptedCommand implements CommandExecutor {
    private final Proverka plugin;

    public ProverkaAcceptedCommand(Proverka plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player checker = (Player) sender;

        if (!plugin.hasPermission(checker)) {
            checker.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        for (Map.Entry<String, ProverkaSession> entry : plugin.getActiveSessions().entrySet()) {
            ProverkaSession session = entry.getValue();
            if (session.getChecker().equals(checker)) {
                Player target = session.getTarget();
                target.sendMessage(ChatColor.GREEN + "Thank you for playing on our project! You have successfully passed the test, well done!");
                plugin.getActiveSessions().remove(entry.getKey());
                checker.sendMessage(ChatColor.GREEN + "Verification completed successfully.");
                return true;
            }
        }

        checker.sendMessage(ChatColor.RED + "You don't have an active check.");
        return true;
    }
}