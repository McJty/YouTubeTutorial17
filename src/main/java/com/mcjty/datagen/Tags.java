package com.mcjty.datagen;

import com.mcjty.setup.Registration;
import com.mcjty.tutorial.Tutorial;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Tags extends BlockTagsProvider {

    public Tags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Tutorial.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.GENERATOR.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Registration.GENERATOR.get());
    }

    @Override
    public String getName() {
        return "Tutorial Tags";
    }
}
