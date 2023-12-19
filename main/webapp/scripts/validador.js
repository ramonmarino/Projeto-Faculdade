/** lógica de validação de dados

 */

 function validar(){
	 let nome = frmContato.nome.value
	 let fone = frmContato.fone.value
	 if(nome === ""){
		 alert("Por favor preencha o campo nome! ")
		 frmContato.nome.focus()
		 return false
	 } else if(fone === ""){
		 alert("Por favor preencha o campo fone!")
		 frmContato.nome.focus()
		 return false
	 }else{
		 document.forms["frmContato"].submit()
		 
	 }
 }