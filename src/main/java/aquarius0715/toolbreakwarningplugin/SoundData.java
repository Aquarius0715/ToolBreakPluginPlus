package aquarius0715.toolbreakwarningplugin;

import org.bukkit.Sound;
import org.bukkit.entity.Player;


public class SoundData {

    ToolBreakWarningPlugin plugin;

    public SoundData(ToolBreakWarningPlugin plugin) {

        this.plugin = plugin;
    }

    public void SoundData(Player player) {

        for (Sound sound : Sound.values()) {
            if (sound.toString().equalsIgnoreCase(plugin.getConfig().getString("sound"))) {
                player.playSound(player.getLocation(), sound, 1.0F, 8.0F);
            }
        }
    }
}
