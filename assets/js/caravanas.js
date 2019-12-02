var request = new XMLHttpRequest();
request.open("GET", "http://"+host_ip+":8085/api/users/" + user_id + "/caravana/", false);
request.send(null);
var mycaravanas = JSON.parse(request.responseText);
var request = new XMLHttpRequest();
request.open("GET", "http://"+host_ip+":8085/api/localidades", false);
request.send(null);
var localidadesdb = JSON.parse(request.responseText);
const urlParams = new URLSearchParams(window.location.search);
var sHtml = '';

function listCaravanasByLocal() {
    const local = urlParams.get('local');
    if (local == null) {
        window.location.href = "./painel-not-found.html";
    } else {
        var request = new XMLHttpRequest();
        request.open("GET", "http://"+host_ip+":8085/api/caravanas/local/" + local, false);
        request.send(null);
        var caravanasdb = JSON.parse(request.responseText);
        listCaravanas(caravanasdb, false);
    }
}

function loadMinhasCaravanas() {
    if (turista == false || turista == 'false') {
        sHtml = `<center>
        <button type="submit" id="menuCaravana" class="btn btn-success btn-round" onclick="menuCriarCaravana()">Criar Caravana</button>
        </center>`;
        document.getElementById('amigonativo-tools2').innerHTML = sHtml;
    }
    listCaravanas(mycaravanas, true);
}

function CadastrarCaravana() {
    var nome = document.getElementsByName('nome')[0].value;
    var data = new Date(document.getElementsByName('data')[0].value);
    var locais = document.getElementsByName('locais')[0].value;
    var desc = document.getElementsByName('desc')[0].value;
    if (atualizar_caravana_id != null){
        var id = atualizar_caravana_id;
        var formData = JSON.stringify({
            "id": id,
            "nome": nome,
            "descricao": desc,
            "ownerId": parseInt(user_id),
            "localId": parseInt(locais),
            "dia": (parseInt(data.getDate()) + 1),
            "mes": (parseInt(data.getMonth()) + 1),
            "ano": (parseInt(data.getFullYear()))
        });
    }else{
        var formData = JSON.stringify({
            "nome": nome,
            "descricao": desc,
            "ownerId": parseInt(user_id),
            "localId": parseInt(locais),
            "dia": (parseInt(data.getDate()) + 1),
            "mes": (parseInt(data.getMonth()) + 1),
            "ano": (parseInt(data.getFullYear()))
        });
    }
    atualizar_caravana_id = null;
    var todayDate = new Date();
    if (nome == null || nome == '' || (nome.length == 0)) {
        $("#nomeError").show();
        $('#nomeError').delay(5000).fadeOut('slow');
        return;
    }
    if (data == 'Invalid Date' || data == null || data == '' || (data.length == 0)) {
        $("#dataError").show();
        $('#dataError').delay(5000).fadeOut('slow');
        return;
    }
    if (locais == 0 || locais == null || locais == '' || (locais.length == 0)) {
        $("#locaisError").show();
        $('#locaisError').delay(5000).fadeOut('slow');
        return;
    }
    if (desc == null || desc == '' || (desc.length == 0)) {
        $("#descError").show();
        $('#descError').delay(5000).fadeOut('slow');
        return;
    }
    if (todayDate > data) {
        $("#dataError").show();
        $('#dataError').delay(5000).fadeOut('slow');
        return;
    }
    $.ajax({
        url: 'http://'+host_ip+':8085/api/caravanas/register',
        contentType: 'application/json',
        cache: false,
        method: 'POST',
        dataType: 'json',
        data: formData,
        success: function (data) {
            if (data != null || data != '') {
                $("#caravanaCriada").show();
                $('#caravanaCriada').delay(5000).fadeOut('slow');
                entrarCaravana(data);
                setTimeout(function () {
                    window.location.href = "./caravana.html?id=" + data;
                }, 5000);
            } else {
                alert("Erro ao criar a caravana.");
            }
        }
    });
}

function menuCriarCaravana() {
    sHtml = `
    <center>
    <div id="caravanaCriada" style="display:none">
        <div class="alert alert-success alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">A Caravana foi criada com sucesso...</br>Em alguns segundos você será redirecionado a pagina da caravana...</span>
        </div>
    </div>
    <div id="nomeError" style="display:none">
        <div class="alert alert-danger alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">Nome não informado.</span>
        </div>
    </div>
    <div id="dataError" style="display:none">
        <div class="alert alert-danger alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">Data invalida.</span>
        </div>
    </div>
    <div id="locaisError" style="display:none">
        <div class="alert alert-danger alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">Local Turistico invalido.</span>
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
    <center><h5>Criar uma nova caravana:</h5></center>
    <form autocomplete="off" method="POST" name="cadastro">
      <div class="row">
        <div class="col-md-4 pr-1">
          <div class="form-group">
            <label>Nome:</label>
            <input name="nome" type="text" class="form-control" placeholder="Nome da Caravana">
          </div>
        </div>
        <div class="col-md-4 pr-1">
          <div class="form-group">
            <label>Data:</label>
            <input name="data" type="date" class="form-control">
          </div>
        </div>
        <div class="col-md-4 pr-1">
          <div class="form-group">
            <label>Local Turistico:</label>
            <select name="locais" class="form-control">
            <option value="0">Selecione um local</option>
        `;
    db = localidadesdb;
    if (db.length > 0) {
        for (i = 0; i < db.length; i++) {
            sHtml += `<option value="${db[i].id}">${db[i].nome}</option>`
        }
    }
    sHtml += `
            </select>
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
          <input type="submit" class="btn btn-primary btn-round" value="Criar Caravana" onclick="return CadastrarCaravana()">
        </div>
      </div>
  </div>
  </div>
  </div>
</div>`;
    atualizar_caravana_id = null;
    $('#menuCaravana').hide();
    document.getElementById('amigonativo-tools2').innerHTML = sHtml;
    $("#amigonativo-tools2").show();
}

function listCaravanas(db, delete_button) {
    counter = 0;
    sHtml += `<div class="row">`;
    if (db.length > 0) {
        for (i = 0; i < db.length; i++) {
            var xhr = new XMLHttpRequest();
            xhr.open('HEAD', db[i].imgUrl, false);
            xhr.send();

            if (xhr.status == "404" && db[i].imgUrl != './assets/img/default-caravana.png') {
                db[i].imgUrl = './assets/img/default-caravana.png';
            }

            sHtml += `
                <div class="col-sm"></div>
                <div class="col-sm-3">
                <div class="card" style="width: 18rem;">
                <a href="./caravana.html?id=${db[i].id}">
                <img class="card-img-top" src="${db[i].imgUrl}">
                <div class="card-body">
                <h5><font color="black">${db[i].nome}</font></h5>
                </a>
                `;
            if (db[i].descricao.length > 100) {
                sHtml += `
                    <p class="card-text"><font color="black" font-size=12px>${db[i].descricao.slice(0,100)}<span id="dots">...</span><span id="more">${db[i].descricao.slice(100)}</span></font></p>`;
            } else {
                sHtml += `
                    <p class="card-text"><font color="black" font-size=12px>${db[i].descricao}</font></p>`;
            }
            sHtml += `
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"> <strong>Data: </strong> &nbsp; ${db[i].dia}/${db[i].mes}/${db[i].ano}</li>
            </ul>
            <div class="card-body text-center">
                <a href="./caravana.html?id=${db[i].id}" class="card-link"><button type="button"
                        class="btn btn-success">&nbsp;Acessar&nbsp;</button></a>`;
            if (admin == 'true' || db[i].ownerId == parseInt(user_id)) {
                sHtml += `<button type="submit" class="btn btn-danger" onclick="deleteCaravana(` + db[i].id + `)">Deletar</button>`;
            }
            sHtml += `
            </div>
        </div>
    </div>
    <div class="col-sm"></div>`;
            counter++;
            if (counter == 3) {
                sHtml += `</div><div class="row">`;
            }
        }
    } else {
        sHtml = `<center><h5>Nenhuma caravana foi encontrada.</h5></center>`;
        $("#amigonativo-tools2").show();
    }
    sHtml += `</div>`;
    document.getElementById('lista').innerHTML = sHtml;
    if (db.length > 0) {
        for (i = 0; i < db.length; i++) {
            if (delete_button) {
                var hadCaravana = botaoCaravana(db[i].id)
            }
        }
    }
}

function loadCaravanaInfo() {
    const id = urlParams.get('id');
    if (id == null) {
        window.location.href = "./painel-not-found.html";
    } else {
        var request = new XMLHttpRequest();
        request.open("GET", "http://"+host_ip+":8085/api/caravanas/" + id, false);
        request.send(null);
        var db = JSON.parse(request.responseText);

        var request = new XMLHttpRequest();
        request.open("GET", "http://"+host_ip+":8085/api/users/" + db.ownerId, false);
        request.send(null);
        var ownerdb = JSON.parse(request.responseText);

        if (db != null || db != '') {
            document.getElementById('nome').innerHTML =
                `<center><h1 class="card-title"><b>${db.nome}</b></h1></center>`;
            if (db.descricao.length > 255) {
                document.getElementById('desc').innerHTML =
                    `<p class="blockquote blockquote-primary">
                ${db.descricao.slice(0,255)}<span id="dots">...</span><span id="more">${db.descricao.slice(255)}</span>
                <button onclick="lerMais()" id="myBtn">Ler mais</button> 
                </p>`;
            } else {
                document.getElementById('desc').innerHTML =
                    `<p class="blockquote blockquote-primary">
                ${db.descricao}
                </p>`;
            }
            document.getElementById('data').innerHTML =
                `<center><h5 class="card-subtitle">${db.dia}/${db.mes}/${db.ano}</h5></center>`;
            document.getElementById('owner-name').innerHTML =
                `${ownerdb.nome}`;
            document.getElementById('owner-email').innerHTML =
                `Email: ${ownerdb.email}`;
            var hadCaravana = botaoCaravana(id);
            if (hadCaravana) {
                document.getElementById('painel-usuario').innerHTML =
                    `<div class="card ">
                </div>`;
            }

            localId = db.localId;
            nome_caravana = db.nome;
            data_caravana = new Date(`${db.ano}/${db.mes}/${db.dia}`);
            data_caravana_string = `${db.ano}-${db.mes}-${db.dia}`;
            desc_caravana = db.descricao;
        } else {
            window.location.href = "./painel-not-found.html";
        }
    }
}

function botaoCaravana(id) {
    var request = new XMLHttpRequest();
    request.open("GET", "http://"+host_ip+":8085/api/users/" + user_id + "/caravana/" + id, false);
    request.send(null);
    var checkcaravana = request.responseText;

    var botao = `<button type="submit" class="btn btn-primary btn-round" onclick="entrarCaravana(` + id + `)">Entrar na Caravana</button>`;
    var hadCaravana = false;
    if (checkcaravana != '') {
        hadCaravana = true;
    }
    if (hadCaravana) {
        var db = JSON.parse(request.responseText);

        var request = new XMLHttpRequest();
        request.open("GET", "http://"+host_ip+":8085/api/users/" + db.ownerId, false);
        request.send(null);
        var ownerdb = JSON.parse(request.responseText);
        if (ownerdb.id == user_id) {
            if (readCookie("caravan.page") == 'caravana.html') {
                botao = `
                <button type="submit" class="btn btn-warning btn-round" onclick="atualizarCaravana(` + id + `)">Atualizar Caravana</button>
                <button type="submit" class="btn btn-danger btn-round" onclick="deleteCaravana(` + id + `)">Deletar Caravana</button>
                `;
            } else {
                botao = `
                <button type="submit" class="btn btn-danger btn-round" onclick="deleteCaravana(` + id + `)">Deletar Caravana</button>
                `;
            }
        } else {
            if (readCookie("caravan.page") == 'caravana.html') {
                botao = `
                <button type="submit" class="btn btn-danger btn-round" onclick="sairCaravana(` + id + `)">Sair da Caravana</button>`;
            } else {
                botao = `
                <button type="submit" class="btn btn-danger btn-round" onclick="sairCaravana(` + id + `)">Sair da Caravana</button>`;
            }
        }
    }
    console.log(botao);
    if (readCookie("caravan.page") == 'painel-minhas-caravanas.html') {
        document.getElementById('botaoCaravana-' + id).innerHTML = botao;
    } else {
        document.getElementById('botaoCaravana').innerHTML = botao;
    }
    return hadCaravana;
}

function atualizarCaravana(id) {
    sHtml = `
    <center>
    <div id="caravanaCriada" style="display:none">
        <div class="alert alert-success alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">A Caravana foi atualizada...</br>Em alguns segundos você será redirecionado a pagina da caravana...</span>
        </div>
    </div>
    <div id="nomeError" style="display:none">
        <div class="alert alert-danger alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">Nome não informado.</span>
        </div>
    </div>
    <div id="dataError" style="display:none">
        <div class="alert alert-danger alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">Data invalida.</span>
        </div>
    </div>
    <div id="locaisError" style="display:none">
        <div class="alert alert-danger alert-with-icon alert-dismissible fade show" data-notify="container">
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert" aria-label="Close">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span data-notify="icon" class="nc-icon nc-alert-circle-i"></span>
            <span data-notify="message">Local Turistico invalido.</span>
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
    <center><h5>Atualizar Caravana:</h5></center>
    <form autocomplete="off" method="POST" name="cadastro">
      <div class="row">
        <div class="col-md-4 pr-1">
          <div class="form-group">
            <label>Nome:</label>
            <input name="nome" type="text" class="form-control" placeholder="Nome da Caravana" value="`+nome_caravana+`">
          </div>
        </div>
        <div class="col-md-4 pr-1">
          <div class="form-group">
            <label>Data:</label>
            <input name="data" type="date" class="form-control" value="`+data_caravana_string+`">
          </div>
        </div>
        <div class="col-md-4 pr-1">
          <div class="form-group">
            <label>Local Turistico:</label>
            <select name="locais" class="form-control">
            <option value="0">Selecione um local</option>
        `;
    db = localidadesdb;
    if (db.length > 0) {
        for (i = 0; i < db.length; i++) {
            if (localId == db[i].id){
                sHtml += `<option value="${db[i].id}" selected>${db[i].nome}</option>`
            }else{
                sHtml += `<option value="${db[i].id}">${db[i].nome}</option>`
            }
        }
    }
    sHtml += `
            </select>
            </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12 pr-1">
          <div class="form-group">
            <label>Descrição:</label>
            <textarea name="desc" cols="10" class="form-control textarea">`+desc_caravana+`</textarea>
          </div>
        </div>
      </div>
      </form>
      <div class="row">
        <div class="update ml-auto mr-auto">
          <input type="submit" class="btn btn-primary btn-round" value="Atualizar Caravana" onclick="return CadastrarCaravana()">
        </div>
      </div>
  </div>
  </div>
  </div>
</div>`;
    document.getElementById('atualizar-caravana').innerHTML = sHtml;
    atualizar_caravana_id = id;
}

function entrarCaravana(id) {
    $.ajax({
        url: 'http://'+host_ip+':8085/api/users/' + user_id + '/caravana/add/' + id,
        type: "POST"
    });
    if (readCookie("caravan.page") == 'caravana.html') {
        location.reload();
    }
}

function sairCaravana(id) {
    $.ajax({
        url: 'http://'+host_ip+':8085/api/users/' + user_id + '/caravana/remove/' + id,
        type: "POST"
    });
    botaoCaravana(id);
    if (readCookie("caravan.page") == 'painel-minhas-caravanas.html') {
        location.reload();
    }
}

function deleteCaravana(id) {
    var request = new XMLHttpRequest();
    request.open("GET", "http://"+host_ip+":8085/api/caravanas/" + id, false);
    request.send(null);
    var db = JSON.parse(request.responseText);
    if (admin == 'true' || db.ownerId == parseInt(user_id)) {
        var r = confirm("Confirme que está caravana será deletada.");
        if (r == true) {
            $.ajax({
                url: 'http://'+host_ip+':8085/api/users/caravana/clean/' + id,
                type: "POST"
            });
            $.ajax({
                url: 'http://'+host_ip+':8085/api/caravanas/delete/' + id,
                type: "POST"
            });
            alert("Caravana deletada.");
            window.location.href = "./painel-minhas-caravanas.html";
        }
    } else {
        console.log("Sem permissão");
        alert("Você não tem permissão para deletar uma caravana.");
    }
}