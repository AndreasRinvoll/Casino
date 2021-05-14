package terningspill.casino;

public class Spiller {
    private String brukernavn;
    private String telefonnr;
    private String land;
    private String by;
    private int terningKast;

    public Spiller(String brukernavn, String telefonnr, String land, String by, int terningKast){
        this.brukernavn = brukernavn;
        this.telefonnr = telefonnr;
        this.land = land;
        this.by = by;
        this.terningKast = terningKast;
    }

    public Spiller(){}

    public String getBrukernavn(){
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn){
        this.brukernavn = brukernavn;
    }

    public String getTelefonnr(){
        return telefonnr;
    }

    public void setTelefonnr(String telefonnr){
        this.telefonnr = telefonnr;
    }

    public String getLand(){
        return land;
    }

    public void setLand(String land){
        this.land = land;
    }

    public String getBy(){
        return by;
    }

    public void setBy(String by){
        this.by = by;
    }

    public int getTerningKast(){
        return terningKast;
    }

    public void setTerningKast(int terningKast){
        this.terningKast = terningKast;
    }
}
