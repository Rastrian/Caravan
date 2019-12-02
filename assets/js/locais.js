var request = new XMLHttpRequest();
request.open("GET", "http://"+host_ip+":8085/api/localidades", false);
request.send(null);
var localidadesdb = JSON.parse(request.responseText);
var sHtml = '';

var user_id = readCookie("caravan.login.userid");

var request = new XMLHttpRequest();
request.open("GET", "http://"+host_ip+":8085/api/users/" + user_id + "/admin", false);
request.send(null);
var admin = request.responseText;

function listarLocaisTuristicos() {
    db = localidadesdb;
    if (db.length > 0) {
        counter = 0;
        sHtml += `<div class="row">`;
        for (i = 0; i < db.length; i++) {
            if (db[i].imgUrl == '' || db[i].imgUrl.length == 0) {
                db[i].imgUrl = "./assets/img/default-local.png";
            }
            sHtml += `
                <div class="col-sm"></div>
                <div class="col-sm-3">
                <div class="card" style="width: 18rem;">
                <a href="./painel-lista-caravanas.html?local=${db[i].id}">
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
            <div class="card-body text-center">
                <a href="./painel-lista-caravanas.html?local=${db[i].id}" class="card-link"><button type="button"
                        class="btn btn-success">&nbsp;Acessar&nbsp;</button></a>`;
            if (admin == 'true') {
                sHtml += `<button type="submit" class="btn btn-danger" onclick="deleteLocalTuristico(` + db[i].id + `)">Deletar</button>`;
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
        sHtml = `<center><h5>Nenhum local turistico foi encontrado.</h5></center>`;
    }
    document.getElementById('lista').innerHTML = sHtml;
}

function deleteLocalTuristico(id) {
    var confirmar = confirm("Você tem certeza que quer deletar este local e todas as caravanas do mesmo?");
    if (confirmar) {
        var request = new XMLHttpRequest();
        request.open("GET", "http://"+host_ip+":8085/api/caravanas/local/" + id, false);
        request.send(null);
        var caravanasdb = JSON.parse(request.responseText);

        for (i = 0; i < caravanasdb.length; i++) {
            $.ajax({
                url: 'http://'+host_ip+':8085/api/users/caravana/clean/' + caravanasdb[i].id,
                type: "POST"
            });
            $.ajax({
                url: 'http://'+host_ip+':8085/api/caravanas/delete/' + caravanasdb[i].id,
                type: "POST"
            });
        }

        $.ajax({
            url: 'http://'+host_ip+':8085/api/localidades/delete/' + id,
            type: "POST",
            success: function (data) {
                console.log(data);
            }
        });
        location.reload();
    }
}