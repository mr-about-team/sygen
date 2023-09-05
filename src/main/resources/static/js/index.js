let objects_list = []
let compteur = 0;

function drad_and_drop() {
  group1.style.overflow = "hidden";
  group1.style.overflowY = "scroll";
  notes.style.display = "block"


  let object = document.getElementById('file');
  // console.log(object.files[0].name[0])
  objects_list.push(object);
  if (objects_list[0].files && objects_list[0].files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {

      var contenuFichier = e.target.result;
      var workbook = XLSX.read(contenuFichier, { type: 'binary' });
      // console.log(workbook.SheetNames[0]);
      var nomFeuille = workbook.SheetNames[0];
      var feuille = workbook.Sheets[nomFeuille];
      var contenu = XLSX.utils.sheet_to_html(feuille);

      var divAffichage = document.getElementById('notes');

      divAffichage.innerHTML = contenu;
      // console.log('ok')
    };
    reader.readAsBinaryString(objects_list[0].files[0]);
  }
}


function del(id) {
  objects_list.splice()
  notes.style.display = "none"
}


function afficher_tableau(id) {
  if (id == "bt1-page2") {
    pagebt1.style.display = "block";
    pagebt2.style.display = "none";
    pagebt3.style.display = "none";
    pagebt4.style.display = "none";
    pagebt5.style.display = "none";


  } else if (id == "bt2-page2") {
    pagebt1.style.display = "none";
    pagebt2.style.display = "block";
    pagebt3.style.display = "none";
    pagebt4.style.display = "none";
    pagebt5.style.display = "none";

  }
  else if (id == "bt3-page2") {
    pagebt1.style.display = "none";
    pagebt2.style.display = "none";
    pagebt3.style.display = "block";
    pagebt4.style.display = "none";
    pagebt5.style.display = "none";

  }
  else if (id == "bt4-page2") {
    pagebt1.style.display = "none";
    pagebt2.style.display = "none";
    pagebt3.style.display = "none";
    pagebt4.style.display = "block";
    pagebt5.style.display = "none";

  }
  else if (id == "bt5-page2") {
    pagebt1.style.display = "none";
    pagebt2.style.display = "none";
    pagebt3.style.display = "none";
    pagebt4.style.display = "none";
    pagebt5.style.display = "block";

  }
  else if (id == "pageform") {
    pagebt1.style.display = "none";
    pagebt2.style.display = "none";
    pagebt3.style.display = "none";
    pagebt4.style.display = "none";
    pagebt5.style.display = "none";
    pageform.style.display = "block"

  }
  else {
    console.log("bad boutton id sendded")
  }
}


function generate_table() {
  // get the reference for the body
  var body = document.getElementsByTagName("body")[0];
  // creates a <table> element and a <tbody> element
  var tbl = document.createElement("table");
  tbl.id = "customers"
  var tblBody = document.createElement("tbody");

  // creating all cells
  for (var i = 0; i < 2; i++) {
    // creates a table row
    var row = document.createElement("tr");

    for (var j = 0; j < 2; j++) {
      // Create a <td> element and a text node, make the text
      // node the contents of the <td>, and put the <td> at
      // the end of the table row
      var cell = document.createElement("td");
      cell.id = "1"


      // cell.style.backgroundColor = "red"
      // let image= document.createElement('img')
      // image.src= "/home/tamanoir/Downloads/a.jpg"
      // cell.appendChild(image)
      // cell.cet('<img src="/home/tamanoir/Downloads/a.jpg" alt="" srcset="">')



      var cellText = document.createTextNode(
        "cell in row " + i + ", column " + j,
      );
      cell.appendChild(cellText);
      row.appendChild(cell);
    }

    // add the row to the end of the table body
    tblBody.appendChild(row);
  }

  // put the <tbody> in the <table>
  tbl.appendChild(tblBody);
  // appends <table> into <body>
  body.appendChild(tbl);
  // sets the border attribute of tbl to 2;
  // tbl.setAttribute("border", "2");

  tbl.style.border = "2px red solid"
}
// generate_table()

function change(id) {
  var form = document.getElementById(id)
  console.log(form)
  form.querySelector('input').click()


}

function mafonct(mot) {
  // var mot = "ceci_est_un_exemple_de_mot";

  var motsSeparés = mot.split("_"); // Divise le mot en un tableau en utilisant "_" comme séparateur
  var variables = []; // Variable pour stocker les éléments précédant chaque "_"

  for (var i = 0; i < motsSeparés.length; i++) {
    // Vérifie si l'élément courant n'est pas le premier élément du tableau

    variables.push(motsSeparés[i]); // Ajoute l'élément précédant "_" dans le tableau variables

  }

  return variables; // Affiche les éléments précédant chaque "_" dans la console
}


function testerMot(mot) {
  var regex = /^[A-Z]{3}\d{3}_[A-Z]{2}_[A-Z]\d_\d{4}_\d{2}$/; // Expression régulière pour vérifier le format
  var donnees = []
  if (regex.test(mot)) {
    console.log("Le mot respecte le format XXXOOO_XO_XX_OO_OOOO !");
    donnees = mafonct(mot)
    return donnees;

  } else {
    console.log("Le mot ne respecte pas le format XXXOOO_XO_XX_OO_OOOO !");
    return false;
  }
}

function formulaire(event) {
  event.preventDefault();

  var form = document.getElementById('form2');
  object = document.getElementById('file');
  console.log(object.files)
  var nom = object.files[0].name;
  var filename = nom.split(".")
  var donnees = testerMot(filename[0]);
  if (donnees == false) {
    // window.location=window.location
    tooltip('erreur');
  } else {
    console.log("Nom du fichier : " + filename[0]);

    console.log("erreur")

    var code = donnees[0]
    var type = donnees[1];
    var noteSur = donnees[4]
    var file = object.files[0]
    var input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name", "code");
    input.setAttribute("value", code);
    form.appendChild(input);
    var input1 = document.createElement("input");
    input1.setAttribute("type", "hidden");
    input1.setAttribute("name", "typeEval");
    input1.setAttribute("value", type);
    form.appendChild(input1);
    var input2 = document.createElement("input");
    input2.setAttribute("type", "hidden");
    input2.setAttribute("name", "noteSur");
    input2.setAttribute("value", noteSur);
    form.appendChild(input2);
    var input3 = document.getElementById('file');
    input3.setAttribute("name", "file");
    input3.setAttribute("value", file);

    console.log(input3.name)
    form.appendChild(input3);
    form.submit();


  }

}


window.onload = fonc

function fonc() {



  var po = document.querySelectorAll('#list');
  console.log(po)
  var tab = document.querySelector("#customers")
  var row = tab.querySelector("tbody").rows
  console.log(row)
  for (let i = 1; i < row.length; i++) {
    val = po[i - 1].querySelector('span').innerHTML;
    if (val == 1) {
      row[i].cells[2].style.display = ""
      row[i].cells[3].style.display = "none"
    }

  }

  var classe = document.getElementById('data')
  var table = classe.querySelector('table')
  var tbody = table.querySelectorAll('tr')

  if (tbody.length > 3) {
    var group1 = document.getElementById("group1");
    group1.style.overflow = "hidden";
    group1.style.overflowY = "scroll";
    // classe.style.display = ""
    notes = group1.querySelector('div')
    table.id = "notes"
    group1.innerHTML = classe.innerHTML
    var del = document.getElementById('delete');
    del.addEventListener("click", function () {
      const newUrl = 'http://localhost:8080/index-import'
      window.location = newUrl;
    })
  } else {
    console.log(tbody)
  }

  // Obtenez la chaîne de requête de l'URL actuelle
  var queryString = window.location.search;

  // Créez un objet URLSearchParams à partir de la chaîne de requête
  var params = new URLSearchParams(queryString);
  if (params != null) {

    // Obtenez la valeur du paramètre 'status'
    var status = params.get('status');

    // Vérifiez si la valeur du paramètre 'status' est définie
    if (status) {
      // Utilisez la valeur du paramètre 'status' comme vous le souhaitez
      console.log('Statut :', status);
      tooltip(status)
    }
  } else {
    console.log("erreur")
  }

  // params.delete('status');
  // const newUrl = window.location.pathname + '?' + params.toString();
  // window.history.replaceState({}, '', newUrl);


}

function addPayment(element) {
  pagebt1.style.display = "none";
  pagebt2.style.display = "none";
  pagebt3.style.display = "none";
  pagebt4.style.display = "none";
  pagebt5.style.display = "none";
  pageform.style.display = "block"
  var id = element.getAttribute('data-id')
  var form = document.getElementById('payement-id');
  var actionUrl = form.getAttribute('action').replace("{id}", id);
  form.setAttribute('action', actionUrl);
  console.log(form)
  console.log(element);
  console.log(id);
}

function changebtn(id) {
  if (id == 'bt1') {
    document.getElementById('container').style.display = ""
    document.getElementById('bt1').style.backgroundColor = "#563e92"
    document.getElementById('bt2').style.backgroundColor = "#303133"
    document.getElementById('bt1').style.borderColor = "#563e92"
    document.getElementById('bt2').style.borderColor = "#303133"
  } else if (id == 'bt2') {
    document.getElementById('container').style.display = "none"
    document.getElementById('bt1').style.backgroundColor = "#303133"
    document.getElementById('bt2').style.backgroundColor = "#563e92"
    document.getElementById('bt1').style.borderColor = "#303133"
    document.getElementById('bt2').style.borderColor = "#563e92"
  }
}
function tooltip(valeur) {

  var tooltip = document.createElement('div');
  tooltip.classList.add('tooltip');
  if (valeur == 'success') {
    tooltip.textContent = "Importer avec Success";
  } else if (valeur == 'erreur') {
    tooltip.textContent = "Erreur du formatage du fichier veiller respecter le nom et l'entete du fonction";
  } else if (valeur == 'bad') {
    tooltip.textContent = "Une erreur c'est produit lors de l'importation";
  } else if (valeur == 'fail') {
    tooltip.textContent = "Une erreur c'est produit lors de l'importation";
  }
  document.body.appendChild(tooltip);

  setTimeout(function () {
    tooltip.style.visibility = 'visible';
    tooltip.style.opacity = '1';
  }, 1000); // Délai de 1 seconde avant d'afficher l'infobulle

  setTimeout(function () {
    tooltip.style.visibility = 'hidden';
    tooltip.style.opacity = '0';
    document.body.removeChild(tooltip);
  }, 5000); // Durée d'affichage de l'infobulle (5 secondes dans cet exemple)

}

function verifier(typeEval, code) {
  var ligne
  var cases
  var row
  var target

    target = event.target; // La cellule sur laquelle on a cliqué
    row = target.parentNode; // La ligne contenant la cellule
    cases = row.parentNode
    ligne = cases.parentNode
    console.log(ligne);
    var elt = ligne.querySelectorAll('td')
    if (elt[2].style.display == "none") {
      console.log(elt)
      return ;
    } else if (elt[3].style.display == "none"){
      trach(typeEval, code)
    }

}

function trach(typeEval, code) {

  console.log("okay")

  const confirmation = confirm("Êtes-vous sûr de vouloir supprimer les donnees importes du" + typeEval + " d'" + code);
  if (confirmation) {

    var form = document.createElement('form');
    form.setAttribute('method', 'POST');
    form.setAttribute('action', '/delete');
    var input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name", "code");
    input.setAttribute("value", code);
    form.appendChild(input);
    var input1 = document.createElement("input");
    input1.setAttribute("type", "hidden");
    input1.setAttribute("name", "typeEval");
    input1.setAttribute("value", typeEval);
    form.appendChild(input1);
    document.body.appendChild(form);
    console.log(code)
    console.log(typeEval)
    console.log(form)
    form.submit()
  } else {
    return;
  }
}



function eye(typeEval, code) {
  var form = document.createElement('form');
  form.setAttribute('method', 'GET');
  form.setAttribute('action', '/shows-data');
  var input = document.createElement("input");
  input.setAttribute("type", "hidden");
  input.setAttribute("name", "code");
  input.setAttribute("value", code);
  form.appendChild(input);
  var input1 = document.createElement("input");
  input1.setAttribute("type", "hidden");
  input1.setAttribute("name", "typeEval");
  input1.setAttribute("value", typeEval);
  form.appendChild(input1);
  document.body.appendChild(form);


  // console.log(table)

  // console.log(group1)
  // // table.id="group1"


  // console.log(code)  
  // console.log(typeEval)
  // console.log(form)
  // console.log(table)
  form.submit()






}



