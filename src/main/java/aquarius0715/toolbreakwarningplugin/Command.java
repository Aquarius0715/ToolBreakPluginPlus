package aquarius0715.toolbreakwarningplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    ToolBreakWarningPlugin plugin;


    public Command(ToolBreakWarningPlugin plugin) {
        this.plugin = plugin;
    }


    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("tbw")) {
            if (!(sender instanceof Player)) {
                Player player = (Player) sender;
                player.sendMessage("You cannot this");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(plugin.prefix + "===============ToolBreakWarningPlugin===============");
                sender.sendMessage(plugin.prefix + "このプラグインはツールの耐久値があといくつなのか、");
                sender.sendMessage(plugin.prefix + "壊れそうになると音で知らせてくれるプラグインです。");
                sender.sendMessage(plugin.prefix + "デフォルトではonになっています。");
                sender.sendMessage(plugin.prefix + "</tbw>: この説明画面を開きます。");
                sender.sendMessage(plugin.prefix + "</tb stopper>: ストッパー機能の有効・無効を指定します。");
                sender.sendMessage(plugin.prefix + "</tbw on>: このプラグインを使用します。");
                sender.sendMessage(plugin.prefix + "</tbw off>: このプラグインの使用をやめます。");
                if (sender.hasPermission("admin")) {
                    sender.sendMessage(plugin.prefix + "</tbw enable>: このプラグインを有効化にします。");
                    sender.sendMessage(plugin.prefix + "</tbw disable>: このプラグインを無効化します。");
                    sender.sendMessage(plugin.prefix + "</tbw reload>: プラグインをリロードします。 ");
                }
                sender.sendMessage(plugin.prefix + "===============ToolBreakWarningPlugin===============");
            }

            if (args.length == 1) {
                Player player = (Player) sender;

                if (args[0].equalsIgnoreCase("on")) {
                    if (!plugin.plugin_stats) {
                        sender.sendMessage(plugin.prefix + "プラグインは停止しています。");
                        return false;
                    }
                    plugin.notice_stats.put(player.getUniqueId(), true);
                    player.sendMessage(plugin.prefix + ChatColor.GREEN + "" + ChatColor.BOLD + "通知機能をオンにしました。");
                    plugin.ScoreBoard.updateNoticeStats(player);
                    return true;
                }
                if (args[0].equalsIgnoreCase("off")) {
                    if (!plugin.plugin_stats) {
                        sender.sendMessage(plugin.prefix + "プラグインは停止しています。");
                        return false;
                    }
                    plugin.notice_stats.put(player.getUniqueId(), false);
                    player.sendMessage(plugin.prefix + ChatColor.RED + "" + ChatColor.BOLD + "通知機能をオフにしました。");
                    plugin.ScoreBoard.updateNoticeStats(player);
                    return false;
                }
                if (args[0].equalsIgnoreCase("disable")) {
                    if (!player.hasPermission("admin")) {
                        sender.sendMessage(plugin.prefix + "あなたはこのコマンドを使うことができません。");
                        return false;
                    }
                    if (!plugin.plugin_stats) {
                        sender.sendMessage(plugin.prefix + "プラグインはすでに無効にされています。");
                        return false;
                    } else {
                        plugin.onDisable();
                        sender.sendMessage(plugin.prefix + "プラグインを無効にしました。");
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("enable")) {
                    if (plugin.plugin_stats && !player.hasPermission("admin")) {
                        sender.sendMessage(plugin.prefix + "あなたはこのコマンドを使うことができません。");
                        return false;
                    }
                    if (plugin.plugin_stats) {
                        sender.sendMessage(plugin.prefix + "プラグインはすでに有効にされています。");
                        return true;
                    }
                }
                if (!plugin.plugin_stats) {
                    plugin.onEnable();
                    sender.sendMessage(plugin.prefix + "プラグインを有効にしました。");
                    return true;
                }

                if (args[0].equalsIgnoreCase("stopper")) {
                    if (this.plugin.stopper_stats.containsValue(true)) {
                        sender.sendMessage(plugin.prefix + "ストッパー機能をオフにしました。");
                        this.plugin.stopper_stats.put(((Player) sender).getUniqueId(), false);
                        plugin.ScoreBoard.updateStopperStats(player);
                    } else {
                        sender.sendMessage(plugin.prefix + "ストッパー機能をオンにしました。");
                        this.plugin.stopper_stats.put(((Player) sender).getUniqueId(), true);
                        plugin.ScoreBoard.updateStopperStats(player);
                    }
                    return true;
                }

                if (args[0].equalsIgnoreCase("reload")) {
                    if (!sender.hasPermission("admin")) {
                        sender.sendMessage(plugin.prefix + "あなたはこのコマンドを使うことができません。");
                        return false;
                    } else {
                        plugin.reloadConfig();
                        sender.sendMessage(plugin.prefix + "リロードしました。");
                    }
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
