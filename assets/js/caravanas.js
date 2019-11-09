const urlParams = new URLSearchParams(window.location.search);
var sHtml = '';

function listCaravanasByLocal() {
    const local = urlParams.get('local');
    if (local == null) {
        window.location.href = "./painel-not-found.html";
    } else {
        var request = new XMLHttpRequest();
        request.open("GET", "http://34.95.17.159:8085/api/caravanas/local/" + local, false);
        request.send(null);
        var caravanasdb = JSON.parse(request.responseText);
        listCaravanas(caravanasdb);
    }
}

function loadMinhasCaravanas(){
    var request = new XMLHttpRequest();
    request.open("GET", "http://34.95.17.159:8085/api/users/" + user_id + "/caravana/", false);
    request.send(null);
    var caravanas = JSON.parse(request.responseText);

    listCaravanas(caravanas);
}

function listCaravanas(db){
    if (db.length > 0) {
        for (i = 0; i < db.length; i++) {
            sHtml += `
                <div class="col-md-3"><center>
                <a href="./caravana.html?id=${db[i].id}">
                <h4><font color="black">${db[i].nome}</font></h4>
                <h5><font color="black">${db[i].dia}/${db[i].mes}/${db[i].ano}</font></h5>
                </a>
                <p><font color="black" font-size=12px>${db[i].descricao.slice(0,100)}<span id="dots">...</span><span id="more">${db[i].descricao.slice(100)}</span></font></p>
                <button onclick="lerMais()" id="myBtn">Ler mais</button> 
                <a href="./caravana.html?id=${db[i].id}">
                <img src="${db[i].imgUrl}" width="225" width="225" alt="${db[i].descricao}"></img></br>
                </a>
                </center></div>`;
        }
    } else {
        sHtml = `<center><h5>Nenhuma caravana foi encontrada.</h5></center>`;
    }
    document.getElementById('lista').innerHTML = sHtml;
}

function loadCaravanaInfo() {
    const id = urlParams.get('id');
    if (id == null) {
        window.location.href = "./painel-not-found.html";
    } else {
        var request = new XMLHttpRequest();
        request.open("GET", "http://34.95.17.159:8085/api/caravanas/" + id, false);
        request.send(null);
        var db = JSON.parse(request.responseText);

        var request = new XMLHttpRequest();
        request.open("GET", "http://34.95.17.159:8085/api/users/" + db.ownerId, false);
        request.send(null);
        var ownerdb = JSON.parse(request.responseText);

        if (db != null || db != '') {
            document.getElementById('nome').innerHTML =
                `<center><h1 class="card-title"><b>${db.nome}</b></h1></center>`;
            document.getElementById('desc').innerHTML =
                `<p class="blockquote blockquote-primary">
                ${db.descricao.slice(0,255)}<span id="dots">...</span><span id="more">${db.descricao.slice(255)}</span>
                <button onclick="lerMais()" id="myBtn">Ler mais</button> 
                </p>`;
            document.getElementById('data').innerHTML =
                `<center><h5 class="card-subtitle">${db.dia}/${db.mes}/${db.ano}</h5></center>`;
            document.getElementById('owner-name').innerHTML =
                `${ownerdb.nome}`;
            document.getElementById('owner-email').innerHTML =
                `${ownerdb.email}`;
            var hadCaravana = botaoCaravana(id);

        } else {
            window.location.href = "./painel-not-found.html";
        }
    }
}

function botaoCaravana(id){
    var request = new XMLHttpRequest();
    request.open("GET", "http://34.95.17.159:8085/api/users/" + user_id + "/caravana/" + id, false);
    request.send(null);
    var checkcaravana = request.responseText;

    var botao = `<button type="submit" class="btn btn-primary btn-round" onclick="entrarCaravana(`+id+`)">Entrar na Caravana</button>`;
    var hadCaravana = false;
    if (checkcaravana != '') {
        botao = `<button type="submit" class="btn btn-danger btn-round" onclick="sairCaravana(`+id+`)">Sair da Caravana</button>`;
    } else {
        hadCaravana = true;
    }
    document.getElementById('botaoCaravana').innerHTML = botao;
    return hadCaravana;
}

function entrarCaravana(id) {
    $.ajax({
        url: 'http://34.95.17.159:8085/api/users/' + user_id + '/caravana/add/' + id,
        type: "POST"
    });
    botaoCaravana(id);
}

function sairCaravana(id) {
    $.ajax({
        url: 'http://34.95.17.159:8085/api/users/' + user_id + '/caravana/remove/' + id,
        type: "POST"
    });
    botaoCaravana(id);
}