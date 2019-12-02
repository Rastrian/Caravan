var navbar = `
<li class="active ">
<a href="./painel.html">
  <i class="nc-icon nc-bank"></i>
  <p>Inicio</p>
</a>
</li>
<li>
<a href="#">
  <i class="nc-icon nc-spaceship"></i>
  <p>meu pau</p>
</a>
</li>
<li>
<a href="#">
  <i class="nc-icon nc-single-02"></i>
  <p>Meu Perfil</p>
</a>
</li>
<li>
<a href="./index.html">
  <i class="nc-icon nc-share-66"></i>
  <p>Deslogar</p>
</a>
</li>`;

document.getElementsByClassName("nav").innerHTML = navbar;

function lerMais() {
    var dots = document.getElementById("dots");
    var moreText = document.getElementById("more");
    var btnText = document.getElementById("myBtn");
  
    if (dots.style.display === "none") {
      dots.style.display = "inline";
      btnText.innerHTML = "Ler mais";
      moreText.style.display = "none";
    } else {
      dots.style.display = "none";
      btnText.innerHTML = "Ler menos";
      moreText.style.display = "inline";
    }
  }