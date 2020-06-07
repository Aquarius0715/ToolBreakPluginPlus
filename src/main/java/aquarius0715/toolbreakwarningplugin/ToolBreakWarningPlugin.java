package aquarius0715.toolbreakwarningplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.*;


public final class ToolBreakWarningPlugin extends JavaPlugin {

    Map<UUID, Boolean> notice_stats = new HashMap<UUID, Boolean>();
    Map<UUID, Boolean> stopper_stats = new HashMap<UUID, Boolean>();
    Map<UUID, Integer> allBlocks = new HashMap<UUID, Integer>();
    Map<UUID, Boolean> scoreboard_stats = new HashMap<UUID, Boolean>();

    ScoreboardManager scoreboardManager;
    Scoreboard scoreboard;
    Objective objective;
    Team blocks;
    Team noticeStats;
    Team stopperStats;

    ScoreBoard ScoreBoard = new ScoreBoard(this);
    SoundData SoundData = new SoundData(this);
    Statistic statistic = new Statistic(this);

    String sound_config;

    int allBrockIn = 0;

    Random random = new Random();


    String prefix = ChatColor.BOLD + "[" + ChatColor.GREEN + "ToolBreakWarning" + ChatColor.WHITE + "" + ChatColor.BOLD + "] ";
    boolean plugin_stats = false;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        sound_config = this.getConfig().getString("sound");

        this.getServer().getPluginManager().registerEvents(new Event(this), this);
        Objects.requireNonNull(getCommand("tbw")).setExecutor(new Command(this));

        plugin_stats = true;
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        plugin_stats = false;
        // Plugin shutdown logic
    }


}