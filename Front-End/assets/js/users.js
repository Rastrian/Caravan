var request = new XMLHttpRequest();
request.open("GET", "http://"+host_ip+":8085/api/users/", false);
request.send(null);
var usersdb = JSON.parse(request.responseText);

var request = new XMLHttpRequest();
request.open("GET", "http://"+host_ip+":8085/api/users/" + user_id, false);
request.send(null);
var myuser = JSON.parse(request.responseText);

function transformarAmigoNativo() {
    var confirmar = confirm("Você tem certeza que quer se tornar um amigo nativo?");
    if (confirmar) {
        $.ajax({
            url: 'http://'+host_ip+':8085/api/users/' + user_id + '/turista',
            type: "POST"
        });
        location.reload();
    }
}

function transformarAdmin() {
    var confirmar = confirm("Você tem certeza que quer se tornar um administrador?");
    if (confirmar) {
        $.ajax({
            url: 'http://'+host_ip+':8085/api/users/' + user_id + '/admin',
            type: "POST"
        });
        location.reload();
    }
}

function loadProfilePage() {
    if (myuser != null || myuser != '') {
        db = myuser;
        document.getElementById('user').innerHTML =
            `${db.nome}`;
        let cargo = '';
        if (db.admin == true) {
            cargo = 'Administrador'
        } else {
            if (db.turista == true) {
                cargo = 'Turista';
            } else {
                cargo = 'Amigo Nativo';
            }
        }
        if (cargo != '') {
            document.getElementById('cargo').innerHTML = cargo;
        }

        var bHtml = '';
        bHtml += `
            <div class="update ml-auto mr-auto">
            <center>`;
        if (db.turista == true) {
            bHtml += `<button type="submit" class="btn btn-primary btn-round" onclick="transformarAmigoNativo()">Torne-se um Amigo Nativo</button>`;
        }
        if (test_mode == true && db.admin == false) {
            bHtml += `<button type="submit" class="btn btn-danger btn-round" onclick="transformarAdmin()">Administrador</br>(In Test Mode Only)</button>`;
        } else if (db.admin == true) {
            bHtml += `<button type="submit" class="btn btn-success btn-round" onclick="menuLocalTuristico()">Criar Novo</br>Local Turistico</button>`;
        }
        bHtml += `</center>
            </div>
            `;
        document.getElementById('botaoAmigoNativo').innerHTML = bHtml;

        var estados = [{
            "label": "Acre",
            "value": "AC"
        }, {
            "label": "Alagoas",
            "value": "AL"
        }, {
            "label": "Amap\u00e1",
            "value": "AP"
        }, {
            "label": "Amazonas",
            "value": "AM"
        }, {
            "label": "Bahia",
            "value": "BA"
        }, {
            "label": "Cear\u00e1",
            "value": "CE"
        }, {
            "label": "Distrito Federal",
            "value": "DF"
        }, {
            "label": "Esp\u00edrito Santo",
            "value": "ES"
        }, {
            "label": "Goi\u00e1s",
            "value": "GO"
        }, {
            "label": "Maranh\u00e3o",
            "value": "MA"
        }, {
            "label": "Mato Grosso",
            "value": "MT"
        }, {
            "label": "Mato Grosso do Sul",
            "value": "MS"
        }, {
            "label": "Minas Gerais",
            "value": "MG"
        }, {
            "label": "Paran\u00e1",
            "value": "PR"
        }, {
            "label": "Para\u00edba",
            "value": "PB"
        }, {
            "label": "Par\u00e1",
            "value": "PA"
        }, {
            "label": "Pernambuco",
            "value": "PE"
        }, {
            "label": "Piau\u00ed",
            "value": "PI"
        }, {
            "label": "Rio Grande do Norte",
            "value": "RN"
        }, {
            "label": "Rio Grande do Sul",
            "value": "RS"
        }, {
            "label": "Rio de Janeiro",
            "value": "RJ"
        }, {
            "label": "Rond\u00f4nia",
            "value": "RO"
        }, {
            "label": "Roraima",
            "value": "RR"
        }, {
            "label": "Santa Catarina",
            "value": "SC"
        }, {
            "label": "Sergipe",
            "value": "SE"
        }, {
            "label": "S\u00e3o Paulo",
            "value": "SP"
        }, {
            "label": "Tocantins",
            "value": "TO"
        }];

        document.getElementById('email').innerHTML =
            `<input id="email" name="email" type="text" class="form-control" disabled="" placeholder="${db.email}" value="${db.email}"></input>`
        document.getElementById('nome').innerHTML =
            `<input id="nome" name="nome" type="text" class="form-control" disabled="" placeholder="${db.nome}" value="${db.nome}">`;
        document.getElementById('telefone').innerHTML =
            `<input id="telefone" name="telefone" type="tel" class="form-control" value="${db.endereco.telefone}"></input>`;
        document.getElementById('endereco').innerHTML =
            `<input id="endereco" name="endereco" type="text" class="form-control" value="${db.endereco.endereco}"></input>`;
        document.getElementById('cidade').innerHTML =
            `<input id="cidade" name="cidade" type="text" class="form-control" value="${db.endereco.cidade}"></input>`;
        document.getElementById('pais').innerHTML =
            `<input id="pais" name="pais" type="text" class="form-control" value="${db.endereco.pais}"></input>`;
        document.getElementById('cep').innerHTML =
            `<input id="cep" name="cep" type="number" class="form-control" value="${db.endereco.cep}"></input>`;
        sHtml = '<select class="form-control" id="estado" name="estado">';
        for (i = 0; i < estados.length; i++) {
            if (estados[i].value == db.endereco.estado) {
                sHtml += `<option value="${estados[i].value}" selected>${estados[i].label}</option>`;
            } else {
                sHtml += `<option value="${estados[i].value}">${estados[i].label}</option>`;
            }
        }
        sHtml += `</select>`;
        document.getElementById('estado').innerHTML = sHtml;
    } else {
        window.location.href = "./index.html";
    }
}

$(document).ready(function () {
    $('form').submit(function (event) {
        var path = window.location.pathname;
        var page = path.split("/").pop();
        if (page == "painel-perfil.html") {
            UpdatePerfil(event);
        }
    })
});

function UpdatePerfil(event) {
    event.preventDefault();
    db = myuser;
    var email = document.getElementsByName('email')[0].value;
    var nome = document.getElementsByName('nome')[0].value;
    var telefone = document.getElementsByName('telefone')[0].value;
    var endereco = document.getElementsByName('endereco')[0].value;
    var cidade = document.getElementsByName('cidade')[0].value;
    var pais = document.getElementsByName('pais')[0].value;
    var cep = document.getElementsByName('cep')[0].value;
    var estado = document.getElementsByName('estado')[0].value;
    var senha = document.getElementsByName('senha')[0].value;
    var novasenha = document.getElementsByName('novasenha')[0].value;

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
    if (senha == null || senha == '' || senha.length == 0 || senha != db.senha ||
        ((novasenha != '' || (novasenha.length > 0)) && novasenha != senha)) {
        $("#senhaError").show();
        $('#senhaError').delay(5000).fadeOut('slow');
        return;
    }
    if ((novasenha != '' || (novasenha.length > 0))) {
        senha = novasenha;
    }

    $.ajax({
        url: 'http://'+host_ip+':8085/api/endereco/register/' + user_id,
        data: {
            endereco: endereco,
            telefone: telefone,
            CEP: cep,
            cidade: cidade,
            pais: pais,
            estado: estado
        },
        success: function (data) {
            console.log(JSON.stringify({
                endereco: endereco,
                telefone: telefone,
                CEP: cep,
                cidade: cidade,
                pais: pais,
                estado: estado
            }))
            console.log(data);
            if (data == "success") {
                $('#success').fadeIn('slow');
                $('#success').delay(3000).fadeOut('slow');
                setTimeout(function () {
                    window.location.href = "./painel-perfil.html";
                }, 3500);
            } else {
                console.log(data);
                $('#error').fadeIn('slow');
                $('#error').delay(5000).fadeOut('slow');
            }
        },
        type: "POST"
    })
}

function menuLocalTuristico() {
    var localHtml = `
    <center>
    <div id="localCriado" style="display:none">
        <div class="alert alert-success alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">Local Turistico criado com sucesso..</br>Em alguns segundos você será redirecionado para a lista de locais turisticos.</span>
        </div>
    </div>
    <div id="createnomeError" style="display:none">
        <div class="alert alert-danger alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">Nome não informado.</span>
        </div>
    </div>
    <div id="descError" style="display:none">
        <div class="alert alert-danger alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">Descrição não informada.</span>
        </div>
    </div>
    </center>
    <div class="row">
    <div class="col-md-12">
        <div class="card ">
    <div class="card-body">
    <center><h5>Criar novo Local Turistico:</h5></center>
    <form autocomplete="off" method="POST" name="cadastro">
      <div class="row">
        <div class="col-md-6 pr-1">
          <div class="form-group">
            <label>Nome:</label>
            <input name="nome" type="text" class="form-control" placeholder="Nome do Local Turistico">
          </div>
        </div>
        <div class="col-md-6 pr-1">
          <div class="form-group">
            <label>URL (Imagem do Local Turistico):</label>
            <input name="img" type="url" class="form-control" placeholder="padrão: ./assets/img/default-local.png">
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12 pr-1">
          <div class="form-group">
            <label>Descrição:</label>
            <textarea name="desc" cols="10" class="form-control textarea"></textarea>
          </div>
        </div>
      </div>
      </form>
      <div class="row">
        <div class="update ml-auto mr-auto">
          <input type="submit" class="btn btn-primary btn-round" value="Criar Local Turistico" onclick="return CadastrarLocalTuristico()">
        </div>
      </div>
  </div>
  </div>
  </div>
</div>`;
    document.getElementById('menuCriarLocalTuristico').innerHTML = localHtml;
}

function CadastrarLocalTuristico() {
    var nome = document.getElementsByName('nome')[0].value;
    var img = document.getElementsByName('img')[0].value;
    var desc = document.getElementsByName('desc')[0].value;

    if (nome == null || nome == '' || (nome.length == 0)) {
        $("#createnomeError").show();
        $('#createnomeError').delay(5000).fadeOut('slow');
        return;
    }
    if (desc == null || desc == '' || (desc.length == 0)) {
        $("#descError").show();
        $('#descError').delay(5000).fadeOut('slow');
        return;
    }

    var formData;
    var xhr = new XMLHttpRequest();
    xhr.open('HEAD', img, false);
    xhr.send();

    if (img == '' || img.length == 0) {
        xhr.status = "404";
    }

    if (xhr.status == "404") {
        formData = JSON.stringify({
            "nome": nome,
            "descricao": desc,
        });
    } else {
        formData = JSON.stringify({
            "nome": nome,
            "descricao": desc,
            "imgUrl": img
        });
    }

    $.ajax({
        url: 'http://'+host_ip+':8085/api/localidades',
        contentType: 'application/json',
        cache: false,
        method: 'POST',
        dataType: 'json',
        data: formData,
        success: function (data) {
            if (data != null || data != '') {
                $("#localCriado").show();
                $('#localCriado').delay(2500).fadeOut('slow');
                setTimeout(function () {
                    window.location.href = "./painel.html";
                }, 3000);
            } else {
                alert("Erro ao criar local turistico.");
            }
        }
    });
}