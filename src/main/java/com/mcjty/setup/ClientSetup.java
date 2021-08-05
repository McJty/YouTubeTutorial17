package com.mcjty.setup;

import com.mcjty.blocks.GeneratorScreen;
import com.mcjty.items.TestItem;
import com.mcjty.tutorial.Tutorial;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static final ResourceLocation DISTANCE_PROPERTY = new ResourceLocation(Tutorial.MODID, "distance");

    public static void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.GENERATOR_CONTAINER.get(), GeneratorScreen::new);
            ItemBlockRenderTypes.setRenderLayer(Registration.DEMO.get(), RenderType.translucent());
            initTestItemOverrides();
        });
    }

    public static void initTestItemOverrides() {
        TestItem item = Registration.TESTITEM.get();
        ItemProperties.register(item, DISTANCE_PROPERTY,
                (stack, level, entity, damage) -> item.getDistance(stack));
    }

}
