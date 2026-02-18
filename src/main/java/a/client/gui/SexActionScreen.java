package a.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SexActionScreen extends Screen {

    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 20;
    private static final int SPACING = 4;

    public SexActionScreen() {
        super(Component.literal("Sex Action Menu"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addRenderableWidget(Button.builder(Component.literal("Missionary"), button -> {
            this.sendAction("missionary");
        }).bounds(centerX - BUTTON_WIDTH / 2, centerY - 50, BUTTON_WIDTH, BUTTON_HEIGHT).build());

        this.addRenderableWidget(Button.builder(Component.literal("Doggy Style"), button -> {
            this.sendAction("doggy");
        }).bounds(centerX - BUTTON_WIDTH / 2, centerY - 50 + (BUTTON_HEIGHT + SPACING), BUTTON_WIDTH, BUTTON_HEIGHT).build());

        this.addRenderableWidget(Button.builder(Component.literal("Cowgirl"), button -> {
            this.sendAction("cowgirl");
        }).bounds(centerX - BUTTON_WIDTH / 2, centerY - 50 + (BUTTON_HEIGHT + SPACING) * 2, BUTTON_WIDTH, BUTTON_HEIGHT).build());

        this.addRenderableWidget(Button.builder(Component.literal("Blowjob"), button -> {
            this.sendAction("blowjob");
        }).bounds(centerX - BUTTON_WIDTH / 2, centerY - 50 + (BUTTON_HEIGHT + SPACING) * 3, BUTTON_WIDTH, BUTTON_HEIGHT).build());

        this.addRenderableWidget(Button.builder(Component.literal("Cunnilingus"), button -> {
            this.sendAction("cunnilingus");
        }).bounds(centerX - BUTTON_WIDTH / 2, centerY - 50 + (BUTTON_HEIGHT + SPACING) * 4, BUTTON_WIDTH, BUTTON_HEIGHT).build());

        this.addRenderableWidget(Button.builder(Component.literal("Stop Action"), button -> {
            this.sendAction("stop");
            this.onClose();
        }).bounds(centerX - BUTTON_WIDTH / 2, centerY + 60, BUTTON_WIDTH, BUTTON_HEIGHT).build());
    }

    private void sendAction(String actionType) {
        // This is where you would call your networking handler to send a packet to the server
        // Example: a.network.NetworkHandler.INSTANCE.sendToServer(new ActionPacket(actionType));
        if (this.minecraft != null && this.minecraft.player != null) {
            this.minecraft.player.displayClientMessage(Component.literal("Executing action: " + actionType), true);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics) {
        super.renderBackground(guiGraphics);
        // Optional: Custom background overlay
        guiGraphics.fillGradient(0, 0, this.width, this.height, 0x88000000, 0xAA000000);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}