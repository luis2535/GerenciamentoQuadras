function isBolsista(cpf) {
    return new Promise((resolve, reject) => {
      $.ajax({
        url: `http://localhost:8080/api/isbolsista/${cpf}`,
        type: "GET",
        dataType: "json",
        success: function(response) {
          console.log("Busca bolsista realizada");
          resolve(response);
        },
        error: function(xhr, status, error) {
          console.error("Erro ao selecionar bolsistas:", xhr.status);
          reject(error);
        }
      });
    });
  }
  function isAdmin(cpf) {
    return new Promise((resolve, reject) => {
      $.ajax({
        url: `http://localhost:8080/api/isadmin/${cpf}`,
        type: "GET",
        dataType: "json",
        success: function(response) {
          console.log("Busca admin realizada");
          resolve(response);
        },
        error: function(xhr, status, error) {
          console.error("Erro ao selecionar Admins:", xhr.status);
          reject(error);
        }
      });
    });
  }

document.getElementById('login-button').addEventListener('click', function(event) {
    event.preventDefault();
  
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    // Limpar o campo de senha
    document.getElementById('password').value = '';
  
    
    const url = `http://localhost:8080/api/usuario/login?cpf=${username}&senha=${password}`;
  
    fetch(url)
      .then(response => response.json())
      .then(data => {
      if (data.cpf !== null) {
        console.log('Login bem-sucedido:', data);
        sessionStorage.setItem('usuario', JSON.stringify(data));
  
        Promise.all([isBolsista(data.cpf), isAdmin(data.cpf)])
          .then(results => {
            const isBolsistaResult = results[0];
            const isAdminResult = results[1];
  
            console.log('isBolsista:', isBolsistaResult);
            console.log('isAdmin:', isAdminResult);
  
            sessionStorage.setItem('isBolsista', isBolsistaResult);
            sessionStorage.setItem('isAdmin', isAdminResult);
  
            window.location.href = "../html/menu.html";
          })
          .catch(error => {
            console.error('Erro durante a requisição:', error);
          });
      } else {
        const errorElement = document.getElementById('error-message');
        errorElement.textContent = 'Usuário ou senha inválido, digite novamente.';
  
        console.log('Credenciais inválidas');
      }
    })
    .catch(error => {
      console.error('Erro durante a requisição:', error);
    });
  })