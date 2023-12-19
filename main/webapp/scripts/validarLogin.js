/**
 * validação de acesso da agenda 
 * @author Ramon Marino 
 * 
 */

 function validarLogin() {
            // Obtendo os valores dos campos de login e senha
            var usuario = document.getElementById('usuario').value;
            var senha = document.getElementById('senha').value;
            
             
            if (usuario === '' || senha === '') {
                alert('Por favor, preencha todos os campos.');
                return false;
            }

            // lógica de validação de login e senha aqui
            
            if (usuario === 'ramon10' && senha === '12345') {
                alert('Login bem-sucedido!');
            } else {
                alert('Credenciais inválidas. Tente novamente.');
            }

            // Impedir o envio do formulário (o link não será seguido)
            return false;
        }