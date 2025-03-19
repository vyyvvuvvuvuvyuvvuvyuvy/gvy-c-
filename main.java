package com.example.grannyandslendrina;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.ModEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.Registry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.world.entity.ai.goal.*;

@Mod.EventBusSubscriber(modid = "grannyandslendrina", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Main {

    public static final String MODID = "grannyandslendrina";
    public static final String NAME = "Granny and Slendrina Mod";
    public static final String VERSION = "1.0";

    // Deferred registers
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    // Items (Spawn Eggs)
    public static final RegistryObject<Item> GRANNY_SPAWN_EGG = ITEMS.register("granny_spawn_egg", () -> new ItemSpawnEgg(GrannyEntity.TYPE, 0xFFFFFF, 0x000000, new Item.Properties()));
    public static final RegistryObject<Item> SLENDRINA_SPAWN_EGG = ITEMS.register("slendrina_spawn_egg", () -> new ItemSpawnEgg(SlendrinaEntity.TYPE, 0x000000, 0xFF0000, new Item.Properties()));

    // Entities (Granny and Slendrina)
    public static final RegistryObject<EntityType<GrannyEntity>> GRANNY_ENTITY = ENTITIES.register("granny", () -> EntityType.Builder.of(GrannyEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).build("granny"));
    public static final RegistryObject<EntityType<SlendrinaEntity>> SLENDRINA_ENTITY = ENTITIES.register("slendrina", () -> EntityType.Builder.of(SlendrinaEntity::new, EntityClassification.MONSTER).sized(0.6F, 2.9F).build("slendrina"));

    // Register to the event bus
    public static void onModLoading(final FMLCommonSetupEvent event) {
        // Register items and entities
        ITEMS.register(event.getModEventBus());
        ENTITIES.register(event.getModEventBus());
    }

    // Granny Entity (Moves like a Zombie)
    public static class GrannyEntity extends EntityZombie {

        public static final EntityType<GrannyEntity> TYPE = EntityType.Builder.of(GrannyEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).build("granny");

        public GrannyEntity(EntityType<? extends EntityZombie> p_i48562_1_, Level p_i48562_2_) {
            super(p_i48562_1_, p_i48562_2_);
        }

        @Override
        public void tick() {
            super.tick();
            // Custom behavior for Granny (like a Zombie)
        }
    }

    // Slendrina Entity (Teleports like an Enderman)
    public static class SlendrinaEntity extends EntityEnderman {

        public static final EntityType<SlendrinaEntity> TYPE = EntityType.Builder.of(SlendrinaEntity::new, EntityClassification.MONSTER).sized(0.6F, 2.9F).build("slendrina");

        public SlendrinaEntity(EntityType<? extends EntityEnderman> p_i48562_1_, Level p_i48562_2_) {
            super(p_i48562_1_, p_i48562_2_);
        }

        @Override
        public void tick() {
            super.tick();
            // Custom teleportation behavior for Slendrina
        }
    }

    // Register the spawn eggs in the creative tabs
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class CreativeTabSetup {
        @SubscribeEvent
        public static void onCreativeTabRegister(CreativeModeTabsEvent.Register event) {
            event.registerCreativeModeTab(CreativeModeTabs.BUILDING_BLOCKS, builder -> builder.icon(() -> new ItemStack(GRANNY_SPAWN_EGG.get())));
        }
    }
}
