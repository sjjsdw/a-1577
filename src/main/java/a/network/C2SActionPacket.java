package a.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraft.server.level.ServerPlayer;

public class C2SActionPacket {
    private final int actionId;

    public C2SActionPacket(int actionId) {
        this.actionId = actionId;
    }

    public static void encode(C2SActionPacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.actionId);
    }

    public static C2SActionPacket decode(FriendlyByteBuf buffer) {
        return new C2SActionPacket(buffer.readInt());
    }

    public static void handle(C2SActionPacket msg, Supplier<NetworkEvent.Context> ctxGetter) {
        NetworkEvent.Context ctx = ctxGetter.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;

            // Handle custom server-side actions here based on actionId
            switch (msg.actionId) {
                case 0:
                    // Action 0
                    break;
                case 1:
                    // Action 1
                    break;
                default:
                    break;
            }
        });
        ctx.setPacketHandled(true);
    }
}