function login() {
    const login = {
        brukernavn: $("#brukernavn").val(),
        passord: $("#passord").val()
    };

    $.post("/login", login, function (ok){
        if(ok){
            alert("Du er logget inn!")
            window.location.href = "/index.html";
        }else {
            $("#feilLogin").html("Feil brukernavn eller passord");
        }
    })
        .fail(function (jqXHR){
            const json = $.parseJSON(jqXHR.responseText);
            $("#feilLogin").html(json.message);
        });
}