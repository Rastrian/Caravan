var request = new XMLHttpRequest();
request.open("GET", "http://34.95.17.159:8085/api/users/", false);
request.send(null);
var usersdb = JSON.parse(request.responseText);

var request = new XMLHttpRequest();
request.open("GET", "http://34.95.17.159:8085/api/users/" + user_id, false);
request.send(null);
var myuser = JSON.parse(request.responseText);

function loadProfilePage(){
    if (myuser != null || myuser != ''){
        db = myuser;
        document.getElementById('user').innerHTML =
        `${db.nome}`;
        let cargo = '';
        if (db.admin == true){
            cargo = 'Administrador'
        }else{
            if (db.turista == true){
                cargo = 'Turista';
            }else{
                cargo = 'Amigo Nativo';
            }
        }
        if (cargo != ''){
            document.getElementById('cargo').innerHTML = cargo;
        }
        document.getElementById('email').innerHTML = 
        `<input type="text" class="form-control" disabled="" placeholder="${db.email}" value="${db.email}"></input>`
        document.getElementById('nome').innerHTML = 
        `<input type="text" class="form-control" disabled="" placeholder="${db.nome}" value="${db.nome}">`;
    }else{
        window.location.href = "./index.html";
    }
}