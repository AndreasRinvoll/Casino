$(function (){
    hentHighscore();
    hentLandOgBy();

})

let teller = 0;

function rullTerning(){
    $.get("/rullTerning", function (resultat){
        if(resultat > 1){
            teller++;
            $("#resultat").html("Du fikk : " + resultat);
            $("#terningKast").val(teller);
        } else {
            $("#resultat").html("Du fikk : " + resultat + ". Spillet er over!");
            $('#rullTerning').attr('disabled','disabled');
            $('#sendResultat').removeAttr('disabled');
        }
    });
}

function sendResultat(){
    //Lager et JS objekt for spiller
    const brukernavn = $("#brukernavn");
    const telefonnr = $("#telefonnr");
    const land = $("#land");
    const by = $("#by");
    const terningKast = $("#terningKast")

    const spiller = {
        brukernavn : brukernavn.val(),
        telefonnr : telefonnr.val(),
        land : land.val(),
        by : by.val(),
        terningKast : terningKast.val()
    };

    $.post("/lagreResultat", spiller, function (){
        $("#resultat").html("Resultatet er sendt!")
        hentHighscore();
    })

    brukernavn.val("");
    telefonnr.val("");
    land.val("");
    by.val("");
    terningKast.val(0);

    $('#rullTerning').removeAttr('disabled');
    $('#sendResultat').attr('disabled', 'disabled');
    teller = 0;
}

function hentHighscore(){
    $.get("/hentHighscore", function (highscore){
        let ut = "<table class='table table-striped table-hover'>" +
            "<tr>" +
                "<th>Brukernavn</th>" +
                "<th>Telefonnr</th>" +
                "<th>Land</th>" +
                "<th>By</th>" +
                "<th>Terningkast</th>" +
            "</tr>";
        for(const score of highscore){
            ut += "<tr>" +
                    "<td>" + score.brukernavn + "</td>" +
                    "<td>" + score.telefonnr + "</td>" +
                    "<td>" + score.land + "</td>" +
                    "<td>" + score.by + "</td>" +
                    "<td>" + score.terningKast + "</td>" +
                "</tr>"
        }
        ut += "</table>";

        $("#highscore").html(ut);
    });
}

function slettHighscore(){
    $.post("/slettHighscore", function (){
        hentHighscore();
        $("#resultat").html("Highscore er slettet!")
    })
}

function hentLandOgBy(){
    $.get("/hentLandOgBy", function (landOgBy){
        $("#land").append($("<option></option>").val(""));

        let forrigeLand = "";
        for (const land of landOgBy){
            let nyttLand = land.land;

            if(nyttLand !== forrigeLand){
                $("#land").append($("<option>" + nyttLand + "</option>").val(nyttLand));
                forrigeLand = nyttLand;
            }
        }
    })
}

function hentBy(land) {
    $("#by").html("");

    $.get("/hentLandOgBy", function (landOgBy){

        for(const by of landOgBy){

            if(by.land === land){
                $("#by").append($("<option>" + by.by + "</option>").val(by.by));
            }
        }
    });
}