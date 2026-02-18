package a.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class C2SRequestSexPacket {
    private final int targetId;

    public C2SRequestSexPacket(int targetId) {
        this.targetId = targetId;
    }

    public C2SRequestSexPacket(FriendlyByteBuf buffer) {
        this.targetId = buffer.readInt();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.targetId);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer sender = context.getSender();
            if (sender == null) return;

            Entity target = sender.level().getEntity(this.targetId);
            if (target != null && sender.distanceToSqr(target) < 25.0D) {
                // Logic for initiating NSFW interaction sequence
                // This typically triggers animations, sound effects, or UI for both parties
                System.out.println("Processing sex interaction request from " + sender.getName().getString() + " to entity ID " + targetId);
                
                // Example: Triggering a custom capability or state machine
                // target.getCapability(SexCapability.INSTANCE).ifPresent(cap -> cap.initiateSex(sender));
            }
        });
        context.setPacketHandled(true);
    }
}
