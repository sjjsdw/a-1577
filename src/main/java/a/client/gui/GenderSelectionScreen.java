package a.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

public class GenderSelectionScreen extends Screen {

    public GenderSelectionScreen() {
        super(Component.literal("Character Gender & Body Selection"));
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Masculine / Male Option
        this.addRenderableWidget(Button.builder(Component.literal("Male"), button -> {
            selectGender("male");
        }).bounds(centerX - 110, centerY - 10, 100, 20).build());

        // Feminine / Female Option
        this.addRenderableWidget(Button.builder(Component.literal("Female"), button -> {
            selectGender("female");
        }).bounds(centerX + 10, centerY - 10, 100, 20).build());

        // Advanced Body Customization (Uncensored Morphs/Anatomy)
        this.addRenderableWidget(Button.builder(Component.literal("Modify Anatomy & Morphs"), button -> {
            openAnatomyCustomization();
        }).bounds(centerX - 85, centerY + 25, 170, 20).build());
    }

    private void selectGender(String gender) {
        // Networking logic to sync the selected gender/body type to the server
        // Example: a.network.NetworkHandler.CHANNEL.sendToServer(new GenderSyncPacket(gender));
        
        // After selection, close the screen or proceed to next customization step
        this.minecraft.setScreen(null);
    }

    private void openAnatomyCustomization() {
        // Logic to open a more detailed screen for nsfw/uncensored body sliders, 
        // such as chest size, hip width, or other physical attributes provided by the mod.
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        
        // Title and Subtitle
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, this.height / 2 - 60, 0xFFFFFF);
        guiGraphics.drawCenteredString(this.font, "Select your character's biological base and physical features.", this.width / 2, this.height / 2 - 40, 0xBBBBBB);
        
        // Rendering logic for buttons and decorations
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics) {
        // Standard dark background
        super.renderBackground(guiGraphics);
        
        // If specific NSFW background art is desired, it would be rendered here:
        // RenderSystem.setShaderTexture(0, GENDER_BG_TEXTURE);
        // guiGraphics.blit(GENDER_BG_TEXTURE, ...);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        // In most gender-selection mods, we prevent closing until a choice is made
        return false;
    }
}