package com.mcjty.setup;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {

    public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec SERVER_CONFIG;

    public static ForgeConfigSpec.IntValue GENERATOR_PERTICK;
    public static ForgeConfigSpec.IntValue GENERATOR_SENDPERTICK;
    public static ForgeConfigSpec.IntValue GENERATOR_CAPACITY;

    public static ForgeConfigSpec.IntValue DEMO_USEPERTICK;
    public static ForgeConfigSpec.IntValue DEMO_CAPACITY;
    public static ForgeConfigSpec.DoubleValue DEMO_PARTICLE_YSPEED;

    public static void init() {
        initServer();
        initClient();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
    }

    private static void initServer() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Generator settings").push("generator");
        GENERATOR_PERTICK = builder
                .comment("How much FE the generator can generate per tick")
                .defineInRange("generatePerTick", 50, 1, Integer.MAX_VALUE);
        GENERATOR_SENDPERTICK = builder
                .comment("How much FE the generator can send to adjacent blocks per tick")
                .defineInRange("sendPerTick", 1000, 1, Integer.MAX_VALUE);
        GENERATOR_CAPACITY = builder
                .comment("How much FE the generator can store")
                .defineInRange("capacity", 100000, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Demo settings").push("demo");
        DEMO_USEPERTICK = builder
                .comment("How much FE the demo block will use per tick")
                .defineInRange("usePerTick", 10, 1, Integer.MAX_VALUE);
        DEMO_CAPACITY = builder
                .comment("How much FE the demo block can store")
                .defineInRange("capacity", 1000, 1, Integer.MAX_VALUE);
        builder.pop();

        SERVER_CONFIG = builder.build();
    }

    private static void initClient() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Demo settings").push("demo");
        DEMO_PARTICLE_YSPEED = builder
                .comment("The vertical speed of the particles")
                .defineInRange("yspeed", 0.0, -1000.0, 1000.0);
        builder.pop();

        CLIENT_CONFIG = builder.build();
    }

}
