package aquarius0715.toolbreakwarningplugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Event implements Listener {

    ToolBreakWarningPlugin plugin;

    public Event(ToolBreakWarningPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();

        if (!plugin.notice_stats.containsKey(player.getUniqueId())) {
            plugin.notice_stats.putIfAbsent(event.getPlayer().getUniqueId(), true);
            plugin.stopper_stats.putIfAbsent(event.getPlayer().getUniqueId(), true);
            plugin.scoreboard_stats.putIfAbsent(event.getPlayer().getUniqueId(), true);
            plugin.hourMinedBlocks.putIfAbsent(event.getPlayer().getUniqueId(), 0);
            plugin.ScoreBoard.updateScoreBoardDamageEvent(player, event);
        }

        int maxDurability = event.getItem().getType().getMaxDurability();
        int nowDurability = (event.getItem().getType().getMaxDurability() - event.getItem().getDurability()) - 1;

        if (!plugin.plugin_stats) {
            event.isCancelled();
            return;
        }

        if (plugin.notice_stats.get(player.getUniqueId()).equals(true)) {

            if (nowDurability <= maxDurability * 0.2 && nowDurability >= maxDurability * 0.05) {
                String message = ChatColor.GRAY + "" + ChatColor.BOLD + "(ツール名: " + event.getItem().getType() +
                        " / 最大耐久値: " + maxDurability +
                        " /" + ChatColor.YELLOW + "" + ChatColor.BOLD + "現在の耐久値: " + nowDurability + ChatColor.GRAY + ChatColor.BOLD + ")";
                TextComponent component = new TextComponent();
                component.setText(message);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);

            } else if (nowDurability <= maxDurability * 0.05) {
                String message = ChatColor.GRAY + "" + ChatColor.BOLD + "(ツール名: " + event.getItem().getType() +
                        " / 最大耐久値: " + maxDurability +
                        " /" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "現在の耐久値: " + nowDurability + ChatColor.GRAY + ChatColor.BOLD + ")";
                TextComponent component = new TextComponent();
                component.setText(message);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
                plugin.SoundData.SoundData(event.getPlayer());

                if (nowDurability == 0) {
                    if (player.getInventory().contains(player.getItemInHand().getType())) {
                        ItemStack itemStack = new ItemStack(Objects.requireNonNull(Material.getMaterial(player.getItemInHand().getType().toString())));
                        itemStack.addEnchantments(player.getItemInHand().getEnchantments());
                        itemStack.setItemMeta(player.getItemInHand().getItemMeta());
                    }
                }

            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        if (!plugin.notice_stats.containsKey(event.getPlayer().getUniqueId())) {
            plugin.notice_stats.putIfAbsent(event.getPlayer().getUniqueId(), true);
            plugin.stopper_stats.putIfAbsent(event.getPlayer().getUniqueId(), true);
            plugin.scoreboard_stats.putIfAbsent(event.getPlayer().getUniqueId(), true);
            plugin.hourMinedBlocks.putIfAbsent(event.getPlayer().getUniqueId(), 0);
            plugin.ScoreBoard.updateScoreBoard(event.getPlayer(), event);
        }

        Player player = event.getPlayer();

        boolean contains = plugin.stopper_stats.containsKey(player.getUniqueId());

        plugin.hourMinedBlocks.put(event.getPlayer().getUniqueId(), plugin.hourMinedBlocks.get(event.getPlayer().getUniqueId()) + 1);

        if (!plugin.plugin_stats || !contains) {
            event.isCancelled();
            return;
        }

        if (plugin.stopper_stats.get(player.getUniqueId()).equals(true)) {
            int nowDurability = (player.getItemInHand().getType().getMaxDurability() - player.getItemInHand().getDurability());
            String message = ChatColor.RED + "" + ChatColor.BOLD + "ストッパーが作動しました！！！";
            TextComponent component = new TextComponent();
            component.setText(message);

            if (nowDurability == 1 && !event.getPlayer().getInventory().getItemInMainHand().getType().isAir()) {
                event.setCancelled(true);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
            }
        }
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.plugin_stats) {
            player.sendMessage(plugin.prefix + "ToolBreakWarning は有効化されています。詳細は「/tbw」と入力して確認して下さい。");

            plugin.notice_stats.putIfAbsent(player.getUniqueId(), true);
            plugin.stopper_stats.putIfAbsent(player.getUniqueId(), true);
            plugin.scoreboard_stats.putIfAbsent(player.getUniqueId(), true);
            plugin.hourMinedBlocks.putIfAbsent(player.getUniqueId(), 0);
            plugin.ScoreBoard.updateScoreBoardJoinEvent(player, event);

        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.scoreboard_stats.putIfAbsent(player.getUniqueId(), false);
    }
}
