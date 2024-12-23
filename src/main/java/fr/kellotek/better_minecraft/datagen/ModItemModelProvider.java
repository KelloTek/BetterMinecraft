package fr.kellotek.better_minecraft.datagen;

import fr.kellotek.better_minecraft.BetterMinecraft;
import fr.kellotek.better_minecraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BetterMinecraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Hammer
        handheldItem(ModItems.WOODEN_HAMMER.get());
        handheldItem(ModItems.STONE_HAMMER.get());
        handheldItem(ModItems.IRON_HAMMER.get());
        handheldItem(ModItems.GOLDEN_HAMMER.get());
        handheldItem(ModItems.DIAMOND_HAMMER.get());
        handheldItem(ModItems.NETHERITE_HAMMER.get());
    }
}
