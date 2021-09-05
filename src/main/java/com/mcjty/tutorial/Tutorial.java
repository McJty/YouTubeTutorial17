package com.mcjty.tutorial;

import com.mcjty.setup.ClientSetup;
import com.mcjty.setup.Config;
import com.mcjty.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Tutorial.MODID)
public class Tutorial {

    public static final String MODID = "tutorial";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    // Order of initialization:
    // At setup:
    //   1. Registration
    //   2. Config reading (for client + common)
    //   3. FMLCommonSetupEvent
    // After world load:
    //   4. Config reading for server

    public Tutorial() {
        Registration.init();
        Config.init();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(ClientSetup::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
}
