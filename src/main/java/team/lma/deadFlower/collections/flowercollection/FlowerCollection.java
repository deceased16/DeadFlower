package team.lma.deadFlower.collections.flowercollection;

import team.lma.deadFlower.collections.effectcollection.FlowerEffect;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

public class FlowerCollection {
    public static List<Flower> collection;

    public FlowerCollection() {
    }

    public static List<String> getNames() {
        Flower[] currentCollection = (Flower[])collection.toArray(new Flower[collection.size()]);
        ArrayList<String> namesList = new ArrayList(collection.size());

        for(int i = 0; i < currentCollection.length; ++i) {
            namesList.add(currentCollection[i].getDisplayName());
        }

        return namesList;
    }

    public static List<Material> getMaterials() {
        Flower[] currentCollection = (Flower[])collection.toArray(new Flower[collection.size()]);
        ArrayList<Material> materialList = new ArrayList(collection.size());

        for(int i = 0; i < currentCollection.length; ++i) {
            materialList.add(currentCollection[i].getMaterial());
        }

        return materialList;
    }

    public static FlowerEffect getFlowerEffectByMaterial(Material _material) {
        int flowerIndex = getMaterials().indexOf(_material);
        return ((Flower)collection.get(flowerIndex)).getFlowerEffect();
    }

    static {
        collection = List.of(
                new Flower(Material.DANDELION, new FlowerEffect(PotionEffectType.SPEED, 0)),
                new Flower(Material.POPPY, new FlowerEffect(PotionEffectType.REGENERATION, 0)),
                new Flower(Material.BLUE_ORCHID, new FlowerEffect(PotionEffectType.WATER_BREATHING, 1)),
                new Flower(Material.ALLIUM, new FlowerEffect(PotionEffectType.HEALTH_BOOST, 1)),
                new Flower(Material.AZURE_BLUET, new FlowerEffect(PotionEffectType.RESISTANCE, 1)),
                new Flower(Material.RED_TULIP, new FlowerEffect(PotionEffectType.REGENERATION, 1)),
                new Flower(Material.ORANGE_TULIP, new FlowerEffect(PotionEffectType.FIRE_RESISTANCE, 1)),
                new Flower(Material.WHITE_TULIP, new FlowerEffect(PotionEffectType.SPEED, 2)),
                new Flower(Material.PINK_TULIP, new FlowerEffect(PotionEffectType.STRENGTH, 1)),
                new Flower(Material.CORNFLOWER, new FlowerEffect(PotionEffectType.NIGHT_VISION, 0)),
                new Flower(Material.LILY_OF_THE_VALLEY, new FlowerEffect(PotionEffectType.RESISTANCE, 2)),
                new Flower(Material.ROSE_BUSH, new FlowerEffect(PotionEffectType.HASTE, 2)),
                new Flower(Material.PEONY, new FlowerEffect(PotionEffectType.JUMP_BOOST, 3)),
                new Flower(Material.LILAC, new FlowerEffect(PotionEffectType.DOLPHINS_GRACE,1)),
                new Flower(Material.SUNFLOWER, new FlowerEffect(PotionEffectType.LUCK,1)),
                new Flower(Material.OXEYE_DAISY, new FlowerEffect(PotionEffectType.STRENGTH,1)),
                new Flower(Material.TORCHFLOWER, new FlowerEffect(PotionEffectType.FIRE_RESISTANCE,2)),
                new Flower(Material.PITCHER_PLANT, new FlowerEffect(PotionEffectType.ABSORPTION,1)),
                new Flower(Material.OPEN_EYEBLOSSOM, new FlowerEffect(PotionEffectType.RESISTANCE,3)),
                new Flower(Material.CLOSED_EYEBLOSSOM, new FlowerEffect(PotionEffectType.RESISTANCE,0)),
                new Flower(Material.WITHER_ROSE, new FlowerEffect(PotionEffectType.WITHER,99)) // I don’t recommend using! (о_О)
        );
    }
}