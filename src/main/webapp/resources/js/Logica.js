/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.log("entra")

function ValidarTipoID() {
    let comboBox = document.getElementById("j_idt12:listaTipoID");
    let val = comboBox.options[comboBox.selectedIndex].value;
    console.log(val)
    if (val == 0) {
        return true;
    }
}

function ValidarID() {
    if (document.getElementById("j_idt12:Identificacion").value == "") {
        return true;
    }
}

function ValidarNombre() {
    if (document.getElementById("j_idt12:Nombre").value == "") {
        return true;
    }
}

function ValidarApellido1() {
    if (document.getElementById("j_idt12:Apellido1").value == "") {
        return true;
    }
}

function ValidarApellido2() {
    if (document.getElementById("j_idt12:Apellido2").value == "") {
        return true;
    }
}

function ValidarFechaNac() {
    if (document.getElementById("j_idt12:FechaNac_input").value == "") {
        return true;
    }
}

function ValidarEdad() {
    if (document.getElementById("j_idt12:Edad").value == "") {
        return true;
    }
}

function ValidarProvincia() {
    let comboBox = document.getElementById("j_idt12:ListaProvincia");
    let val = comboBox.options[comboBox.selectedIndex].value;

    if (val == 0) {
        return true;
    }
}

function ValidarCanton() {
    let comboBox = document.getElementById("j_idt12:ListaCanton");
    let val = comboBox.options[comboBox.selectedIndex].value;

    if (val == 0) {
        return true;
    }
}

function ValidarDistrito() {
    let comboBox = document.getElementById("j_idt12:ListaDistrito");
    let val = comboBox.options[comboBox.selectedIndex].value;

    if (val == 0) {
        return true;
    }
}

function ValidarBarrio() {
    let comboBox = document.getElementById("j_idt12:ListaBarrio");
    let val = comboBox.options[comboBox.selectedIndex].value;

    if (val == 0) {
        return true;
    }
}

function ValidarOtrasSennas() {
    if (document.getElementById("j_idt12:OtrasSennas").value == "") {
        return true;
    }
}

function ValidarTipoTel() {
    let comboBox = document.getElementById("j_idt12:TipoTelefono");
    let val = comboBox.options[comboBox.selectedIndex].value;

    if (val == 0) {
        return true;
    }
}

function ValidarTelefono() {
    if (document.getElementById("j_idt12:Telefono1").value == "") {
        return true;
    }
}

function ValidarCorreo() {
    if (document.getElementById("j_idt12:Correo").value == "") {
        return true;
    }
}

function ValidarSede() {
    let comboBox = document.getElementById("j_idt12:ListaSede");
    let val = comboBox.options[comboBox.selectedIndex].value;

    if (val == 0) {
        return true;
    }
}

function ValidarTipoPerfil() {
    event.preventDefault();
    let comboBox = document.getElementById("j_idt12:ListaPerfil");
    let val = comboBox.options[comboBox.selectedIndex].value;
    if (val == 0) {
        return true;
    }
}

function Validacion() {
    if (ValidarTipoID()) {
        alert("Debe elegir un tipo de identificación.");
        window.stop();
        return false;
    } else if (ValidarID()) {
        alert("Debe llenar el campo Identificación.");
        return false;
    } else if (ValidarNombre()) {
        alert("Debe llenar el campo Nombre.");
        return false;
    } else if (ValidarApellido1()) {
        alert("Debe llenar el campo Apellido1.");
        return false;
    } else if (ValidarApellido2()) {
        alert("Debe llenar el campo Apellido2.");
        return false;
    } else if (ValidarFechaNac()) {
        alert("Debe llenar el campo Fecha de Nacimiento.");
        return false;
    } else if (ValidarEdad()) {
        alert("Debe llenar el campo Edad.");
        return false;
    } else if (ValidarProvincia()) {
        alert("Debe elegir una Provincia.");
        return false;
    } else if (ValidarCanton()) {
        alert("Debe elegir un Canton.");
        return false;
    } else if (ValidarDistrito()) {
        alert("Debe elegir un Distrito.");
        return false;
    } else if (ValidarBarrio()) {
        alert("Debe elegir un Barrio.");
        return false;
    } else if (ValidarOtrasSennas()) {
        alert("Debe llenar el campo Otras señas.");
        return false;
    } else if (ValidarTipoTel()) {
        alert("Debe elegir un tipo de teléfono.");
        return false;
    } else if (ValidarTelefono()) {
        alert("Debe llenar el campo Teléfono.");
        return false;
    } else if (ValidarCorreo()) {
        alert("Debe llenar el campo Correo.");
        return false;
    } else if (ValidarSede()) {
        alert("Debe elegir una Sede.");
        return false;
    } else if (ValidarTipoPerfil()) {
        alert("Debe elegir un tipo de perfil.");
        return false;
    } else {
        return true;
    }
    
    function Volver(){
        window.history.back();
    }
}


