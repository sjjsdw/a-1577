package a.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class C2SSetGenderPacket {
    private final String gender;

    public C2SSetGenderPacket(String gender) {
        this.gender = gender;
    }

    public static void encode(C2SSetGenderPacket msg, FriendlyByteBuf buffer) {
        buffer.writeUtf(msg.gender);
    }

    public static C2SSetGenderPacket decode(FriendlyByteBuf buffer) {
        return new C2SSetGenderPacket(buffer.readUtf());
    }

    public static void handle(C2SSetGenderPacket msg, Supplier<NetworkEvent.Context> ctxGetter) {
        NetworkEvent.Context ctx = ctxGetter.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null) {
                // This is where you would handle the gender data, typically by saving it to a player capability or NBT.
                // For example:
                // player.getCapability(GenderProvider.GENDER_CAP).ifPresent(cap -> {
                //     cap.setGender(msg.gender);
                // });
            }
        });
        ctx.setPacketHandled(true);
    }

    public String getGender() {
        return gender;
    }
}