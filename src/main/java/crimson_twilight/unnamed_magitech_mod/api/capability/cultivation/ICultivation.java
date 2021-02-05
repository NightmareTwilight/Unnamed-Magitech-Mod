package crimson_twilight.unnamed_magitech_mod.api.capability.cultivation;

public interface ICultivation
{
    int getCultivationBase();
    int getKiToCB();
    int getCBGain();
    int getAscensionLevel();
    int getCultivationRank();
    int getFoundationAmount();
    int getCoreAmount();
    int getSoulAmount();
    long getStartTime();
    boolean isCultivating();


    void setCultivationBase(int amount);
    void setKiToCB(int amount);
    void setCBGain(int amount);
    void setAscensionLevel(int amount);
    void setCultivationRank(int amount);
    void setFoundationAmount(int amount);
    void setCoreAmount(int amount);
    void setSoulAmount(int amount);
    void setStartTime(long amount);
    void setIsCultivating(boolean state);

    void addCultivationBase(int amount);
    void addKiToCB(int amount);
    void addCBGain(int amount);
    void addAscensionLevel(int amount);
    void addCultivationRank(int amount);
    void addFoundationAmount(int amount);
    void addCoreAmount(int amount);
    void addSoulAmount(int amount);
}
