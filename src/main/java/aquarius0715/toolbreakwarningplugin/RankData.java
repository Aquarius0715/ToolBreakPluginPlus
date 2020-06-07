package aquarius0715.toolbreakwarningplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RankData {

    ToolBreakWarningPlugin plugin;

    public RankData(ToolBreakWarningPlugin plugin) {
        this.plugin = plugin;
    }

    public void RankData(Player player) {
        if (plugin.allBlocks.get(player.getUniqueId()) >= 0 && plugin.allBlocks.get(player.getUniqueId()) <= 1000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.YELLOW + "Tourist");
            plugin.remainRank.put(player.getUniqueId(), 1000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 1000 && plugin.allBlocks.get(player.getUniqueId()) <= 10000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.DARK_GREEN + "Crafter");
            plugin.remainRank.put(player.getUniqueId(), 10000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 10000 && plugin.allBlocks.get(player.getUniqueId()) <= 30000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.DARK_GREEN + "Crafter" + ChatColor.DARK_PURPLE + "+");
            plugin.remainRank.put(player.getUniqueId(), 30000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 30000 && plugin.allBlocks.get(player.getUniqueId()) <= 50000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.LIGHT_PURPLE + "Expert");
            plugin.remainRank.put(player.getUniqueId(), 50000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 50000 && plugin.allBlocks.get(player.getUniqueId()) <= 100000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.LIGHT_PURPLE + "Expert" + ChatColor.YELLOW + "+");
            plugin.remainRank.put(player.getUniqueId(), 100000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 100000 && plugin.allBlocks.get(player.getUniqueId()) <= 200000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.GRAY + "Miner");
            plugin.remainRank.put(player.getUniqueId(), 200000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 200000 && plugin.allBlocks.get(player.getUniqueId()) <= 300000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.LIGHT_PURPLE + "Miner" + ChatColor.YELLOW + "+");
            plugin.remainRank.put(player.getUniqueId(), 300000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 300000 && plugin.allBlocks.get(player.getUniqueId()) <= 500000) {
            plugin.rank.put(player.getUniqueId(), "Super" + ChatColor.GRAY + "Miner");
            plugin.remainRank.put(player.getUniqueId(), 500000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 500000 && plugin.allBlocks.get(player.getUniqueId()) <= 1000000) {
            plugin.rank.put(player.getUniqueId(), "Super" + ChatColor.GRAY + "Miner" + ChatColor.YELLOW + "+");
            plugin.remainRank.put(player.getUniqueId(), 1000000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 1000000 && plugin.allBlocks.get(player.getUniqueId()) <= 3000000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Breaker");
            plugin.remainRank.put(player.getUniqueId(), 3000000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 3000000 && plugin.allBlocks.get(player.getUniqueId()) <= 5000000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Breaker" + ChatColor.LIGHT_PURPLE + "+");
            plugin.remainRank.put(player.getUniqueId(), 5000000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) >= 5000000 && plugin.allBlocks.get(player.getUniqueId()) <= 10000000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.BLACK + "" + ChatColor.BOLD + "Destroyer");
            plugin.remainRank.put(player.getUniqueId(), 10000000 - plugin.allBlocks.get(player.getUniqueId()));

        } else if (plugin.allBlocks.get(player.getUniqueId()) < 10000000) {
            plugin.rank.put(player.getUniqueId(), ChatColor.BLACK + "" + ChatColor.BOLD + "" + ChatColor.MAGIC
                    + "a" + ChatColor.BLACK + ChatColor.BOLD
                    + "Destroyer" + ChatColor.BLACK + ChatColor.BOLD + ChatColor.MAGIC
                    + "a");
            plugin.remainRank.put(player.getUniqueId(), 2147483647 - plugin.allBlocks.get(player.getUniqueId()));

        }
    }
}
