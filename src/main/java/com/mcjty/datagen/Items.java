package com.mcjty.datagen;

import com.mcjty.setup.Registration;
import com.mcjty.tutorial.Tutorial;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.mcjty.setup.ClientSetup.DISTANCE_PROPERTY;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Tutorial.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Example generation for a simple textured item:
        //        singleTexture(
        //                Registration.TESTITEM.get().getRegistryName().getPath(),
        //                new ResourceLocation("item/handheld"),
        //                "layer0",
        //                new ResourceLocation(Tutorial.MODID, "item/firstitem"));

        getBuilder(Registration.TESTITEM.get().getRegistryName().getPath())
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/firstitem0")
                .override().predicate(DISTANCE_PROPERTY, 0).model(createTestModel(0)).end()
                .override().predicate(DISTANCE_PROPERTY, 1).model(createTestModel(1)).end()
                .override().predicate(DISTANCE_PROPERTY, 2).model(createTestModel(2)).end()
                .override().predicate(DISTANCE_PROPERTY, 3).model(createTestModel(3)).end();

        withExistingParent(Registration.GENERATOR_ITEM.get().getRegistryName().getPath(), new ResourceLocation(Tutorial.MODID, "block/generator"));
    }

    private ItemModelBuilder createTestModel(int suffix) {
        return getBuilder("testitem" + suffix).parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/firstitem" + suffix);
    }

}
