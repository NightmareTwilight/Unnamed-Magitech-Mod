package crimson_twilight.unnamed_magitech_mod.api.capability.player_ki;

public class PlayerKi implements IPlayerKi
{
    int amount = 0;
    int max = 0;
    int gen = 10;
    boolean hasVeins;

    @Override
    public int getKiAmount() {
        return this.amount;
    }

    @Override
    public int getMaxKiAmount() {
        return this.max;
    }

    @Override
    public int getKiGenAmount() { return this.gen; }

    @Override
    public boolean hasVeins() { return hasVeins; }

    @Override
    public void setKiAmount(int amount) { this.amount = Math.min(amount, this.max); }

    @Override
    public void setKiGenAmount(int amount) {
        this.gen = amount;
    }

    @Override
    public void setMaxKiAmount(int amount) {
        this.max = amount;
    }

    @Override
    public void setHasVeins(boolean hasVeins) { this.hasVeins = hasVeins; }

    @Override
    public void addKiAmount(int amount) {
        setKiAmount(this.amount + amount);
    }

    @Override
    public void addMaxKiAmount(int amount) {
        setMaxKiAmount(this.max + amount);
    }

    @Override
    public void addKiGenAmount(int amount) {
        setKiGenAmount(this.gen + amount);
    }
}
