package terningspill.casino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CasinoRepository {

    @Autowired
    private JdbcTemplate db;

    public int rullTerning(){
        int rull =  (int)(Math.random() * 6 ) + 1;
        return rull;
    }

    public void lagreResultat(Spiller spiller){
        String sql = "INSERT INTO Spiller (brukernavn, telefonnr, land, by, terningkast) VALUES (?,?,?,?,?);";
        db.update(sql,
                spiller.getBrukernavn(),
                spiller.getTelefonnr(),
                spiller.getLand(),
                spiller.getBy(),
                spiller.getTerningKast());
    }

    public List<Spiller> hentHighscore(){
        String sql = "SELECT * FROM Spiller ORDER BY terningKast DESC;";
        List<Spiller> highscore = db.query(sql, new BeanPropertyRowMapper<>(Spiller.class));
        return highscore;
    }

    public void slettHighscore(){
        String sql = "DELETE FROM Spiller;";
        db.update(sql);
    }

    public List<LandOgBy> hentLandOgBy(){
        String sql = "SELECT * FROM LandOgBy ORDER BY land ASC;";
        List<LandOgBy> landOgBy = db.query(sql, new BeanPropertyRowMapper<>(LandOgBy.class));
        return landOgBy;
    }
}
