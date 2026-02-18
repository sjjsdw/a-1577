package a.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class GenderCapability {
    private String gender = "neutral";
    private float breastSize = 0.0f;
    private float penisSize = 0.0f;
    private boolean hasVagina = false;
    private float arousal = 0.0f;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getBreastSize() {
        return breastSize;
    }

    public void setBreastSize(float breastSize) {
        this.breastSize = breastSize;
    }

    public float getPenisSize() {
        return penisSize;
    }

    public void setPenisSize(float penisSize) {
        this.penisSize = penisSize;
    }

    public boolean hasVagina() {
        return hasVagina;
    }

    public void setHasVagina(boolean hasVagina) {
        this.hasVagina = hasVagina;
    }

    public float getArousal() {
        return arousal;
    }

    public void setArousal(float arousal) {
        this.arousal = Math.max(0.0f, Math.min(100.0f, arousal));
    }

    public void copyFrom(GenderCapability source) {
        this.gender = source.gender;
        this.breastSize = source.breastSize;
        this.penisSize = source.penisSize;
        this.hasVagina = source.hasVagina;
        this.arousal = source.arousal;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putString("gender", gender);
        nbt.putFloat("breastSize", breastSize);
        nbt.putFloat("penisSize", penisSize);
        nbt.putBoolean("hasVagina", hasVagina);
        nbt.putFloat("arousal", arousal);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.gender = nbt.getString("gender");
        this.breastSize = nbt.getFloat("breastSize");
        this.penisSize = nbt.getFloat("penisSize");
        this.hasVagina = nbt.getBoolean("hasVagina");
        this.arousal = nbt.getFloat("arousal");
    }
}