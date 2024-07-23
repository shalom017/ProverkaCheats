package blood.proverka;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ProverkaCommand implements CommandExecutor {
    private final Proverka plugin;

    public ProverkaCommand(Proverka plugin) {
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

        if (args.length != 1) {
            checker.sendMessage(ChatColor.RED + "Usage: /proverka <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            checker.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        Location checkLocation = new Location(checker.getWorld(), 0, 150, 0);

        checkLocation.getBlock().setType(Material.BARRIER);

        checker.teleport(checkLocation.clone().add(0, 1, 0));
        target.teleport(checkLocation.clone().add(0, 1, 0));

        target.sendTitle(ChatColor.RED + "You've been summoned for an inspection", "", 10, 200, 10);

        target.sendMessage(ChatColor.YELLOW + "You have 5 minutes to drop your discord, to contact the checker - for example /ds my discord, leave/afk on the check - ban for 30 days");

        BossBar bossBar = Bukkit.createBossBar("Time to provide Discord", BarColor.RED, BarStyle.SOLID);
        bossBar.addPlayer(target);

        ProverkaSession session = new ProverkaSession(checker, target, bossBar);
        plugin.getActiveSessions().put(target.getName(), session);

        new BukkitRunnable() {
            int timeLeft = 300;

            @Override
            public void run() {
                if (timeLeft <= 0 || !plugin.getActiveSessions().containsKey(target.getName())) {
                    bossBar.removeAll();
                    cancel();
                    return;
                }

                bossBar.setProgress(timeLeft / 300.0);
                timeLeft--;
            }
        }.runTaskTimer(plugin, 0L, 20L);

        return true;
    }
}