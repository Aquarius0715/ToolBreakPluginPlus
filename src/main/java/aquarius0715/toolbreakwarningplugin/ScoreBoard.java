package aquarius0715.toolbreakwarningplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.Objects;

public class ScoreBoard {

    ToolBreakWarningPlugin plugin;


    public ScoreBoard(ToolBreakWarningPlugin plugin) {
        this.plugin = plugin;
    }

    public void createScoreBoard(Player player) {
        plugin.scoreboardManager = Bukkit.getScoreboardManager();
        plugin.scoreboard = Objects.requireNonNull(plugin.scoreboardManager).getNewScoreboard();
        plugin.objective = plugin.scoreboard.registerNewObjective(ChatColor.BOLD + "Man10Server", "Dummy");
        plugin.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        plugin.objective.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "資源サーバー");

        plugin.blocks = plugin.scoreboard.registerNewTeam("blocks");
        plugin.blocks.addEntry(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "総採掘数 : ");
        plugin.blocks.setSuffix(ChatColor.YELLOW + "" + ChatColor.BOLD + plugin.allBlocks.get(player.getUniqueId()) + "" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " ブロック");

        plugin.noticeStats = plugin.scoreboard.registerNewTeam("noticeStats");
        plugin.noticeStats.addEntry(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "ツール破壊警告 : ");
        plugin.noticeStats.setSuffix("");

        plugin.stopperStats = plugin.scoreboard.registerNewTeam("stopperStats");
        plugin.stopperStats.addEntry(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "ストッパー : ");
        plugin.stopperStats.setSuffix("");


        plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD
                + player.getDisplayName() + ChatColor.WHITE + "" + ChatColor.BOLD + "さんこんにちは！").setScore(14);

        plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD
                + "ランク : " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Miner").setScore(13);

        plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "総採掘数 : ").setScore(12);

        plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "ツール破壊警告 : ").setScore(11);

        plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "ストッパー : ").setScore(10);

        player.setScoreboard(plugin.scoreboard);
    }

    public void updateBlocks(Player player) {
        plugin.statistic.getStatistic(player);
        plugin.blocks.setSuffix(ChatColor.YELLOW + "" + ChatColor.BOLD + plugin.allBlocks.get(player.getUniqueId()) + "" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " ブロック");
        player.setScoreboard(plugin.scoreboard);

    }

    public void updateNoticeStats(Player player) {
        if (plugin.notice_stats.get(player.getUniqueId()).equals(true)) {
            plugin.noticeStats.setSuffix(ChatColor.GREEN + "" + ChatColor.BOLD + "有効");
            player.setScoreboard(plugin.scoreboard);
        } else
            plugin.noticeStats.setSuffix(ChatColor.RED + "" + ChatColor.BOLD + "無効");
        player.setScoreboard(plugin.scoreboard);
    }

    public void updateStopperStats(Player player) {
        if (plugin.stopper_stats.get(player.getUniqueId()).equals(true)) {
            plugin.stopperStats.setSuffix(ChatColor.GREEN + "" + ChatColor.BOLD + "有効");
            player.setScoreboard(plugin.scoreboard);
        } else
            plugin.stopperStats.setSuffix(ChatColor.RED + "" + ChatColor.BOLD + "無効");
        player.setScoreboard(plugin.scoreboard);
    }
}
