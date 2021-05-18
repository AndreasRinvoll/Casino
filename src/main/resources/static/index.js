$(function () {
    hentHighscore();
    hentLandOgBy();

    //Hvis du ikke har onclick, men vil bruke id:
    /*$("#demo").click(function (){
        test();
    });*/

})

/*function test(){

}*/

let teller = 0;

//let brukernavnOK = false;
//let telefonnrOK = false;

function rullTerning() {
    $.get("/rullTerning", function (resultat) {
        if (resultat !== -1) {
            if (resultat > 1) {
                teller++;
                $("#resultat").html("Du fikk : " + resultat);
                $("#terningKast").val(teller);
            } else {
                $("#resultat").html("Du fikk : " + resultat + ". Spillet er over!");
                $('#rullTerning').attr('disabled', 'disabled');
                $('#sendResultat').removeAttr('disabled');
            }
        }

    })
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}

function validerBrukernavn() {
    const brukernavn = $("#brukernavn").val();
    const regexp = /^[a-zA-ZæøåÆØÅ\- .]{3,8}$/;
    const ok = regexp.test(brukernavn);

    if (!ok) {
        $("#feilBrukernavn").html("Brukernavnet må være 3-8 tegn langt, og kan ikke inneholde tall");
        //brukernavnOK = false;
        return false;
    } else {
        $("#feilBrukernavn").html("");
        //brukernavnOK = true;
        return true;
    }
}

function validerTelefonnr() {
    const telefonnr = $("#telefonnr").val();
    const test = /^[0-9]{8}$/;
    const ok = test.test(telefonnr);

    if (!ok) {
        $("#feilTelefonnr").html("Nummeret kan bare inneholde tall og må være 8 langt");
        //telefonnrOK = false;
        return false;

    } else {
        $("#feilTelefonnr").html("");
        //telefonnrOK = true;
        return true;
    }

}

function validerBrukernavnOgTelefonnummer() {

    if (validerBrukernavn() && validerTelefonnr()) {
        sendResultat()
    }
}


function sendResultat() {
    //Lager et JS objekt for spiller
    const test = $("#brukernavn");

    const brukernavn = $("#brukernavn");
    const telefonnr = $("#telefonnr");
    const land = $("#land");
    const by = $("#by");
    const terningKast = $("#terningKast")

    const spiller = {
        brukernavn: brukernavn.val(),
        telefonnr: telefonnr.val(),
        land: land.val(),
        by: by.val(),
        terningKast: terningKast.val()
    };

    $.post("/lagreResultat", spiller, function () {
        $("#resultat").html("Resultatet er sendt!")
        hentHighscore();
    })
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });

    brukernavn.val("");
    telefonnr.val("");
    land.val("");
    by.val("");
    terningKast.val(0);

    $('#rullTerning').removeAttr('disabled');
    $('#sendResultat').attr('disabled', 'disabled');
    teller = 0;

    //let brukernavnOK = false;
    //let telefonnrOK = false;
}

function hentHighscore() {
    $.get("/hentHighscore", function (highscore) {
        let ut = "<table class='table table-striped table-hover'>" +
            "<tr>" +
            "<th>Brukernavn</th>" +
            "<th>Telefonnr</th>" +
            "<th>Land</th>" +
            "<th>By</th>" +
            "<th>Terningkast</th>" +
            "</tr>";
        for (const score of highscore) {
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
    })
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}

function slettHighscore() {
    $.post("/slettHighscore", function () {
        hentHighscore();
        $("#resultat").html("Highscore er slettet!")
    })
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}

function hentLandOgBy() {
    $.get("/hentLandOgBy", function (landOgBy) {
        $("#land").append($("<option></option>").val(""));

        let forrigeLand = "";
        for (const land of landOgBy) {
            let nyttLand = land.land;

            if (nyttLand !== forrigeLand) {
                $("#land").append($("<option>" + nyttLand + "</option>").val(nyttLand));
                forrigeLand = nyttLand;
            }
        }
    })
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}

function hentBy(land) {
    $("#by").html("");

    $.get("/hentLandOgBy", function (landOgBy) {

        for (const by of landOgBy) {

            if (by.land === land) {
                $("#by").append($("<option>" + by.by + "</option>").val(by.by));
            }
        }
    });
}