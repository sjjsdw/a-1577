package a.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PregnancyCapability {
    private boolean isPregnant = false;
    private int gestationProgress = 0;
    private int maxGestation = 24000; 
    private String fatherName = "";
    private String motherName = "";

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.isPregnant = pregnant;
    }

    public int getGestationProgress() {
        return gestationProgress;
    }

    public void setGestationProgress(int progress) {
        this.gestationProgress = progress;
    }

    public int getMaxGestation() {
        return maxGestation;
    }

    public void setMaxGestation(int max) {
        this.maxGestation = max;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String name) {
        this.fatherName = name;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String name) {
        this.motherName = name;
    }

    public void incrementProgress() {
        if (isPregnant) {
            this.gestationProgress++;
        }
    }

    public boolean isTerm() {
        return isPregnant && gestationProgress >= maxGestation;
    }

    public void reset() {
        this.isPregnant = false;
        this.gestationProgress = 0;
        this.fatherName = "";
    }

    public void copyFrom(PregnancyCapability source) {
        this.isPregnant = source.isPregnant;
        this.gestationProgress = source.gestationProgress;
        this.maxGestation = source.maxGestation;
        this.fatherName = source.fatherName;
        this.motherName = source.motherName;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("isPregnant", isPregnant);
        nbt.putInt("gestationProgress", gestationProgress);
        nbt.putInt("maxGestation", maxGestation);
        nbt.putString("fatherName", fatherName);
        nbt.putString("motherName", motherName);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.isPregnant = nbt.getBoolean("isPregnant");
        this.gestationProgress = nbt.getInt("gestationProgress");
        this.maxGestation = nbt.getInt("maxGestation");
        this.fatherName = nbt.getString("fatherName");
        this.motherName = nbt.getString("motherName");
    }
}