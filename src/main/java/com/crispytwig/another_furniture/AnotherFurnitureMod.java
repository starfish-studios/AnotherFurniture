package com.crispytwig.another_furniture;

import com.crispytwig.another_furniture.block.*;
import com.crispytwig.another_furniture.entity.SeatEntity;
import com.crispytwig.another_furniture.init.ModEntity;
import com.crispytwig.another_furniture.render.SeatRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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

import static com.crispytwig.another_furniture.init.ModBlocks.BLOCKS;
import static com.crispytwig.another_furniture.init.ModBlocks.OAK_CHAIR;
import static com.crispytwig.another_furniture.init.ModItems.ITEMS;

@Mod("another_furniture")
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
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

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getPlayer();
        if(player.isPassenger())
            return;

        Level world = event.getWorld();
        BlockPos pos = event.getPos();

        if(!world.getBlockState(pos.above()).getCollisionShape(world, pos).isEmpty())
            return;

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



    public static final CreativeModeTab TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(OAK_CHAIR.get().asItem());
        }
    };
}
