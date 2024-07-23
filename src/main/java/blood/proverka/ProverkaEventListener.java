package blood.proverka;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ProverkaEventListener implements Listener {
    private final Proverka plugin;

    public ProverkaEventListener(Proverka plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.DARK_RED + "Inspection menu")) {
            event.setCancelled(true);
            Player checker = (Player) event.getWhoClicked();
            ProverkaSession session = null;

            for (ProverkaSession s : plugin.getActiveSessions().values()) {
                if (s.getChecker().equals(checker)) {
                    session = s;
                    break;
                }
            }

            if (session == null) {
                checker.sendMessage(ChatColor.RED + "You don't have an active check.");
                return;
            }

            Player target = session.getTarget();

            switch (event.getSlot()) {
                case 10: // Teleport
                    target.teleport(checker.getLocation());
                    checker.sendMessage(ChatColor.GREEN + "Player teleported to you.");
                    break;
                case 12: // Freeze/Unfreeze
                    session.setFrozen(!session.isFrozen());
                        String freezeStatus = session.isFrozen() ? "frozen" : "defrosted";
                    checker.sendMessage(ChatColor.GREEN + "Player " + freezeStatus + ".");
                    target.sendMessage(ChatColor.RED + "You were " + freezeStatus + ".");
                    break;
                case 14: // Check inventory
                    checker.openInventory(target.getInventory());
                    break;
                case 16: // Accept check
                    plugin.getServer().dispatchCommand(checker, "proverkaaccepted");
                    break;
                case 22: // Reject check
                    target.sendMessage(ChatColor.RED + "You have not been verified. Please contact the administration.");
                    plugin.getActiveSessions().remove(target.getName());
                    checker.sendMessage(ChatColor.RED + "Check denied.");
                    break;
            }

            checker.closeInventory();
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ProverkaSession session = plugin.getActiveSessions().get(player.getName());
        if (session != null && (session.isFrozen() || !session.isOnHold())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        if (plugin.getActiveSessions().containsKey(player.getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (plugin.getActiveSessions().containsKey(player.getName())) {
            String command = event.getMessage().split(" ")[0].toLowerCase();
            if (!command.equals("/ds")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You can only use the /ds command during the checkout.");
            }
        }
    }
}