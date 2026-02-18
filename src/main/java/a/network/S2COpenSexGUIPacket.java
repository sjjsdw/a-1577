package a.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import a.client.SexGUI;
import java.util.function.Supplier;

public class S2COpenSexGUIPacket {
    public S2COpenSexGUIPacket() {
    }

    public static void encode(S2COpenSexGUIPacket msg, FriendlyByteBuf buf) {
    }

    public static S2COpenSexGUIPacket decode(FriendlyByteBuf buf) {
        return new S2COpenSexGUIPacket();
    }

    public static void handle(S2COpenSexGUIPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (Minecraft.getInstance().level != null) {
                Minecraft.getInstance().setScreen(new SexGUI());
            }
        });
        ctx.get().setPacketHandled(true);
    }
}