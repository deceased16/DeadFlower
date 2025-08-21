package ru.deceased16.deadFlower.commands;

import ru.deceased16.deadFlower.collections.flowercollection.Flower;
import ru.deceased16.deadFlower.collections.flowercollection.FlowerCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FlowerCommands implements CommandExecutor {
    public FlowerCommands() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        if (args.length > 0) {
            switch (args[0]) {
                case "set":
                    if (!FlowerCollection.getMaterials().contains(player.getItemInHand().getType())) {
                        return false;
                    }

                    if (player.getItemInHand().getType() == Material.getMaterial((String)playerData.get(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING))) {
                        player.sendMessage("You already set this flower");
                        return true;
                    }

                    int flowerIndex = FlowerCollection.getMaterials().indexOf(player.getItemInHand().getType());
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                    if (playerData.has(NamespacedKey.fromString("flower-type"), PersistentDataType.STRING)) {
                        player.getInventory().addItem(new ItemStack[]{Flower.InitializeFlowerStack(Material.getMaterial((String)playerData.get(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING)), player)});
                        player.removePotionEffect(FlowerCollection.getFlowerEffectByMaterial(Material.getMaterial((String)playerData.get(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING))).getEffectType());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1, false, false));
                    }

                    playerData.set(NamespacedKey.fromString("flower-type"), PersistentDataType.STRING, ((Flower)FlowerCollection.collection.get(flowerIndex)).getDisplayName());
                    playerData.set(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING, ((Flower)FlowerCollection.collection.get(flowerIndex)).getMaterial().toString());
                    player.addPotionEffect(new PotionEffect(FlowerCollection.getFlowerEffectByMaterial((Material)FlowerCollection.getMaterials().get(flowerIndex)).getEffectType(), 9999999, FlowerCollection.getFlowerEffectByMaterial((Material)FlowerCollection.getMaterials().get(flowerIndex)).getAmplifier(), false, true));
                    String var10001 = (String)playerData.get(NamespacedKey.fromString("flower-type"), PersistentDataType.STRING);
                    sender.sendMessage("Set your flower to " + var10001);
                    break;
                case "clear":
                    sender.sendMessage("Clearing your thing");
                    if (!playerData.has(NamespacedKey.fromString("flower-type"), PersistentDataType.STRING)) {
                        player.sendMessage("No flower set");
                        return true;
                    }

                    player.removePotionEffect(FlowerCollection.getFlowerEffectByMaterial(Material.getMaterial((String)playerData.get(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING))).getEffectType());
                    playerData.remove(NamespacedKey.fromString("flower-type"));
                    playerData.remove(NamespacedKey.fromString("flower-material"));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 2, false, false));
                    sender.sendMessage("All clean");
                    break;
                case "get":
                    if (!playerData.has(NamespacedKey.fromString("flower-type"), PersistentDataType.STRING)) {
                        sender.sendMessage("No flower set");
                        return true;
                    }

                    player.getInventory().addItem(new ItemStack[]{Flower.InitializeFlowerStack(Material.getMaterial((String)playerData.get(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING)), player)});
                    player.removePotionEffect(FlowerCollection.getFlowerEffectByMaterial(Material.getMaterial((String)playerData.get(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING))).getEffectType());
                    player.removePotionEffect(FlowerCollection.getFlowerEffectByMaterial(Material.getMaterial((String)playerData.get(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING))).getEffectType());
                    playerData.remove(NamespacedKey.fromString("flower-type"));
                    playerData.remove(NamespacedKey.fromString("flower-material"));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1, false, false));
                    break;
                case "help":
                    sender.sendMessage(Bukkit.getHelpMap().getHelpTopic("/flower").getFullText(player));
                    break;
                default:
                    return false;
            }

            return true;
        } else {
            return false;
        }
    }
}
