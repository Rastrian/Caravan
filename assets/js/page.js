var path = window.location.pathname;
var page = path.split("/").pop();
if (readCookie("caravan.login.userid") == '') {
    redirect();
    deleteCookie("caravan.login.userid");
} else {
    var user_id = readCookie("caravan.login.userid");
    var request = new XMLHttpRequest();
    request.open("GET", "http://"+host_ip+":8085/api/users/" + user_id, false);
    request.send(null);
    if (request.responseText == null || request.responseText == '') {
        deleteCookie("caravan.login.userid");
        redirect();
    }
    writeCookie("caravan.page", page, 4);

    var request = new XMLHttpRequest();
    request.open("GET", "http://"+host_ip+":8085/api/users/" + user_id + "/turista", false);
    request.send(null);
    var turista = request.responseText;

    var request = new XMLHttpRequest();
    request.open("GET", "http://"+host_ip+":8085/api/users/" + user_id + "/admin", false);
    request.send(null);
    var admin = request.responseText;
}

function redirect() {
    window.location.href = "./index.html";
    setTimeout(function () {
        window.location.href = "./index.html";
    }, 3000);
}