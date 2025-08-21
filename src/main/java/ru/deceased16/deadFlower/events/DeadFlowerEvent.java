package ru.deceased16.deadFlower.events;

import ru.deceased16.deadFlower.DeadFlower;
import ru.deceased16.deadFlower.collections.flowercollection.Flower;
import ru.deceased16.deadFlower.collections.flowercollection.FlowerCollection;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DeadFlowerEvent implements Listener {
    public DeadFlowerEvent() {
    }

    @EventHandler
    private void PlayerDied(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        if (player.getKiller() != null && playerData.has(NamespacedKey.fromString("flower-type"), PersistentDataType.STRING) && player.getKiller().getPersistentDataContainer().has(NamespacedKey.fromString("flower-type"), PersistentDataType.STRING)) {
            Flower.InitializeFlowerAtPlayer(Material.getMaterial((String)playerData.get(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING)), player);
            playerData.remove(NamespacedKey.fromString("flower-type"));
            playerData.remove(NamespacedKey.fromString("flower-material"));
        }

    }

    @EventHandler
    private void PlayerPlacedFlower(BlockPlaceEvent event) {
        ItemStack placedBlockItem = event.getItemInHand();
        PersistentDataContainer itemData = placedBlockItem.getItemMeta().getPersistentDataContainer();
        if (itemData.has(NamespacedKey.fromString("player"), PersistentDataType.STRING)) {
            List<Player> players = new ArrayList(Bukkit.getOnlinePlayers());
            Player flowerPlayer = null;
            PotionEffectType flowerEffectType = PotionEffectType.getByName((String)itemData.get(NamespacedKey.fromString("effect-type"), PersistentDataType.STRING));
            event.getPlayer().sendMessage(new String[0]);
            AreaEffectCloud flowerEffectCloud = (AreaEffectCloud)event.getBlockPlaced().getLocation().getWorld().spawn(event.getBlockPlaced().getLocation().add((double)0.5F, (double)0.0F, (double)0.5F), EntityType.AREA_EFFECT_CLOUD.getEntityClass());
            flowerEffectCloud.setRadius(0.5F);
            flowerEffectCloud.setDuration(69);
            flowerEffectCloud.setParticle(Particle.CLOUD);
            flowerEffectCloud.addCustomEffect(new PotionEffect(flowerEffectType, 35, 1, false, false), true);

            for(int i = 0; i < players.size(); ++i) {
                if (((Player)players.get(i)).getName().equals(itemData.get(NamespacedKey.fromString("player"), PersistentDataType.STRING))) {
                    flowerPlayer = (Player)players.get(i);
                    break;
                }
            }

            flowerPlayer.teleport(event.getBlockPlaced().getLocation().add((double)0.5F, (double)0.0F, (double)0.5F));
        }
    }

    @EventHandler
    private void PlayerJoined(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.getPersistentDataContainer().has(NamespacedKey.fromString("flower-type"), PersistentDataType.STRING)) {
            event.getPlayer().sendMessage("You can place a flower using the /flower set command, and you will receive a free effect.");
        }

    }

    @EventHandler
    private void PlayerRespawned(PlayerRespawnEvent event) {
        final Player player = event.getPlayer();
        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(DeadFlower.class), new Runnable() {
            public void run() {
                if (player.getPersistentDataContainer().has(NamespacedKey.fromString("flower-type"), PersistentDataType.STRING)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(FlowerCollection.getFlowerEffectByMaterial(Material.getMaterial((String)player.getPersistentDataContainer().get(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING))).getEffectType().getName()), 9999999, FlowerCollection.getFlowerEffectByMaterial(Material.getMaterial((String)player.getPersistentDataContainer().get(NamespacedKey.fromString("flower-material"), PersistentDataType.STRING))).getAmplifier(), false, true));
                }

            }
        }, 20L);
    }
}