/**
 * Confirmação de Exclusão de um contato
 * @author Ramon Marino
 */

 function confirmar(idcon){
	 let resposta = confirm("Gostaria de excluir esse contato? ")
	 if(resposta === true){
		 //alert(idcon)
		window.location.href= "delete?idcon=" + idcon 
	 }
 }
 