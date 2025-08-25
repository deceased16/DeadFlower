package team.lma.deadFlower.collections.flowercollection;

import team.lma.deadFlower.collections.effectcollection.FlowerEffect;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class Flower {
    private Material flowerType;
    private String displayName;
    private FlowerEffect flowerEffect;

    Flower(Material _flowerType, FlowerEffect _effect) {
        this.flowerType = _flowerType;
        this.displayName = this.flowerType.getKey().toString();
        this.flowerEffect = _effect;
    }

    public Material getMaterial() {
        return this.flowerType;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public FlowerEffect getFlowerEffect() {
        return this.flowerEffect;
    }

    public static Item InitializeFlowerAtPlayer(Material flowerType, Player player) {
        ItemStack flowerStack = InitializeFlowerStack(flowerType, player);
        return player.getWorld().dropItem(player.getLocation(), flowerStack);
    }

    public static ItemStack InitializeFlowerStack(Material flowerType, Player player) {
        ItemStack flowerStack = new ItemStack(flowerType);
        FlowerEffect flowerEffect = FlowerCollection.getFlowerEffectByMaterial(flowerType);
        ItemMeta flowerMeta = flowerStack.getItemMeta();

        flowerMeta.getPersistentDataContainer().set(NamespacedKey.fromString("player"), PersistentDataType.STRING, player.getDisplayName());
        flowerMeta.getPersistentDataContainer().set(NamespacedKey.fromString("effect-type"), PersistentDataType.STRING, flowerEffect.getEffectType().getName());
        flowerMeta.setDisplayName(flowerMeta.getPersistentDataContainer().get(NamespacedKey.fromString("player"), PersistentDataType.STRING));

        flowerStack.setItemMeta(flowerMeta);
        return flowerStack;
    }
}
