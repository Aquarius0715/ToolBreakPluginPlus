package aquarius0715.toolbreakwarningplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Statistic {

    ToolBreakWarningPlugin plugin;

    public Statistic(ToolBreakWarningPlugin plugin) {
        this.plugin = plugin;
    }

    public void getStatistic(Player player) {

        for (Material material : Material.values()) {
            plugin.allBrockIn = plugin.allBrockIn + player.getStatistic(org.bukkit.Statistic.MINE_BLOCK, material);
        }
        plugin.allBlocks.put(player.getUniqueId(), plugin.allBrockIn);
        plugin.allBrockIn = 0;
    }
}
