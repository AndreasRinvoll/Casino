package terningspill.casino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CasinoController {

    @Autowired
    private CasinoRepository rep;

    @GetMapping("/rullTerning")
    public int rullTerning(){
        return rep.rullTerning();
    }

    @PostMapping("/lagreResultat")
    public void lagreResultat(Spiller spiller){
        rep.lagreResultat(spiller);
    }

    @GetMapping("/hentHighscore")
    public List<Spiller> hentHighscore(){
        return rep.hentHighscore();
    }

    @PostMapping("/slettHighscore")
    public void slettHighscore(){
        rep.slettHighscore();
    }

    @GetMapping("/hentLandOgBy")
    public List<LandOgBy> hentLandOgBy(){
        return rep.hentLandOgBy();
    }

}
