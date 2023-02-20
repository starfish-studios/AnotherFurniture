package com.starfish_studios.another_furniture.integration.forge;

import com.simibubi.create.content.contraptions.components.actors.SeatBlock;
import com.simibubi.create.content.contraptions.components.actors.SeatInteractionBehaviour;
import com.simibubi.create.content.contraptions.components.structureMovement.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.components.structureMovement.Contraption;
import com.simibubi.create.content.contraptions.components.structureMovement.MovingInteractionBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class AFSeatInteractionBehaviour extends MovingInteractionBehaviour {

    @Override
    public boolean handlePlayerInteraction(Player player, InteractionHand activeHand, BlockPos localPos, AbstractContraptionEntity contraptionEntity) {
        return false;
    }

    @Override
    public void handleEntityCollision(Entity entity, BlockPos localPos, AbstractContraptionEntity contraptionEntity) {
        Contraption contraption = contraptionEntity.getContraption();
        int index = contraption.getSeats().indexOf(localPos);
        System.out.println(index);
        if (index == -1)
            return;
        if (entity instanceof Player)
            return;
        contraptionEntity.addSittingPassenger(entity, index);
    }

}