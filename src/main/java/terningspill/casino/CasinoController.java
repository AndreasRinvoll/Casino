package terningspill.casino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CasinoController {

    @Autowired
    private CasinoRepository rep;

    @GetMapping("/rullTerning")
    public int rullTerning(HttpServletResponse response) throws IOException{
        if(rep.rullTerning() == -1){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kan ikke rulle terning");
            return -1;
        }else {
            return rep.rullTerning();
        }

    }

    @PostMapping("/lagreResultat")
    public void lagreResultat(Spiller spiller, HttpServletResponse response) throws IOException {
        if(!rep.lagreResultat(spiller)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
    }

    @GetMapping("/hentHighscore")
    public List<Spiller> hentHighscore(HttpServletResponse response) throws IOException{

        if(rep.hentHighscore() == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
            return null;

        }else {
            return rep.hentHighscore();
        }

    }

    @PostMapping("/slettHighscore")
    public void slettHighscore(HttpServletResponse response) throws IOException{
        if(!rep.slettHighscore()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
    }

    @GetMapping("/hentLandOgBy")
    public List<LandOgBy> hentLandOgBy(HttpServletResponse response) throws IOException{
        if(rep.hentLandOgBy() == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
            return null;
        } else {
            return rep.hentLandOgBy();
        }

    }

}
