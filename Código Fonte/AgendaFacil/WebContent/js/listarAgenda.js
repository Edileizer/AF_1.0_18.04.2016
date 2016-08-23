//desmarca todos os radios deixando apenas o selecionado marcado
function desmarcar(radio){
	var formulario = document.getElementById('formulario');
	var campo;
	for(var item=0; item<formulario.length; item++){
    	campo = formulario[item];
        if(campo.type=='radio' && campo.name!=radio.name){    
        	campo.checked = false;
    	}
    }
}

//armazena o identificador do item escolhido
function selecionarItem(campoHidden,radioSelecionado){
	document.getElementById(campoHidden).value = radioSelecionado.value;
	desmarcar(radioSelecionado);
}

//verifica se algum item esta selecionado
function validarSelecionado(){
	var formulario = document.getElementById('formulario');
	var campo;
	for(var item=0; item<formulario.length; item++){
    	campo = formulario[item];
        if(campo.type=='radio' && campo.checked){    
        	return true;
    	}
    }
    alert('� necess�rio selecionar um item da lista.');
    return false;
}

//valida o formulario
function validarFormulario(){
	alert('a');
	var formulario = document.getElementById('formulario');
	if(formulario.nome.value.lenght()<0){
		alert('O campo nome deve ser preenchido').
		formulario.nome.focus();
		return false;
	}
	
	return true;
}
