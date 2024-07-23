package blood.proverka;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ProverkaMenuManager {

    public static void openCheckerMenu(Player checker, Proverka plugin) {
        Inventory menu = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "Inspection menu");

        ItemStack teleportItem = createMenuItem(Material.ENDER_PEARL, ChatColor.YELLOW + "Teleport a player");
        ItemStack freezeItem = createMenuItem(Material.ICE, ChatColor.AQUA + "Freeze/unfreeze a player");
        ItemStack checkInvItem = createMenuItem(Material.CHEST, ChatColor.GREEN + "Check the inventory");
        ItemStack acceptItem = createMenuItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "Accept the inspection");
        ItemStack rejectItem = createMenuItem(Material.REDSTONE_BLOCK, ChatColor.RED + "Reject the inspection");

        menu.setItem(10, teleportItem);
        menu.setItem(12, freezeItem);
        menu.setItem(14, checkInvItem);
        menu.setItem(16, acceptItem);
        menu.setItem(22, rejectItem);

        checker.openInventory(menu);
    }

    private static ItemStack createMenuItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}