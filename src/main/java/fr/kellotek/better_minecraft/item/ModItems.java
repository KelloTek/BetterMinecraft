package fr.kellotek.better_minecraft.item;

import fr.kellotek.better_minecraft.BetterMinecraft;
import fr.kellotek.better_minecraft.item.custom.HammerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BetterMinecraft.MODID);

    // Wooden Hammer
    public static final DeferredItem<HammerItem> WOODEN_HAMMER = ITEMS.register("wooden_hammer",
            () -> new HammerItem(Tiers.WOOD, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.WOOD, 5f, -2.8f))));

    // Stone Hammer
    public static final DeferredItem<HammerItem> STONE_HAMMER = ITEMS.register("stone_hammer",
            () -> new HammerItem(Tiers.STONE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.STONE, 6f, -2.8f))));

    // Iron Hammer
    public static final DeferredItem<HammerItem> IRON_HAMMER = ITEMS.register("iron_hammer",
            () -> new HammerItem(Tiers.IRON, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.IRON, 5f, -2.8f))));

    // Gold Hammer
    public static final DeferredItem<HammerItem> GOLDEN_HAMMER = ITEMS.register("golden_hammer",
            () -> new HammerItem(Tiers.GOLD, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.GOLD, 5f, -2.8f))));

    // Diamond Hammer
    public static final DeferredItem<HammerItem> DIAMOND_HAMMER = ITEMS.register("diamond_hammer",
            () -> new HammerItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.DIAMOND, 4f, -2.8f))));

    // Netherite Hammer
    public static final DeferredItem<HammerItem> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
            () -> new HammerItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.NETHERITE, 4f, -2.8f))));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}