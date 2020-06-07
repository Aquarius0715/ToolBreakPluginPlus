package aquarius0715.toolbreakwarningplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
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

        plugin.ranks = plugin.scoreboard.registerNewTeam("ranks");
        plugin.ranks.addEntry(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "現在のランク : ");
        plugin.ranks.setSuffix(plugin.rank.get(player.getUniqueId()) + "");

        plugin.nextRank = plugin.scoreboard.registerNewTeam("nextRank");
        plugin.nextRank.addEntry(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "次のランクまで : " + ChatColor.YELLOW + "" + ChatColor.BOLD);
        plugin.nextRank.setSuffix(plugin.remainRank.get(player.getUniqueId()) + "" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " ブロック");

        plugin.achieveInt = plugin.scoreboard.registerNewTeam("achieveInt");
        plugin.achieveInt.addEntry(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "int最大値まで : " + ChatColor.YELLOW + "" + ChatColor.BOLD);
        plugin.achieveInt.setSuffix(plugin.remainRank.get(player.getUniqueId()) + "" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " ブロック");

        plugin.hourminedBlocks = plugin.scoreboard.registerNewTeam("hourmined");
        plugin.hourminedBlocks.addEntry(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "1時間に掘れる理論値は : " + ChatColor.YELLOW + "" + ChatColor.BOLD);
        plugin.hourminedBlocks.setSuffix(plugin.hourMinedBlocks.get(player.getUniqueId()) * 60 + "" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "ブロック");

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

        plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "現在のランク : ").setScore(13);

        if (Objects.equals(plugin.rank.get(player.getUniqueId()), ChatColor.BLACK + "" + ChatColor.BOLD + "" + ChatColor.MAGIC
                + "a" + ChatColor.BLACK + ChatColor.BOLD
                + "Destroyer" + ChatColor.BLACK + ChatColor.BOLD + ChatColor.MAGIC
                + "a")) {
            plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "int最大値まで : " + ChatColor.YELLOW + "" + ChatColor.BOLD).setScore(12);
        } else {

            plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "次のランクまで : " + ChatColor.YELLOW + "" + ChatColor.BOLD).setScore(12);
        }

        plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "1時間に掘れる理論値は : " + ChatColor.YELLOW + "" + ChatColor.BOLD).setScore(11);

        plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "総採掘数 : ").setScore(10);

        plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "ツール破壊警告 : ").setScore(9);

        plugin.objective.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "ストッパー : ").setScore(8);

        player.setScoreboard(plugin.scoreboard);
    }

    public void updateBlocks(Player player) {
        plugin.statistic.getStatistic(player);
        plugin.blocks.setSuffix(ChatColor.YELLOW + "" + ChatColor.BOLD + plugin.allBlocks.get(player.getUniqueId()) + "" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " ブロック");
        player.setScoreboard(plugin.scoreboard);

    }

    public void updateRank(Player player) {
        plugin.rankData.RankData(player);
        plugin.ranks.setSuffix(plugin.rank.get(player.getUniqueId()));
        player.setScoreboard(plugin.scoreboard);
    }

    public void updateHourMineBlocks(Player player) {
        plugin.hourminedBlocks.setSuffix(plugin.hourMinedBlocks.get(player.getUniqueId()) * 60 + "");
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

    public void updateScoreBoard(Player player, BlockBreakEvent event) {
        final int[] time = {120};
        plugin.hourMinedBlocks.put(event.getPlayer().getUniqueId(), plugin.hourMinedBlocks.get(event.getPlayer().getUniqueId()) + 1);

        new BukkitRunnable() {
            @Override
            public void run() {

                if (time[0] == 0) {
                    plugin.hourMinedBlocks.put(player.getUniqueId(), 0);
                    time[0] = 120;
                }
                if (plugin.scoreboard_stats.get(player.getUniqueId()).equals(true)) {
                    plugin.statistic.getStatistic(player);
                    createScoreBoard(player);
                    updateRank(player);
                    updateHourMineBlocks(player);
                    updateBlocks(player);
                    updateNoticeStats(player);
                    updateStopperStats(player);
                } else {
                    player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
                }
                time[0]--;
            }
        }.runTaskTimer(plugin, 0, plugin.getConfig().getLong("update_speed"));
    }

    public void updateScoreBoardJoinEvent(Player player, PlayerJoinEvent event) {
        final int[] time = {120};
        new BukkitRunnable() {
            @Override
            public void run() {

                if (time[0] == 0) {
                    plugin.hourMinedBlocks.put(player.getUniqueId(), 0);
                    time[0] = 120;
                }
                if (plugin.scoreboard_stats.get(player.getUniqueId()).equals(true)) {
                    plugin.statistic.getStatistic(player);
                    createScoreBoard(player);
                    updateRank(player);
                    updateHourMineBlocks(player);
                    updateBlocks(player);
                    updateNoticeStats(player);
                    updateStopperStats(player);
                } else {
                    player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
                }
                time[0]--;
            }
        }.runTaskTimer(plugin, 0, plugin.getConfig().getLong("update_speed"));
    }

    public void updateScoreBoardDamageEvent(Player player, PlayerItemDamageEvent event) {
        final int[] time = {120};
        new BukkitRunnable() {
            @Override
            public void run() {


                if (time[0] == 0) {
                    plugin.hourMinedBlocks.put(player.getUniqueId(), 0);
                    time[0] = 120;
                }
                if (plugin.scoreboard_stats.get(player.getUniqueId()).equals(true)) {
                    plugin.statistic.getStatistic(player);
                    createScoreBoard(player);
                    updateRank(player);
                    updateHourMineBlocks(player);
                    updateBlocks(player);
                    updateNoticeStats(player);
                    updateStopperStats(player);
                } else {
                    player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
                }
                time[0]--;
            }
        }.runTaskTimer(plugin, 0, plugin.getConfig().getLong("update_speed"));
    }
}
