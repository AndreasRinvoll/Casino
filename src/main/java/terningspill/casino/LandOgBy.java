package terningspill.casino;

public class LandOgBy {
    private String land;
    private String by;

    public LandOgBy(String land, String by) {
        this.land = land;
        this.by = by;
    }

    public LandOgBy() {
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
