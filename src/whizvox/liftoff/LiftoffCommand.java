package whizvox.liftoff;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LiftoffCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("liftoff.help")) {
            if (args.length > 0) {
                switch (args[0].toLowerCase()) {
                    case "help":
                        sender.sendMessage(HELP_MESSAGES);
                        break;
                    case "version":
                        sender.sendMessage(VERSION_MESSAGES);
                        break;
                    default:
                        sender.sendMessage(command.getUsage());
                }
            } else {
                sender.sendMessage(command.getUsage());
            }
            return true;
        }
        return false;
    }

    private static final String[] HELP_MESSAGES = new String[] {
            ChatColor.AQUA + "Help Information for Liftoff",
            "This is a very lightweight plugin that allows the usage of teleportation elevators.",
            "For thorough documentation, check out the wiki: " + ChatColor.BLUE + "http://github.com/whizvox/Liftoff/wiki"
    };

    private static final String[] VERSION_MESSAGES = new String[] {
            ChatColor.AQUA + "Version Information for Liftoff",
            "Version: " + ChatColor.BLUE + Liftoff.VERSION,
            "Author: " + ChatColor.BLUE + "whizvox " + ChatColor.RESET + "( http://whizvox.weebly.com/ )",
            "Source Code: " + ChatColor.BLUE + "http://github.com/whizvox/Liftoff"
    };

}
