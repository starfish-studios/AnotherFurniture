package com.crispytwig.another_furniture;

import com.crispytwig.another_furniture.block.ChairBlock;
import com.crispytwig.another_furniture.block.SeatBlock;
import com.crispytwig.another_furniture.block.ShelfBlock;
import com.crispytwig.another_furniture.block.StoolBlock;
import com.crispytwig.another_furniture.entity.SeatEntity;
import com.crispytwig.another_furniture.init.ModEntity;
import com.crispytwig.another_furniture.render.SeatRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

@Mod("another_furniture")
@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
public class AnotherFurnitureMod
{
    public static final String MOD_ID = "another_furniture";

    public AnotherFurnitureMod()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ModEntity.REGISTER.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Block> OAK_CHAIR = registerBlock("oak_chair",
            () -> new ChairBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> OAK_SHELF = registerBlock("oak_shelf",
            () -> new ShelfBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WHITE_STOOL = registerBlock("white_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ORANGE_STOOL = registerBlock("orange_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MAGENTA_STOOL = registerBlock("magenta_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LIGHT_BLUE_STOOL = registerBlock("light_blue_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> YELLOW_STOOL = registerBlock("yellow_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LIME_STOOL = registerBlock("lime_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PINK_STOOL = registerBlock("pink_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GRAY_STOOL = registerBlock("gray_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LIGHT_GRAY_STOOL = registerBlock("light_gray_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CYAN_STOOL = registerBlock("cyan_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PURPLE_STOOL = registerBlock("purple_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BLUE_STOOL = registerBlock("blue_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BROWN_STOOL = registerBlock("brown_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GREEN_STOOL = registerBlock("green_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> RED_STOOL = registerBlock("red_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BLACK_STOOL = registerBlock("black_stool",
            () -> new StoolBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

    @SubscribeEvent
    public static void onFMLCLientSetup(FMLClientSetupEvent event)
    {
        EntityRenderers.register(ModEntity.SEAT.get(), SeatRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(OAK_CHAIR.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getPlayer();
        if(player.isPassenger())
            return;

        Level world = event.getWorld();
        BlockPos pos = event.getPos();

        Vec3 vec = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        double maxDist = 3;
        if((vec.x - player.getX()) * (vec.x - player.getX()) + (vec.y - player.getY()) * (vec.y - player.getY()) + (vec.z - player.getZ()) * (vec.z - player.getZ()) > maxDist * maxDist)
            return;

        BlockState state = world.getBlockState(pos);

        ItemStack stack1 = player.getMainHandItem();
        ItemStack stack2 = player.getOffhandItem();
        if(!stack1.isEmpty() || !stack2.isEmpty())
            return;

        if(state.getBlock() instanceof SeatBlock seatblock) {
            List<SeatEntity> seats = world.getEntitiesOfClass(SeatEntity.class, new AABB(pos, pos.offset(1, 1, 1)));
            if(seats.isEmpty()) {
                SeatEntity seat = new SeatEntity(world, pos, seatblock.seatHeight());
                world.addFreshEntity(seat);
                event.getPlayer().startRiding(seat);
            }
        }
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(TAB)));
        return registryObject;
    }

    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(OAK_CHAIR.get().asItem());
        }
    };
}
