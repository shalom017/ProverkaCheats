package blood.proverka;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProverMenuCommand implements CommandExecutor {
    private final Proverka plugin;

    public ProverMenuCommand(Proverka plugin) {
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
            checker.sendMessage(ChatColor.RED + "You do not have permissions to use this command.");
            return true;
        }

        ProverkaMenuManager.openCheckerMenu(checker, plugin);
        return true;
    }
}