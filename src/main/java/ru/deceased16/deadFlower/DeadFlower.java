package ru.deceased16.deadFlower;

import ru.deceased16.deadFlower.commands.FlowerCommands;
import ru.deceased16.deadFlower.compliters.FlowerCommandsCompliter;
import ru.deceased16.deadFlower.events.DeadFlowerEvent;
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
