// script.js

// Função executada quando o formulário é enviado
function cadastrarUsuario(event) {
    event.preventDefault(); // Impede o envio padrão do formulário
  
    // Obtém os valores dos campos do formulário
    const cpf = document.getElementById('cpf').value;
    const nome = document.getElementById('nome').value;
    const sobrenome = document.getElementById('sobrenome').value;
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;
    const status = document.getElementById('status').value;
    const funcao = document.getElementById('funcao').value;
  
    // Realiza o processamento do cadastro (pode ser substituído pela lógica de backend)
    // Aqui, apenas exibimos os valores no console
    console.log('CPF:', cpf);
    console.log('Nome:', nome);
    console.log('Sobrenome:', sobrenome);
    console.log('Email:', email);
    console.log('Senha:', senha);
    console.log('Status:', status);
    console.log('Função:', funcao);
  
    // Limpa os campos do formulário
    document.getElementById('userForm').reset();
  }
  
  // Adiciona um listener para o evento de envio do formulário
  document.getElementById('userForm').addEventListener('submit', cadastrarUsuario);
  