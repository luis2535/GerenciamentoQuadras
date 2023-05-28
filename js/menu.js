
function checkLogin() {
const usuario = JSON.parse(sessionStorage.getItem('usuario'));
const isBolsista = sessionStorage.getItem('isBolsista') === 'true';
const isAdmin = sessionStorage.getItem('isAdmin') === 'true';

if (usuario !== null) {
  // O usuário está logado, você pode usar as informações do usuário como necessário
  console.log('Usuário logado:', usuario);
  document.getElementById('nome-usuario').textContent = usuario.pnome + " "+ usuario.unome;
  if (isBolsista){
    document.getElementById('tipo').textContent = 'Bolsista';
  }else{
    document.getElementById('tipo').textContent = usuario.funcao;
  }
  if (isAdmin){
    document.getElementById('tipo').textContent = 'Admin';
  }else{
    document.getElementById('tipo').textContent = usuario.funcao;
  }
  console.log(isAdmin)
  console.log(isBolsista)

  
} else {
  // O usuário não está logado, redireciona para a página de login
  window.location.href = 'login.html';
}
}

// Chama a função checkLogin ao carregar a página
window.addEventListener('load', checkLogin);

function encerrarSessao(){
    sessionStorage.removeItem('usuario');
    sessionStorage.removeItem('isBolsista');
    sessionStorage.removeItem('isAdmin');
    window.location.href = 'login.html';
  }