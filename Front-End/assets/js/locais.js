var request = new XMLHttpRequest();
request.open("GET", "http://34.95.17.159:8085/api/localidades", false);
request.send(null);
var localidadesdb = JSON.parse(request.responseText);
var sHtml = '';

function listarLocaisTuristicos(){
    db = localidadesdb;
    if (db.length > 0){
        for (i = 0; i < db.length; i++) {
        sHtml += `
                <div class="col-md-3"><center>
                <a href="./painel-lista-caravanas.html?local=${db[i].id}">
                <h4><font color="black">${db[i].nome}</font></h4>
                </a>
                <p><font color="black" font-size=12px>${db[i].descricao.slice(0,100)}<span id="dots">...</span><span id="more">${db[i].descricao.slice(100)}</span></font></p>
                <button onclick="lerMais()" id="myBtn">Ler mais</button> 
                <a href="./painel-lista-caravanas.html?local=${db[i].id}">
                <img src="${db[i].imgUrl}" width="225" width="225" alt="${db[i].descricao}"></img></br>
                </a>
                </center></div>`;
        }
    }else{
        sHtml = `<center><h5>Nenhum local turistico foi encontrado.</h5></center>`;
    }
    document.getElementById('lista').innerHTML = sHtml;
}