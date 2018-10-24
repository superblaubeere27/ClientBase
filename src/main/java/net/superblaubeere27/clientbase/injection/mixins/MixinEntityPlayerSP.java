package net.superblaubeere27.clientbase.injection.mixins;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.types.EventType;
import net.minecraft.client.entity.EntityPlayerSP;
import net.superblaubeere27.clientbase.events.MotionUpdateEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP extends MixinEntity {
    private double cachedX;
    private double cachedY;
    private double cachedZ;

    private float cachedRotationPitch;
    private float cachedRotationYaw;

    @Inject(method = "onUpdateWalkingPlayer", at = @At("HEAD"))
    private void onUpdateWalkingPlayerPre(CallbackInfo ci) {
        cachedX = posX;
        cachedY = posY;
        cachedZ = posZ;

        cachedRotationYaw = rotationYaw;
        cachedRotationPitch = rotationPitch;

        MotionUpdateEvent event = new MotionUpdateEvent(EventType.PRE, posX, posY, posZ, rotationYaw, rotationPitch, onGround);

        EventManager.call(event);

        posX = event.getX();
        posY = event.getY();
        posZ = event.getZ();

        rotationYaw = event.getYaw();
        rotationPitch = event.getPitch();
    }

    @Inject(method = "onUpdateWalkingPlayer", at = @At("RETURN"))
    private void onUpdateWalkingPlayerPost(CallbackInfo ci) {
        posX = cachedX;
        posY = cachedY;
        posZ = cachedZ;

        rotationYaw = cachedRotationYaw;
        rotationPitch = cachedRotationPitch;

        EventManager.call(new MotionUpdateEvent(EventType.POST, posX, posY, posZ, rotationYaw, rotationPitch, onGround));
    }

}
