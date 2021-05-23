package terningspill.casino;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class CasinoController {

    @Autowired
    private CasinoRepository rep;

    @Autowired
    private HttpSession session;

    Logger logger = LoggerFactory.getLogger(CasinoController.class);

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
        if(validerSpiller(spiller)){
            if(!rep.lagreResultat(spiller)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
            }
        }else {
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Feil i validering av Spiller.");
        }
    }

    private boolean validerSpiller(Spiller spiller){
        String regexpBrukernavn = "[a-zA-ZæøåÆØÅ\\- .]{3,8}";
        String regexpTelefonnr = "[0-9]{8}";

        boolean brukernavnOK = spiller.getBrukernavn().matches(regexpBrukernavn);
        boolean telefonnrOK = spiller.getTelefonnr().matches(regexpTelefonnr);

        if(brukernavnOK && telefonnrOK){
            return true;
        } else {
            logger.error("Feil i validering av Spiller.");
            return false;
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
        if(session.getAttribute("Innlogget")!=null){
            if(!rep.slettHighscore()){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
            }
        }else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Du må logge inn for å slette");
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

    @PostMapping("/login")
    public boolean login(Login bruker){
         if(rep.login(bruker)){
             session.setAttribute("Innlogget", bruker);
             return true;
         }
         return false;
    }

    @GetMapping("/logut")
    public void logut(){
        session.removeAttribute("Innlogget");
    }

    @PostMapping("/nyBruker")
    public boolean nyBruker(Login nyBruker){
        return rep.nyBruker(nyBruker);
    }

}
