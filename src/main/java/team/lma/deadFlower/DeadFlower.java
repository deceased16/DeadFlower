package team.lma.deadFlower;

import team.lma.deadFlower.commands.FlowerCommands;
import team.lma.deadFlower.compliters.FlowerCommandsCompliter;
import team.lma.deadFlower.events.DeadFlowerEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeadFlower extends JavaPlugin {
    public DeadFlower() {
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new DeadFlowerEvent(), this);
        this.getCommand("flower").setExecutor(new FlowerCommands());
        this.getCommand("flower").setTabCompleter(new FlowerCommandsCompliter());
    }

    public void onDisable() {
    }
}
