package crimson_twilight.unnamed_magitech_mod.api.capability.player_ki;

public interface IPlayerKi
{
    int getKiAmount();
    int getMaxKiAmount();
    int getKiGenAmount();
    boolean hasVeins();
    int getCorruption();

    void setKiAmount(int amount);
    void setMaxKiAmount(int amount);
    void setKiGenAmount(int amount);
    void setHasVeins(boolean hasVeins);
    void setCorruption(int amount);

    void addKiAmount(int amount);
    void addMaxKiAmount(int amount);
    void addKiGenAmount(int amount);
    void addCorruption(int amount);
}
