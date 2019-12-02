$(document).ready(function () {
    $('form').submit(function (event) {
        var path = window.location.pathname;
        var page = path.split("/").pop();
        if (page == "cadastro.html"){
            Cadastro(event);
        }else if (page == "login.html"){
            Login(event);
        }
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
        url: 'http://'+host_ip+':8085/api/users/login',
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

    var telefone = document.getElementsByName('telefone')[0].value;
    var endereco = document.getElementsByName('endereco')[0].value;
    var cidade = document.getElementsByName('cidade')[0].value;
    var pais = document.getElementsByName('pais')[0].value;
    var cep = document.getElementsByName('cep')[0].value;
    var estado = document.getElementsByName('estado')[0].value;

    if (telefone == null || telefone == '' || (telefone.length < 9)) {
        $("#telefoneError").show();
        $('#telefoneError').delay(5000).fadeOut('slow');
        return;
    }
    if (endereco == null || endereco == '' || (endereco.length == 0)) {
        $("#enderecoError").show();
        $('#enderecoError').delay(5000).fadeOut('slow');
        return;
    }
    if (cidade == null || cidade == '' || (telefone.length == 0)) {
        $("#cidadeError").show();
        $('#cidadeError').delay(5000).fadeOut('slow');
        return;
    }
    if (pais == null || pais == '' || (pais.length == 0)) {
        $("#paisError").show();
        $('#paisError').delay(5000).fadeOut('slow');
        return;
    }
    if (estado == null || estado == '' || (estado.length == 0)) {
        $("#estadoError").show();
        $('#estadoError').delay(5000).fadeOut('slow');
        return;
    }
    if (cep == null || cep == '' || (cep.length < 8)) {
        $("#cepError").show();
        $('#cepError').delay(5000).fadeOut('slow');
        return;
    }

    $.ajax({
        url: 'http://'+host_ip+':8085/api/users/register',
        data: formData,
        success: function (data) {
            if (data == "MailError") {
                $('#sameemailError').fadeIn('slow');
                $('#sameemailError').delay(5000).fadeOut('slow');
            } else if (data == "error") {
                console.log(data);
                $('#error').fadeIn('slow');
                $('#error').delay(5000).fadeOut('slow');
            } else if (data == "success") {
                $('#success').fadeIn('slow');
                $('#success').delay(5000).fadeOut('slow');
                $.ajax({
                    url: 'http://'+host_ip+':8085/api/users/login',
                    data: {
                        email: email,
                        senha: senha
                    },
                    success: function (data) {
                        if (parseInt(data) == null){
                            console.log(data);
                            $('#error').fadeIn('slow');
                            $('#error').delay(5000).fadeOut('slow');
                            return;
                        }
                        $.ajax({
                            url: 'http://'+host_ip+':8085/api/endereco/register/'+data,
                            data: {
                                endereco: endereco,
                                telefone: telefone,
                                CEP: cep,
                                cidade: cidade,
                                pais: pais,
                                estado: estado
                            },
                            success: function (data) {
                                console.log(JSON.stringify(
                                    {
                                        endereco: endereco,
                                        telefone: telefone,
                                        CEP: cep,
                                        cidade: cidade,
                                        pais: pais,
                                        estado: estado
                                    }
                                ))
                                console.log(data);
                                if (data=="success"){
                                    setTimeout(function () {
                                        window.location.href = "./login.html";
                                    }, 3000);
                                }else{
                                    console.log(data);
                                    $('#error').fadeIn('slow');
                                    $('#error').delay(5000).fadeOut('slow');
                                }
                            },
                            type: "POST"
                        })
                    },
                    type: "POST"
                })
            }
        },
        type: "POST"
    });
};