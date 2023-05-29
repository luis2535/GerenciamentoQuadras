
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
  if (isAdmin) {
    $('#gerenciar-blocos').show();
    $('#gerenciar-quadras').show();
    $('#gerenciar-equipamentos').show();
    $('#gerenciar-usuarios').show();
  } else {
    $('#gerenciar-blocos').hide();
    $('#gerenciar-quadras').hide();
    $('#gerenciar-equipamentos').hide();
    $('#gerenciar-usuarios').hide();
  }

  
} else {
  // O usuário não está logado, redireciona para a página de login
  window.location.href = 'login.html';
}
}
const showCalendarButton = document.getElementById('show-calendar-button');
showCalendarButton.addEventListener('click', function() {
  const calendarContainer = document.querySelector('.calendar');
  calendarContainer.style.display = 'flex';
  const blocoContainer = document.querySelector('.bloco-container');
  blocoContainer.style.display = 'none';
  const quadraContainer = document.querySelector('.quadra-container');
  quadraContainer.style.display = 'none';
  const userContainer = document.querySelector('.user-container');
  userContainer.style.display = 'none';
  const equipamentoContainer = document.querySelector('.equipamentos-container');
  equipamentoContainer.style.display = 'none';
});

const blocoButton = document.getElementById('gerenciar-blocos');
blocoButton.addEventListener('click', function() {
  const calendarContainer = document.querySelector('.calendar');
  calendarContainer.style.display = 'none';
  const gerenciarBloco = document.querySelector('.bloco-container');
  gerenciarBloco.style.display = 'block';
  const quadraContainer = document.querySelector('.quadra-container');
  quadraContainer.style.display = 'none';
  const userContainer = document.querySelector('.user-container');
  userContainer.style.display = 'none';
  const equipamentoContainer = document.querySelector('.equipamentos-container');
  equipamentoContainer.style.display = 'none';
});

const gerenciarQuadrasButton = document.getElementById('gerenciar-quadras');
gerenciarQuadrasButton.addEventListener('click', function() {
  const calendarContainer = document.querySelector('.calendar');
  calendarContainer.style.display = 'none';
  const blocoContainer = document.querySelector('.bloco-container');
  blocoContainer.style.display = 'none';
  const quadraContainer = document.querySelector('.quadra-container');
  quadraContainer.style.display = 'block';
  const userContainer = document.querySelector('.user-container');
  userContainer.style.display = 'none';
  const equipamentoContainer = document.querySelector('.equipamentos-container');
  equipamentoContainer.style.display = 'none';

  carregarBlocos();
});

const gerenciarUsuariosButton = document.getElementById('gerenciar-usuarios');
gerenciarUsuariosButton.addEventListener('click', function() {
  const userContainer = document.querySelector('.user-container');
  userContainer.style.display = 'block';
  const gerenciarBloco = document.querySelector('.bloco-container');
  gerenciarBloco.style.display = 'none';
  const calendarContainer = document.querySelector('.calendar');
  calendarContainer.style.display = 'none';
  const quadraContainer = document.querySelector('.quadra-container');
  quadraContainer.style.display = 'none';
  const equipamentoContainer = document.querySelector('.equipamentos-container');
  equipamentoContainer.style.display = 'none';
});

const gerenciarEquipamentosButton = document.getElementById('gerenciar-equipamentos');
gerenciarEquipamentosButton.addEventListener('click', function() {
  const userContainer = document.querySelector('.user-container');
  userContainer.style.display = 'none';
  const gerenciarBloco = document.querySelector('.bloco-container');
  gerenciarBloco.style.display = 'none';
  const calendarContainer = document.querySelector('.calendar');
  calendarContainer.style.display = 'none';
  const quadraContainer = document.querySelector('.quadra-container');
  quadraContainer.style.display = 'none';
  const equipamentoContainer = document.querySelector('.equipamentos-container');
  equipamentoContainer.style.display = 'block';
});


// Chama a função checkLogin ao carregar a página
window.addEventListener('load', checkLogin);

function encerrarSessao(){
    sessionStorage.removeItem('usuario');
    sessionStorage.removeItem('isBolsista');
    sessionStorage.removeItem('isAdmin');
    window.location.href = 'login.html';
  }
// Função para preencher a grade do calendário
// Função para preencher a grade do calendário
// Função para preencher a grade do calendário
function fillCalendar() {
    const calendarBody = document.getElementById('calendar-body');
  
    // Loop para as horas das 7h às 23h
    for (let hour = 7; hour <= 23; hour++) {
      const row = document.createElement('tr');
  
      // Célula com o horário
      const hourCell = document.createElement('td');
      hourCell.textContent = `${hour}:00 - ${hour + 1}:00`;
      row.appendChild(hourCell);
  
      // Loop para os próximos 7 dias
      for (let i = 0; i < 7; i++) {
        const cell = document.createElement('td');
        row.appendChild(cell);
  
        // Adicionar evento de clique à célula vazia
        cell.addEventListener('click', function() {
          // Ação a ser executada ao selecionar a célula
          console.log(`Selecionado: Dia ${i + 1}, Hora ${hour}:00 - ${hour + 1}:00`);
        });
      }
  
      calendarBody.appendChild(row);
    }
  }
  
  // Chamar a função para preencher o calendário
  fillCalendar();
  
  function adicionarBloco(){
    document.getElementById('adicionar-bloco-button').addEventListener('click', function(event){
    const nome = document.getElementById('nome-bloco').value;
    const descricao = document.getElementById('descricao-bloco').value;
    document.getElementById('nome-bloco').value = '';
    document.getElementById('descricao-bloco').value = '';
    const bloco = {
      id_bloco: 1,
      nome: nome,
      descricao: descricao
    };
    
    $.ajax({
      url: 'http://localhost:8080/api/bloco',
      type: "POST",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify(bloco),
      success: function(response){
        console.log("Bloco adicionado com sucesso");
      },
      error: function(xhr, status, erro){
        console.error("Erro ao adicionar bloco:", xhr.status);
        getBlocos();
      }
    });
    });
  }
  adicionarBloco();
  function exibirBlocos(blocos) {
    const blocosContainer = $('#blocos-container');
    blocosContainer.empty();
  
    blocos.forEach(function(bloco) {
      const blocoDiv = $('<div>').addClass('bloco');
      const nomeSpan = $('<span>').text('Nome: ' + bloco.nome).append($('<br>'));
      const descricaoSpan = $('<span>').text('Descrição: ' + bloco.descricao);
      const deleteButton = $('<button>').text('Excluir').addClass('excluir-bloco');
      deleteButton.data('id', bloco.id_bloco);
  
      blocoDiv.append(nomeSpan, descricaoSpan, deleteButton);
      blocosContainer.append(blocoDiv);
    });
  }
  function getBlocos() {
    $.ajax({
      url: 'http://localhost:8080/api/bloco',
      type: 'GET',
      dataType: 'json',
      success: function(response) {
        exibirBlocos(response);
      },
      error: function(xhr, status, erro) {
        console.error('Erro ao obter blocos:', xhr.status);
      }
    });
  }
  function excluirBloco() {
    $(document).on('click', '.excluir-bloco', function() {
      const idBloco = $(this).data('id');
      const url = 'http://localhost:8080/api/bloco';
  
      const bloco = {
        id_bloco: idBloco
      };
  
      $.ajax({
        url: url,
        type: 'DELETE',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(bloco),
        success: function(response) {
          console.log('Bloco excluído com sucesso');
        },
        error: function(xhr, status, erro) {
          console.error('Erro ao excluir bloco:', xhr.status);
          getBlocos(); // Atualiza a lista de blocos exibidos
        }
      });
    });
  }
  
  
  
  
  
  getBlocos();
  excluirBloco();
  
  // Função para adicionar uma nova quadra
  const blocosSelect = document.getElementById('select-bloco');

  function carregarBlocos() {
  
    $.ajax({
      url: 'http://localhost:8080/api/bloco',
      type: 'GET',
      dataType: 'json',
      success: function(data) {
        console.log(blocosSelect)
        console.log(data)

          $(blocosSelect).append($('<option>',{
          value: '',
          text: 'Selecione um bloco',
          disabled: true,  // Adiciona o atributo "disabled"
          selected: true   // Define a opção como selecionada por padrão
        }))
  
        // Adicionar as opções de blocos ao elemento select
        data.forEach(function(bloco) {
          $(blocosSelect).append($('<option>',{
            value: bloco.id_bloco,
            'data-info': JSON.stringify(bloco),
            text: bloco.nome
          }))
        });
        
      },
      error: function(xhr, status, error) {
        
        console.error('Erro ao carregar os blocos:', error);
      }
    });
  }

  
  
function adicionarQuadra() {
  document.getElementById('adicionar-quadra-button').addEventListener('click', function(event){
    const modalidade = document.getElementById('modalidade-quadra').value;
    const descricao = document.getElementById('descricao-quadra').value;
    var select = document.getElementById('select-bloco');
    const bloco = parseInt(select.value);

    const blocoSelecionado = {
      id_bloco: bloco,
      nome: 'nomeDoBLoco',
      descricao: 'descricao'
    };
    
    // Criação do objeto Quadra
    const quadra = {
      id_quadra: 1,
      modalidade: modalidade,
      descricao: descricao,
      bloco: blocoSelecionado
    };
    
    console.log(quadra)
  
    // Requisição AJAX para adicionar a quadra no servidor
    $.ajax({
      url: "http://localhost:8080/api/quadra/" + bloco, // URL da rota para adicionar quadra no backend
      type: "POST",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify(quadra),
      success: function(response) {
        console.log("Quadra adicionada com sucesso");
        },
        error: function(xhr, status, erro){
          console.error("Erro ao adicionar quadra:", xhr.status);
          getQuadras();
      }
    });

  });
}
function exibirQuadras(quadras){
  const quadraContainer = $('#quadras-container');
  quadraContainer.empty();

  quadras.forEach(function(quadra){
    const quadraDiv = $('<div>').addClass('quadra');
    const modalidadeSpan = $('<span>').text('Modalidade: ' + quadra.modalidade).append($('<br>'));
    const descricaoSpan = $('<span>').text('Descrição: ' + quadra.descricao).append($('<br>'));
    const blocoSpan = $('<span>').text('Bloco: ' + quadra.id_bloco.nome);
    const deletaButton = $('<button>').text('Excluir').addClass('excluir-quadra');
    deletaButton.data('id_quadra', quadra.id_quadra);

    quadraDiv.append(modalidadeSpan,descricaoSpan,blocoSpan, deletaButton);
    quadraContainer.append(quadraDiv);
  });
}
function getQuadras(){
  $.ajax({
    url: 'http://localhost:8080/api/quadra',
    type: 'GET',
    dataType: 'json',
    success: function(response) {
      console.log(response);
      exibirQuadras(response);
    },
    error: function(xhr, status, erro) {
      console.error('Erro ao obter quadras:', xhr.status);
    }
  });
}
function excluirQuadra() {
  $(document).on('click', '.excluir-quadra', function(){
    const idQuadra = $(this).data('id_quadra');
    const url = 'http://localhost:8080/api/quadra';
    const quadra = {
      id_quadra: idQuadra
    };

    $.ajax({
      url: url,
      type: 'DELETE',
      dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(quadra),
        success: function(response) {
          console.log('Quadra excluído com sucesso');
        },
        error: function(xhr, status, erro) {
          console.error('Erro ao excluir bloco:', xhr.status);
          getQuadras(); // Atualiza a lista de blocos exibidos
        }
    });
  });
}
  
adicionarQuadra();
getQuadras();
excluirQuadra();




const gerenciarUsuariosForm = document.getElementById('gerenciar-usuarios-form');
gerenciarUsuariosForm.addEventListener('submit', function(event) {
  event.preventDefault();

  const cpfInput = document.getElementById('cpf');
  const nomeInput = document.getElementById('nome');
  const sobrenomeInput = document.getElementById('sobrenome');
  const emailInput = document.getElementById('email');
  const senhaInput = document.getElementById('senha');
  const statusSelect = document.getElementById('status');
  const funcaoSelect = document.getElementById('funcao');

  // Obtenha os valores dos campos
  const cpf = cpfInput.value;
  const nome = nomeInput.value;
  const sobrenome = sobrenomeInput.value;
  const email = emailInput.value;
  const senha = senhaInput.value;
  const status = statusSelect.value;
  const funcao = funcaoSelect.value;

  // Crie um objeto de usuário com os valores dos campos
  const usuario = {
    cpf: cpf,
    pnome: nome,
    unome: sobrenome,
    email: email,
    senha: senha,
    status: status,
    funcao: funcao
  };

  // Chame a função para adicionar o usuário
  adicionarUsuario(usuario);

  // Limpe os campos do formulário
  cpfInput.value = '';
  nomeInput.value = '';
  sobrenomeInput.value = '';
  emailInput.value = '';
  senhaInput.value = '';
  statusSelect.value = 'ativo';
  funcaoSelect.value = 'aluno';
});

function adicionarUsuario(usuario) {
  $.ajax({
    url: "http://localhost:8080/api/usuario",
    type: "POST",
    dataType: "json",
    contentType: "application/json",
    data: JSON.stringify(usuario),
    success: function(response) {
      console.log("Usuário adicionado com sucesso!");
    },
    error: function(xhr, status, error) {
      console.error("Erro ao adicionar usuário:", xhr.status);
    }
  });
}

function adicionarEquipamento(){
  document.getElementById('adicionar-equip-button').addEventListener('click', function(event){
  const nome = document.getElementById('nome-equip').value;
  const descricao = document.getElementById('descricao-equip').value;
  document.getElementById('nome-equip').value = '';
  document.getElementById('descricao-equip').value = '';
  const equip = {
    id_equip: 1,
    tipo: nome,
    descricao: descricao
  };
  
  $.ajax({
    url: 'http://localhost:8080/api/equipamento',
    type: "POST",
    dataType: "json",
    contentType: "application/json",
    data: JSON.stringify(equip),
    success: function(response){
      console.log("Equipamento adicionado com sucesso");
    },
    error: function(xhr, status, erro){
      console.error("Erro ao adicionar equipamentos:", xhr.status);
      getEquipamentos();
    }
  });
  });
  
}

function getEquipamentos() {
  $.ajax({
    url: 'http://localhost:8080/api/equipamento',
    type: 'GET',
    dataType: 'json',
    success: function(response) {
      exibirEquipamentos(response);
    },
    error: function(xhr, status, erro) {
      console.error('Erro ao obter equipamentos:', xhr.status);
    }
  });
}
function exibirEquipamentos(equips) {
  const equipamentosContainer = $('#equipamento-container');
  equipamentosContainer.empty();
  

  equips.forEach(function(equip) {
    const equipDiv = $('<div>').addClass('equip');
    const tipoSpan = $('<span>').text('Nome: ' + equip.tipo).append($('<br>'));
    const descSpan = $('<span>').text('Descrição: ' + equip.descricao);
    const deletarButton = $('<button>').text('Excluir').addClass('excluir-equip');
    deletarButton.data('id', equip.id_equipamento);
    equipDiv.append(tipoSpan, descSpan, deletarButton);
    equipamentosContainer.append(equipDiv);
  });
}
function excluirEquipamento(){
  $(document).on('click', '.excluir-equip', function(){
    const id_equip = $(this).data('id');
    const url = 'http://localhost:8080/api/equipamento';
    const equipamento = {
      id_equipamento: id_equip
    };

    $.ajax({
      url: url,
      type: 'DELETE',
      dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(equipamento),
        success: function(response) {
          console.log('Equipamento excluído com sucesso');
        },
        error: function(xhr, status, erro) {
          console.error('Erro ao excluir equipamento:', xhr.status);
          getEquipamentos();
        }
    });
  });
}
adicionarEquipamento();
getEquipamentos();
excluirEquipamento();