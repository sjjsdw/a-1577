package a.capability;

public interface IPregnancyData {
    boolean isPregnant();
    void setPregnant(boolean isPregnant);

    int getPregnancyProgress();
    void setPregnancyProgress(int progress);

    int getMaxPregnancyTime();
    void setMaxPregnancyTime(int maxTime);

    String getFatherName();
    void setFatherName(String fatherName);

    void copyFrom(IPregnancyData source);
}