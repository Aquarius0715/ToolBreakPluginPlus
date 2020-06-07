package aquarius0715.toolbreakwarningplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


public final class ToolBreakWarningPlugin extends JavaPlugin {

    Map<UUID, Boolean> notice_stats = new HashMap<UUID, Boolean>();
    Map<UUID, Boolean> stopper_stats = new HashMap<UUID, Boolean>();
    Map<UUID, Integer> allBlocks = new HashMap<UUID, Integer>();
    Map<UUID, Boolean> scoreboard_stats = new HashMap<UUID, Boolean>();
    Map<UUID, String> rank = new HashMap<UUID, String>();
    Map<UUID, Integer> remainRank = new HashMap<UUID, Integer>();
    Map<UUID, Integer> hourMinedBlocks = new HashMap<UUID, Integer>();

    ScoreboardManager scoreboardManager;
    Scoreboard scoreboard;
    Objective objective;
    Team blocks;
    Team noticeStats;
    Team stopperStats;
    Team ranks;
    Team nextRank;
    Team achieveInt;
    Team hourminedBlocks;

    ScoreBoard ScoreBoard = new ScoreBoard(this);
    SoundData SoundData = new SoundData(this);
    Statistic statistic = new Statistic(this);
    RankData rankData = new RankData(this);

    String sound_config;

    int allBrockIn = 0;


    String prefix = ChatColor.BOLD + "[" + ChatColor.GREEN + "ToolBreakWarning" + ChatColor.WHITE + "" + ChatColor.BOLD + "] ";
    boolean plugin_stats;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        sound_config = this.getConfig().getString("sound");

        this.getServer().getPluginManager().registerEvents(new Event(this), this);
        Objects.requireNonNull(getCommand("tbw")).setExecutor(new Command(this));

        plugin_stats = this.getConfig().getBoolean("plugin_stats");
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        plugin_stats = false;
        // Plugin shutdown logic
    }


}