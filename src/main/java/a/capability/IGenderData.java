package a.capability;

import net.minecraft.nbt.CompoundTag;

public interface IGenderData {
    String getGender();
    void setGender(String gender);
    void copyFrom(IGenderData source);
    void saveNBTData(CompoundTag nbt);
    void loadNBTData(CompoundTag nbt);
}