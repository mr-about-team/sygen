function addData() {
    // Récupération des valeurs du formulaire
    var ue = document.getElementById("ue").value;
    var examen = document.getElementById("examen").value;
    var note = document.getElementById("notes").value;
    var semestre = document.getElementById("semestre").value;
    var année = document.getElementById("année").value;

    // Création d'une nouvelle ligne dans le tableau
    var table = document.getElementById("dataHead");
    var newRow = table.insertRow();

    // Insertion des cellules dans la nouvelle ligne
    var cell1 = newRow.insertCell(0);
    var cell2 = newRow.insertCell(1);
    var cell3 = newRow.insertCell(2);
    var cell4 = newRow.insertCell(3);
    var cell5 = newRow.insertCell(4);

    // Remplissage des cellules avec les valeurs du formulaire
    cell1.innerHTML = ue;
    cell2.innerHTML = examen;
    cell3.innerHTML = note;
    cell4.innerHTML = semestre;
    cell5.innerHTML = année;

    //Réinitialisation du formulaire
    document.getElementById("myForm").reset();
}



(function() {
    "use strict";

    /**
     * Easy selector helper function
     */
    const select = (el, all = false) => {
        el = el.trim()
        if (all) {
            return [...document.querySelectorAll(el)]
        } else {
            return document.querySelector(el)
        }
    }

    /**
     * Easy event listener function
     */
    const on = (type, el, listener, all = false) => {
        if (all) {
            select(el, all).forEach(e => e.addEventListener(type, listener))
        } else {
            select(el, all).addEventListener(type, listener)
        }
    }

    /**
     * Easy on scroll event listener 
     */
    const onscroll = (el, listener) => {
        el.addEventListener('scroll', listener)
    }

    /**
     * Sidebar toggle
     */
    if (select('.toggle-sidebar-btn')) {
        on('click', '.toggle-sidebar-btn', function(e) {
            select('body').classList.toggle('toggle-sidebar')
        })
    }

    /**
     * Search bar toggle
     */
    if (select('.search-bar-toggle')) {
        on('click', '.search-bar-toggle', function(e) {
            select('.search-bar').classList.toggle('search-bar-show')
        })
    }

    /**
     * Navbar links active state on scroll
     */
    let navbarlinks = select('#navbar .scrollto', true)
    const navbarlinksActive = () => {
        let position = window.scrollY + 200
        navbarlinks.forEach(navbarlink => {
            if (!navbarlink.hash) return
            let section = select(navbarlink.hash)
            if (!section) return
            if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
                navbarlink.classList.add('active')
            } else {
                navbarlink.classList.remove('active')
            }
        })
    }
    window.addEventListener('load', navbarlinksActive)
    onscroll(document, navbarlinksActive)

    /**
     * Toggle .header-scrolled class to #header when page is scrolled
     */
    let selectHeader = select('#header')
    if (selectHeader) {
        const headerScrolled = () => {
            if (window.scrollY > 100) {
                selectHeader.classList.add('header-scrolled')
            } else {
                selectHeader.classList.remove('header-scrolled')
            }
        }
        window.addEventListener('load', headerScrolled)
        onscroll(document, headerScrolled)
    }

    /**
     * Back to top button
     */
    let backtotop = select('.back-to-top')
    if (backtotop) {
        const toggleBacktotop = () => {
            if (window.scrollY > 100) {
                backtotop.classList.add('active')
            } else {
                backtotop.classList.remove('active')
            }
        }
        window.addEventListener('load', toggleBacktotop)
        onscroll(document, toggleBacktotop)
    }

    /**
     * Initiate tooltips
     */
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl)
    })

    /**
     * Initiate quill editors
     */
    if (select('.quill-editor-default')) {
        new Quill('.quill-editor-default', {
            theme: 'snow'
        });
    }

    if (select('.quill-editor-bubble')) {
        new Quill('.quill-editor-bubble', {
            theme: 'bubble'
        });
    }

    if (select('.quill-editor-full')) {
        new Quill(".quill-editor-full", {
            modules: {
                toolbar: [
                    [{
                        font: []
                    }, {
                        size: []
                    }],
                    ["bold", "italic", "underline", "strike"],
                    [{
                            color: []
                        },
                        {
                            background: []
                        }
                    ],
                    [{
                            script: "super"
                        },
                        {
                            script: "sub"
                        }
                    ],
                    [{
                            list: "ordered"
                        },
                        {
                            list: "bullet"
                        },
                        {
                            indent: "-1"
                        },
                        {
                            indent: "+1"
                        }
                    ],
                    ["direction", {
                        align: []
                    }],
                    ["link", "image", "video"],
                    ["clean"]
                ]
            },
            theme: "snow"
        });
    }

    /**
     * Initiate TinyMCE Editor
     */
    const useDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;
    const isSmallScreen = window.matchMedia('(max-width: 1023.5px)').matches;

    tinymce.init({
        selector: 'textarea.tinymce-editor',
        plugins: 'preview importcss searchreplace autolink autosave save directionality code visualblocks visualchars fullscreen image link media template codesample table charmap pagebreak nonbreaking anchor insertdatetime advlist lists wordcount help charmap quickbars emoticons',
        editimage_cors_hosts: ['picsum.photos'],
        menubar: 'file edit view insert format tools table help',
        toolbar: 'undo redo | bold italic underline strikethrough | fontfamily fontsize blocks | alignleft aligncenter alignright alignjustify | outdent indent |  numlist bullist | forecolor backcolor removeformat | pagebreak | charmap emoticons | fullscreen  preview save print | insertfile image media template link anchor codesample | ltr rtl',
        toolbar_sticky: true,
        toolbar_sticky_offset: isSmallScreen ? 102 : 108,
        autosave_ask_before_unload: true,
        autosave_interval: '30s',
        autosave_prefix: '{path}{query}-{id}-',
        autosave_restore_when_empty: false,
        autosave_retention: '2m',
        image_advtab: true,
        link_list: [{
                title: 'My page 1',
                value: 'https://www.tiny.cloud'
            },
            {
                title: 'My page 2',
                value: 'http://www.moxiecode.com'
            }
        ],
        image_list: [{
                title: 'My page 1',
                value: 'https://www.tiny.cloud'
            },
            {
                title: 'My page 2',
                value: 'http://www.moxiecode.com'
            }
        ],
        image_class_list: [{
                title: 'None',
                value: ''
            },
            {
                title: 'Some class',
                value: 'class-name'
            }
        ],
        importcss_append: true,
        file_picker_callback: (callback, value, meta) => {
            /* Provide file and text for the link dialog */
            if (meta.filetype === 'file') {
                callback('https://www.google.com/logos/google.jpg', {
                    text: 'My text'
                });
            }

            /* Provide image and alt text for the image dialog */
            if (meta.filetype === 'image') {
                callback('https://www.google.com/logos/google.jpg', {
                    alt: 'My alt text'
                });
            }

            /* Provide alternative source and posted for the media dialog */
            if (meta.filetype === 'media') {
                callback('movie.mp4', {
                    source2: 'alt.ogg',
                    poster: 'https://www.google.com/logos/google.jpg'
                });
            }
        },
        templates: [{
                title: 'New Table',
                description: 'creates a new table',
                content: '<div class="mceTmpl"><table width="98%%"  border="0" cellspacing="0" cellpadding="0"><tr><th scope="col"> </th><th scope="col"> </th></tr><tr><td> </td><td> </td></tr></table></div>'
            },
            {
                title: 'Starting my story',
                description: 'A cure for writers block',
                content: 'Once upon a time...'
            },
            {
                title: 'New list with dates',
                description: 'New List with dates',
                content: '<div class="mceTmpl"><span class="cdate">cdate</span><br><span class="mdate">mdate</span><h2>My List</h2><ul><li></li><li></li></ul></div>'
            }
        ],
        template_cdate_format: '[Date Created (CDATE): %m/%d/%Y : %H:%M:%S]',
        template_mdate_format: '[Date Modified (MDATE): %m/%d/%Y : %H:%M:%S]',
        height: 600,
        image_caption: true,
        quickbars_selection_toolbar: 'bold italic | quicklink h2 h3 blockquote quickimage quicktable',
        noneditable_class: 'mceNonEditable',
        toolbar_mode: 'sliding',
        contextmenu: 'link image table',
        skin: useDarkMode ? 'oxide-dark' : 'oxide',
        content_css: useDarkMode ? 'dark' : 'default',
        content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:16px }'
    });

    /**
     * Initiate Bootstrap validation check
     */
    var needsValidation = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(needsValidation)
        .forEach(function(form) {
            form.addEventListener('submit', function(event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })

    /**
     * Initiate Datatables
     */
    const datatables = select('.datatable', true)
    datatables.forEach(datatable => {
        new simpleDatatables.DataTable(datatable);
    })

    /**
     * Autoresize echart charts
     */
    const mainContainer = select('#main');
    if (mainContainer) {
        setTimeout(() => {
            new ResizeObserver(function() {
                select('.echart', true).forEach(getEchart => {
                    echarts.getInstanceByDom(getEchart).resize();
                })
            }).observe(mainContainer);
        }, 200);
    }

})();

/*var heureActuelle = document.getElementById("heure").textContent;


function afficherHeure() {
  var date = new Date();
  var heure = date.getHours();
  var minute = date.getMinutes();
  var seconde = date.getSeconds();

  heure = heure < 10 ? "0" + heure : heure;
  minute = minute < 10 ? "0" + minute : minute;
  seconde = seconde < 10 ? "0" + seconde : seconde;

  heureActuelle = heure + ":" + minute + ":" + seconde;
}

setInterval(afficherHeure, 1000);

var dateDuJour = document.getElementById("date").textContent;

function afficherDate(){
  var date = new Date();
  var jour = date.getDate();
  var mois = date.getMonth() + 1;
  var annee = date.getFullYear();

  dateDuJour = jour + "/" + mois + "/" + annee;
}

afficherDate();*/


// ****************debut du script  de la partie importation****************************//



object= document.getElementById("input1");














function afficher_contenu2(id , idbouton) {
    block1.style.display = "block"


    idbouton =idbouton -1
    input11.style.display = "none";
    notes.style.display = "block"
    div22.style.overflow = "hidden";

    div22.style.overflowY = "scroll";
    div22.style.paddingLeft = "10%";
    unselect.style.display = "block"    

    // notes.style.display = "block";
    object= document.getElementById('input1');
    console.log(object.files[0].name)
    if (object.files && object.files[idbouton]) {

        var reader = new FileReader();

        reader.onload = function(e) {

            var contenuFichier = e.target.result;
            var workbook = XLSX.read(contenuFichier, { type: 'binary' });
            console.log(workbook.SheetNames[0]);
            var nomFeuille = workbook.SheetNames[0];
            var feuille = workbook.Sheets[nomFeuille];
            var contenu = XLSX.utils.sheet_to_html(feuille);

            var divAffichage = document.getElementById('notes');

            divAffichage.innerHTML = contenu;

            console.log('ok')
        };

        reader.readAsBinaryString(object.files[idbouton]);

    }


    block1.style.display = "none"
}




























function afficher_contenu3() {
    console.log('ok')
    block1.style.display = "none"
    block2.style.display = "block"

    // notes.style.display = "block";
    object= document.getElementById('input1');
    console.log(object);

    total=object.files.length
    console.log(total)
    
    for (let i = 1; i <=total; i++) {
        ob = document.getElementById(i);
        console.log(ob)
        ob.style.display = "block";
    }
}



function formulaire(){
    // var form = document.createElement("form");
    var form =document.getElementById('form2');
    object= document.getElementById('input1');
    console.log(object.files)
    var nom=object.files[0].name;
    var filename = nom.split(".")
    var donnees = testerMot(filename[0]);
    console.log("Nom du fichier : " + filename[0]);
    if (donnees == false){
        //gestion de l'erreur
        console.log("erreur")
    } else{
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
            var input3 = document.getElementById('input1');
            // input3.setAttribute("type", "file");
            input3.setAttribute("name", "file");
            input3.setAttribute("value", file);

            console.log(input3.files[0])
            form.appendChild(input3);

    }

}

function add (){
    input11.style.display = "block"
    notes.style.display = "none"
    div22.style.paddingLeft = "0%";
}

function mafonct(mot){
    // var mot = "ceci_est_un_exemple_de_mot";

var motsSeparés = mot.split("_"); // Divise le mot en un tableau en utilisant "_" comme séparateur
var variables = []; // Variable pour stocker les éléments précédant chaque "_"

for (var i = 0; i < motsSeparés.length; i++) {
  // Vérifie si l'élément courant n'est pas le premier élément du tableau
  
    variables.push(motsSeparés[i]); // Ajoute l'élément précédant "_" dans le tableau variables
  
}

return variables; // Affiche les éléments précédant chaque "_" dans la console
}


function testerMot(mot){
    var regex = /^[A-Za-z]{3}\d{3}_[A-Za-z]{2}_[A-Za-z]\d_\d{4}_\d{2}$/; // Expression régulière pour vérifier le format
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








// function menu(id) {
//     let i = 1;
//     let ob;
//     for (i = id; i <= 14; i++) {

//         ob = document.getElementById("a" + i);
//         ob.style.display = "none";
//     }
//     for (i = 0; i <= 14; i++) {
//         let objet = document.getElementById(i);
//         let nom_matiere = "matiere" + i;
//         objet.innerHTML = nom_matiere;
//     }

// }



// function afficher_page(id) {

//     let objet = document.getElementById("matiere");
//     objet.innerHTML = "matiere" + id;
//     textacceuil.style.display = "none";
// }



$(document).ready(function() {
    // $.jqx.themes = "custom-bosley";
      // Chargement des données de votre table SQL
      var data = [
        { id: 1, nom: "John Doe" },
        { id: 2, nom: "Jane Smith" },
        // Ajoutez les autres enregistrements ici...
      ];
      var source = {
        localdata: data,
  
        datatype: "array",
        // datatype: "json",
        datafields: [
          { name: "id", type: "number" },
          { name: "nom", type: "string" }
        ],
    };
    var dataAdapter = new $.jqx.dataAdapter(source);
  
  
      var  columns= [
        { text: "ID", datafield: "id", width: "30%" , color: "#000001"},
        { text: "Nom", datafield: "nom", width: "35%" , color: "#000001"},
        {
          text: "Action",
          width: "35%",
          color: "#000000",
          cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties, rowdata) {
            return '<button onclick="actionClick(' + row + ')">Action</button>';
          }
  
        }
      ]
    
      // Configuration du tableau jqxGrid
      $("#jqxgrid").jqxDataTable({
        source: dataAdapter,
        columns :columns,
        pageable:true,
       
        theme: "ui-sunny",
        width: "100%"
       
      });
    
  
  
      // $("#myTable").addClass("custom-bosley");
  
    });
  
    function actionClick(row) {
      var rowData = $('#jqxgrid').jqxGrid('getrowdata', row);
      // Effectuez les opérations souhaitées sur les données de la ligne cliquée
      console.log("ID de la ligne cliquée : " + rowData.id);
      console.log("Nom de la ligne cliquée : " + rowData.nom);
    }
  
