$(document).ready(function () {
    $('form').submit(function (event) {
        Login(event);
    })
});

function Login(event) {
    event.preventDefault();
    var email = document.getElementsByName('email')[0].value;
    var senha = document.getElementsByName('senha')[0].value;
    var formData = {
        email: email,
        senha: senha
    };
    if (email == null || email == '' || (email.length == 0)) {
        $("#emailError").show();
        $('#emailError').delay(5000).fadeOut('slow');
        return;
    }
    if (senha == null || senha == '' || (senha.length == 0)) {
        $("#senhaError").show();
        $('#senhaError').delay(5000).fadeOut('slow');
        return;
    }
    $.ajax({
        url: 'http://34.95.17.159:8085/api/users/login',
        data: formData,
        success: function (data) {
            if (data == "MailError") {
                $('#sameemailError').fadeIn('slow');
                $('#sameemailError').delay(5000).fadeOut('slow');
            } else if (data == "error") {
                $('#error').fadeIn('slow');
                $('#error').delay(5000).fadeOut('slow');
            } else if (data == "invalidpassword") {
                $('#invalidpassword').fadeIn('slow');
                $('#invalidpassword').delay(5000).fadeOut('slow');
            } else {
                $('#success').fadeIn('slow');
                $('#success').delay(5000).fadeOut('slow');
                writeCookie("caravan.login.userid", data, 4);
                console.log("id = " + data);
                setTimeout(function () {
                    window.location.href = "./painel.html";
                }, 3000);
            }
            //$('#msg').html(data).fadeIn('slow');
            //$('#msg').delay(5000).fadeOut('slow');
        },
        type: "POST"
    })
};

$(document).ready(function () {
    $('form').submit(function (event) {
        Cadastro(event);
    })
});

function Cadastro(event) {
    event.preventDefault();
    var nome = document.getElementsByName('nome')[0].value;
    var email = document.getElementsByName('email')[0].value;
    var senha = document.getElementsByName('senha')[0].value;
    var repitasenha = document.getElementsByName('repitasenha')[0].value;
    var formData = {
        nome: nome,
        email: email,
        senha: senha
    };
    if (senha != repitasenha) {
        $("#repitasenhaError").show();
        $('#repitasenhaError').delay(5000).fadeOut('slow');
        return;
    }
    if (nome == null || nome == '' || (nome.length == 0)) {
        $("#nomeError").show();
        $('#nomeError').delay(5000).fadeOut('slow');
        return;
    }
    if (email == null || email == '' || (email.length == 0)) {
        $("#emailError").show();
        $('#emailError').delay(5000).fadeOut('slow');
        return;
    }

    $.ajax({
        url: 'http://34.95.17.159:8085/api/users/register',
        data: formData,
        success: function (data) {
            if (data == "MailError") {
                $('#sameemailError').fadeIn('slow');
                $('#sameemailError').delay(5000).fadeOut('slow');
            } else if (data == "error") {
                $('#error').fadeIn('slow');
                $('#error').delay(5000).fadeOut('slow');
            } else if (data == "success") {
                $('#success').fadeIn('slow');
                $('#success').delay(5000).fadeOut('slow');
                setTimeout(function () {
                    window.location.href = "./index.html";
                }, 3000);
            }
        },
        type: "POST"
    });
};