package fr.kellotek.better_minecraft.item;

import fr.kellotek.better_minecraft.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {

    public static final Tier TEST = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_TEST_TOOL,
            1400, 4f, 3f, 28, () -> Ingredient.of(ModItems.WOODEN_HAMMER));
}
