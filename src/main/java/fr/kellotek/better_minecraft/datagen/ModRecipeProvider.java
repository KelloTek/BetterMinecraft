package fr.kellotek.better_minecraft.datagen;

import fr.kellotek.better_minecraft.BetterMinecraft;
import fr.kellotek.better_minecraft.block.ModBlocks;
import fr.kellotek.better_minecraft.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> WOODEN_BLASTING = List.of(ModItems.WOODEN_HAMMER);
        List<ItemLike> IRON_SMELTING = List.of(ModItems.IRON_HAMMER);
        List<ItemLike> GOLD_SMELTING = List.of(ModItems.GOLDEN_HAMMER);

        // Wooden Hammer
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.WOODEN_HAMMER.get())
                .pattern("WWW")
                .pattern("WSW")
                .pattern(" S ")
                .define('W', Ingredient.of(Blocks.ACACIA_PLANKS, Blocks.BAMBOO_PLANKS, Blocks.BIRCH_PLANKS, Blocks.CHERRY_PLANKS, Blocks.CRIMSON_PLANKS,
                        Blocks.DARK_OAK_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.MANGROVE_PLANKS, Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.WARPED_PLANKS))
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK)).save(recipeOutput);

        // Stone Hammer
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STONE_HAMMER.get())
                .pattern("CCC")
                .pattern("CSC")
                .pattern(" S ")
                .define('C', Ingredient.of(Blocks.COBBLESTONE, Blocks.COBBLED_DEEPSLATE, Blocks.BLACKSTONE))
                .define('S', Items.STICK)
                .unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE))
                .unlockedBy("has_cobbled_deepslate", has(Blocks.COBBLED_DEEPSLATE))
                .unlockedBy("has_blackstone", has(Blocks.BLACKSTONE)).save(recipeOutput);

        // Iron Hammer
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.IRON_HAMMER.get())
                .pattern("III")
                .pattern("ISI")
                .pattern(" S ")
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(recipeOutput);

        // Golden Hammer
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.GOLDEN_HAMMER.get())
                .pattern("GGG")
                .pattern("GSG")
                .pattern(" S ")
                .define('G', Items.GOLD_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(recipeOutput);

        // Diamond Hammer
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DIAMOND_HAMMER.get())
                .pattern("DDD")
                .pattern("DSD")
                .pattern(" S ")
                .define('D', Items.DIAMOND)
                .define('S', Items.STICK)
                .unlockedBy("has_diamond", has(Items.DIAMOND)).save(recipeOutput);

        // Netherite Hammer
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(ModItems.DIAMOND_HAMMER),
                Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.TOOLS,
                ModItems.NETHERITE_HAMMER.get()
                ).unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(recipeOutput, "netherite_hammer_smithing");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.DISASSEMBLY_TABLE.get(), 2)
                .requires(ModBlocks.DISASSEMBLY_TABLE)
                .unlockedBy("has_test_block", has(ModBlocks.DISASSEMBLY_TABLE)).save(recipeOutput);

        // Blasting
        oreBlasting(recipeOutput, WOODEN_BLASTING, RecipeCategory.TOOLS, ModItems.WOODEN_HAMMER.get(), 0.1f, 100, "wooden");

        // Smelting
        oreSmelting(recipeOutput, IRON_SMELTING, RecipeCategory.TOOLS, Items.IRON_NUGGET, 0.1f, 200, "iron");
        oreSmelting(recipeOutput, GOLD_SMELTING, RecipeCategory.TOOLS, Items.GOLD_NUGGET, 0.1f, 200, "gold");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, BetterMinecraft.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
