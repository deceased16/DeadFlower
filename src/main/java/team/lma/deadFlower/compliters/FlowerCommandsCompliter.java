package team.lma.deadFlower.compliters;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class FlowerCommandsCompliter implements TabCompleter {
    public FlowerCommandsCompliter() {
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? List.of("set", "clear", "get", "help") : List.of();
    }
}
