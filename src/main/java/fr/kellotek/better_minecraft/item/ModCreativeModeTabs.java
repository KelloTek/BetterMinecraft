package fr.kellotek.better_minecraft.item;

import fr.kellotek.better_minecraft.BetterMinecraft;
import fr.kellotek.better_minecraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BetterMinecraft.MODID);

    public static final Supplier<CreativeModeTab> TOOLS_ITEMS_TAB = CREATIVE_MODE_TABS.register("tools_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.NETHERITE_HAMMER.get()))
                    .title(Component.translatable("creativetab." + BetterMinecraft.MODID + ".tools_items"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        // Hammer
                        output.accept(ModItems.WOODEN_HAMMER);
                        output.accept(ModItems.STONE_HAMMER);
                        output.accept(ModItems.IRON_HAMMER);
                        output.accept(ModItems.GOLDEN_HAMMER);
                        output.accept(ModItems.DIAMOND_HAMMER);
                        output.accept(ModItems.NETHERITE_HAMMER);

                        // Block
                        output.accept(ModBlocks.DISASSEMBLY_TABLE);
                    })).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
