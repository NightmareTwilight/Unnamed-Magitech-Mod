package crimson_twilight.unnamed_magitech_mod.api.capability.cultivation;

public class Cultivation implements ICultivation
{
    int cb = 0;
    int ratio = 100;
    int gain = 1;
    int lvl = 0;
    int rank = 0;
    int foundation = 0;
    int core = 0;
    int soul = 0;
    long time;

    @Override
    public int getCultivationBase() {
        return cb;
    }

    @Override
    public int getKiToCB() {
        return ratio;
    }

    @Override
    public int getCBGain() {
        return gain;
    }

    @Override
    public int getAscensionLevel() {
        return lvl;
    }

    @Override
    public int getCultivationRank() {
        return rank;
    }

    @Override
    public int getFoundationAmount() {
        return foundation;
    }

    @Override
    public int getCoreAmount() {
        return core;
    }

    @Override
    public int getSoulAmount() {
        return soul;
    }

    @Override
    public long getStartTime() {
        return time;
    }

    @Override
    public void setCultivationBase(int amount) {
        this.cb = Math.max(0, amount);
    }

    @Override
    public void setKiToCB(int amount) {
        this.ratio = Math.max(1, amount);
    }

    @Override
    public void setCBGain(int amount) {
        this.gain = Math.max(1, amount);
    }

    @Override
    public void setAscensionLevel(int amount) {
        this.lvl = Math.max(0, amount);
    }

    @Override
    public void setCultivationRank(int amount) {
        this.rank = Math.max(0, amount);
        if(this.rank > 10) {
            this.rank = 1;
            this.addAscensionLevel(1);
        }
    }

    @Override
    public void setFoundationAmount(int amount) {
        this.foundation = Math.max(0, amount);
    }

    @Override
    public void setCoreAmount(int amount) {
        this.core = Math.max(0, amount);
    }

    @Override
    public void setSoulAmount(int amount) {
        this.soul = Math.max(0, amount);
    }

    @Override
    public void setStartTime(long time) {
        this.time = time;
    }



    @Override
    public void addCultivationBase(int amount) {
        this.cb = Math.max(0, this.cb + amount);
    }

    @Override
    public void addKiToCB(int amount) {
        this.ratio = Math.max(1, this.ratio + amount);
    }

    @Override
    public void addCBGain(int amount) {
        this.gain = Math.max(1, this.gain + amount);
    }

    @Override
    public void addAscensionLevel(int amount) {
        this.lvl = Math.max(0, this.lvl + amount);
    }

    @Override
    public void addCultivationRank(int amount) {
        setCultivationRank(Math.max(0, this.rank + amount));
    }

    @Override
    public void addFoundationAmount(int amount) {
        this.foundation = Math.max(0, this.foundation + amount);
    }

    @Override
    public void addCoreAmount(int amount) {
        this.core = Math.max(0, this.core + amount);
    }

    @Override
    public void addSoulAmount(int amount) {
        this.soul = Math.max(0, this.soul + amount);
    }
}
