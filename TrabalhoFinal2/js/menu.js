
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
    $('.admin-container').show();
    $('#gerenciar-bolsista').show();
    $('#gerenciar-agendamentos').show();
  } else {
    $('#gerenciar-blocos').hide();
    $('#gerenciar-quadras').hide();
    $('#gerenciar-equipamentos').hide();
    $('#gerenciar-usuarios').hide();
    $('.admin-container').hide();
    $('#gerenciar-bolsista').hide();
    $('#gerenciar-agendamentos').hide();
  }
  if (isAdmin || isBolsista){
    $('.bolsista-funcao').show();
  }else{
    $('.bolsista-funcao').hide();
  }

  
} else {
  // O usuário não está logado, redireciona para a página de login
  window.location.href = 'login.html';
}
}
const showApresentacao = document.getElementById('menuPrincipal');
showApresentacao.addEventListener('click', function(){
  $('.bolsista-container').hide();
  const container = document.querySelector('.content');
  container.style.display = 'none';
  const calendarContainer = document.querySelector('.calendar');
  calendarContainer.style.display = 'none';
  const blocoContainer = document.querySelector('.bloco-container');
  blocoContainer.style.display = 'none';
  const quadraContainer = document.querySelector('.quadra-container');
  quadraContainer.style.display = 'none';
  const userContainer = document.querySelector('.user-container');
  userContainer.style.display = 'none';
  const equipamentoContainer = document.querySelector('.equipamentos-container');
  equipamentoContainer.style.display = 'none';
  const meuAgendamentoContainer = document.querySelector('.meus-agendamentos-container');
  meuAgendamentoContainer.style.display = 'none';
  const todoAgendamentoContainer = document.querySelector('.gerenciar-agendamentos-container');
  todoAgendamentoContainer.style.display = 'none';
  const apresentacaoContainer = document.querySelector('.apresentacao');
  apresentacaoContainer.style.display = 'block';
});

const showCalendarButton = document.getElementById('show-calendar-button');
showCalendarButton.addEventListener('click', function() {
  $('.bolsista-container').hide();
  const container = document.querySelector('.content');
  container.style.display = 'flex';
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
  const meuAgendamentoContainer = document.querySelector('.meus-agendamentos-container');
  meuAgendamentoContainer.style.display = 'none';
  const todoAgendamentoContainer = document.querySelector('.gerenciar-agendamentos-container');
  todoAgendamentoContainer.style.display = 'none';
  const apresentacaoContainer = document.querySelector('.apresentacao');
  apresentacaoContainer.style.display = 'none';
});

const blocoButton = document.getElementById('gerenciar-blocos');
blocoButton.addEventListener('click', function() {
  $('.bolsista-container').hide();
  const container = document.querySelector('.content');
  container.style.display = 'none';
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
  const meuAgendamentoContainer = document.querySelector('.meus-agendamentos-container');
  meuAgendamentoContainer.style.display = 'none';
  const todoAgendamentoContainer = document.querySelector('.gerenciar-agendamentos-container');
  todoAgendamentoContainer.style.display = 'none';
  const apresentacaoContainer = document.querySelector('.apresentacao');
  apresentacaoContainer.style.display = 'none';
});

const gerenciarButton = document.getElementById('gerenciar-bolsista');
gerenciarButton.addEventListener('click', function() {
  const container = document.querySelector('.content');
  container.style.display = 'none';
  const bolsistaContainer = document.querySelector('.bolsista-container');
  bolsistaContainer.style.display = 'block';
  const calendarContainer = document.querySelector('.calendar');
  calendarContainer.style.display = 'none';
  const gerenciarBloco = document.querySelector('.bloco-container');
  gerenciarBloco.style.display = 'none';
  const quadraContainer = document.querySelector('.quadra-container');
  quadraContainer.style.display = 'none';
  const userContainer = document.querySelector('.user-container');
  userContainer.style.display = 'none';
  const equipamentoContainer = document.querySelector('.equipamentos-container');
  equipamentoContainer.style.display = 'none';
  const meuAgendamentoContainer = document.querySelector('.meus-agendamentos-container');
  meuAgendamentoContainer.style.display = 'none';
  const todoAgendamentoContainer = document.querySelector('.gerenciar-agendamentos-container');
  todoAgendamentoContainer.style.display = 'none';
  const apresentacaoContainer = document.querySelector('.apresentacao');
  apresentacaoContainer.style.display = 'none';
});

const gerenciarQuadrasButton = document.getElementById('gerenciar-quadras');
gerenciarQuadrasButton.addEventListener('click', function() {
  const container = document.querySelector('.content');
  container.style.display = 'none';
  $('.bolsista-container').hide();
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
  const meuAgendamentoContainer = document.querySelector('.meus-agendamentos-container');
  meuAgendamentoContainer.style.display = 'none';
  const todoAgendamentoContainer = document.querySelector('.gerenciar-agendamentos-container');
  todoAgendamentoContainer.style.display = 'none';
  const apresentacaoContainer = document.querySelector('.apresentacao');
  apresentacaoContainer.style.display = 'none';

  carregarBlocos();
});

const gerenciarUsuariosButton = document.getElementById('gerenciar-usuarios');
gerenciarUsuariosButton.addEventListener('click', function() {
  const container = document.querySelector('.content');
  container.style.display = 'none';
  $('.bolsista-container').hide();
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
  const meuAgendamentoContainer = document.querySelector('.meus-agendamentos-container');
  meuAgendamentoContainer.style.display = 'none';
  const todoAgendamentoContainer = document.querySelector('.gerenciar-agendamentos-container');
  todoAgendamentoContainer.style.display = 'none';
  const apresentacaoContainer = document.querySelector('.apresentacao');
  apresentacaoContainer.style.display = 'none';
});

const gerenciarEquipamentosButton = document.getElementById('gerenciar-equipamentos');
gerenciarEquipamentosButton.addEventListener('click', function() {
  const container = document.querySelector('.content');
  container.style.display = 'none';
  $('.bolsista-container').hide();
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
  const meuAgendamentoContainer = document.querySelector('.meus-agendamentos-container');
  meuAgendamentoContainer.style.display = 'none';
  const todoAgendamentoContainer = document.querySelector('.gerenciar-agendamentos-container');
  todoAgendamentoContainer.style.display = 'none';
  const apresentacaoContainer = document.querySelector('.apresentacao');
  apresentacaoContainer.style.display = 'none';

});
const meusAgendamentosButton = document.getElementById('meus-agendamentos');
meusAgendamentosButton.addEventListener('click', function(){
  const container = document.querySelector('.content');
  container.style.display = 'none';
  $('.bolsista-container').hide();
  const userContainer = document.querySelector('.user-container');
  userContainer.style.display = 'none';
  const gerenciarBloco = document.querySelector('.bloco-container');
  gerenciarBloco.style.display = 'none';
  const calendarContainer = document.querySelector('.calendar');
  calendarContainer.style.display = 'none';
  const quadraContainer = document.querySelector('.quadra-container');
  quadraContainer.style.display = 'none';
  const equipamentoContainer = document.querySelector('.equipamentos-container');
  equipamentoContainer.style.display = 'none';
  const meuAgendamentoContainer = document.querySelector('.meus-agendamentos-container');
  meuAgendamentoContainer.style.display = 'block';
  const todoAgendamentoContainer = document.querySelector('.gerenciar-agendamentos-container');
  todoAgendamentoContainer.style.display = 'none';
  const apresentacaoContainer = document.querySelector('.apresentacao');
  apresentacaoContainer.style.display = 'none';

  getMeusAgendamentos();
});

const todosAgendamentosButton = document.getElementById('gerenciar-agendamentos');
todosAgendamentosButton.addEventListener('click', function(){
  const container = document.querySelector('.content');
  container.style.display = 'none';
  $('.bolsista-container').hide();
  const userContainer = document.querySelector('.user-container');
  userContainer.style.display = 'none';
  const gerenciarBloco = document.querySelector('.bloco-container');
  gerenciarBloco.style.display = 'none';
  const calendarContainer = document.querySelector('.calendar');
  calendarContainer.style.display = 'none';
  const quadraContainer = document.querySelector('.quadra-container');
  quadraContainer.style.display = 'none';
  const equipamentoContainer = document.querySelector('.equipamentos-container');
  equipamentoContainer.style.display = 'none';
  const meuAgendamentoContainer = document.querySelector('.meus-agendamentos-container');
  meuAgendamentoContainer.style.display = 'none';
  const todoAgendamentoContainer = document.querySelector('.gerenciar-agendamentos-container');
  todoAgendamentoContainer.style.display = 'block';
  const apresentacaoContainer = document.querySelector('.apresentacao');
  apresentacaoContainer.style.display = 'none';

  getTodosAgendamento();
});


// Chama a função checkLogin ao carregar a página
window.addEventListener('load', checkLogin);

function encerrarSessao(){
    sessionStorage.removeItem('usuario');
    sessionStorage.removeItem('isBolsista');
    sessionStorage.removeItem('isAdmin');
    window.location.href = 'login.html';
  }



  
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


function getQuadrasSelect() {
  $.ajax({
    url: 'http://localhost:8080/api/quadra',
    type: 'GET',
    dataType: 'json',
    success: function(response) {
      const selectQuadras = document.getElementById('select-quadras');

      response.forEach(function(quadra) {
        $(selectQuadras).append($('<option>',{
          value: quadra.id_quadra,
          text: quadra.modalidade
        }))
      });
      selectQuadras.addEventListener('change', function() {
        const quadraSelecionada = selectQuadras.value;
        getAgendamento(quadraSelecionada);
      });

      // Carregar agendamentos iniciais para a quadra selecionada (primeira opção)
      const quadraSelecionada = selectQuadras.value;
      getAgendamento(quadraSelecionada);
      
    },
    error: function(xhr, status, erro) {
      console.error('Erro ao obter quadras:', xhr.status);
    }
  });
}
function getEquipamentoSelect() {
  $.ajax({
    url: 'http://localhost:8080/api/equipamento',
    type: 'GET',
    dataType: 'json',
    success: function(response) {
      const selectEquips = document.getElementById('select-equips');

      response.forEach(function(equip) {
        $(selectEquips).append($('<option>',{
          value: equip.id_equipamento,
          text: equip.tipo
        }))
      });
      
      
    },
    error: function(xhr, status, erro) {
      console.error('Erro ao obter equipamentos:', xhr.status);
    }
  });
}
let ultimoDia = null;
let ultimoHorario = null;
function fillCalendar(agendamentos) {
  const calendarBody = document.getElementById('calendar-body');
  const today = new Date();
  console.log(agendamentos);

   // Limpar o conteúdo do calendarBody
   calendarBody.innerHTML = '';
  // Criação da linha de cabeçalho com horários
  const headerRow = document.createElement('tr');
  const hourHeader = document.createElement('th');
  hourHeader.textContent = 'Hora';
  headerRow.appendChild(hourHeader);

  // Loop para os próximos 7 dias
  for (let i = 0; i < 7; i++) {
    const day = new Date(today.getFullYear(), today.getMonth(), today.getDate() + i);
    const dayString = `${day.getDate().toString().padStart(2, '0')}/${(day.getMonth() + 1).toString().padStart(2, '0')}`;
    const dayHeader = document.createElement('th');
    dayHeader.textContent = dayString;
    headerRow.appendChild(dayHeader);
  }

  calendarBody.appendChild(headerRow);

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

      const day = new Date(today.getFullYear(), today.getMonth(), today.getDate() + i);
      const month = (day.getMonth() + 1).toString().padStart(2, '0');
      const dayOfMonth = day.getDate().toString().padStart(2, '0');
      const dayString = `${month}-${dayOfMonth}`;

      // Verificar se o horário e a data correspondem a algum agendamento
      let agendamentoMarcado = null;
      for (let j = 0; j < agendamentos.length; j++) {
        const agendamento = agendamentos[j];
        if (agendamento.horario_inicio === hour + ':00' && agendamento.data.trim() === '2023-' + dayString && agendamento.status === 'ATIVO') {
          agendamentoMarcado = agendamento;
          break;
        }
      }

      // Adicionar a palavra "Reservado" na célula correspondente
      if (agendamentoMarcado) {
        const reservadoDiv = document.createElement('div');
        reservadoDiv.textContent = 'Reservado';
        cell.appendChild(reservadoDiv);
        cell.classList.add('horario-marcado')
      }

      // Adicionar evento de clique à célula vazia
      cell.addEventListener('click', function() {
        // Remover a classe 'celula-selecionada' de todas as células antes de adicionar à célula atual
        const cells = calendarBody.querySelectorAll('td');
        cells.forEach(function(cell) {
          cell.classList.remove('celula-selecionada');
        });
    
        // Adicionar a classe 'celula-selecionada' à célula atual
        cell.classList.add('celula-selecionada');
    
        // Ação a ser executada ao selecionar a célula
        // selecionarCelula(dayString, hour);
        ultimoDia = dayString;
        ultimoHorario = hour;
      });
    }
    

    calendarBody.appendChild(row);
  }
  
}
// function selecionarCelula(dia, horario) {
//   console.log('Selecionado: Dia', dia, 'Horário', horario + ':00 - ' + (horario + 1) + ':00');
//   // Realize outras ações necessárias com o dia e horário selecionados
//   fazerAgendamento(dia, horario);
// }






function getAgendamento(id_quadra){
  $.ajax({
    url: 'http://localhost:8080/api/agendamento',
    type: 'GET',
    dataType: 'json',
    success: function(response){
      const agendamentosQuadra = [];
      // Filtrar os agendamentos da quadra específica
      response.forEach(function(agendamento) {
        if (parseInt(agendamento.quadra.id_quadra) === parseInt(id_quadra)) {
          agendamentosQuadra.push(agendamento);
        }
      });

      console.log(agendamentosQuadra);
      fillCalendar(agendamentosQuadra);
    },
    error: function(xhr, status, erro) {
      console.error('Erro ao obter agendamento:', xhr.status);
    }
  });
}
function fazerAgendamento() {
  const marcarAgendamentoButton = document.getElementById('marcar-agendamento-button');
  marcarAgendamentoButton.addEventListener('click', function() {
    const quadraSelecionada = document.getElementById('select-quadras').value;
    const equipamentoSelecionado = document.getElementById('select-equips').value;
    const quantidade = document.getElementById('qtd').value;

    const horario_inicio = ultimoHorario + ':00';
    const horario_fim = (ultimoHorario + 1) + ':00';
    const data = '2023-' + ultimoDia;
    const usuario = JSON.parse(sessionStorage.getItem('usuario'));
    var quadra = {
      id_quadra: quadraSelecionada
    };
    var agendamento = {
      id_agendamento: 1,
      horario_inicio: horario_inicio,
      horario_fim: horario_fim,
      data: data,
      status: 'ATIVO',
      usuario: usuario,
      quadra: quadra
    };

    console.log(agendamento);
  $.ajax({
    url: "http://localhost:8080/api/agendamento/" + equipamentoSelecionado + "/" + quantidade,
    type: "POST",
    dataType:"json",
    contentType: "application/json",
    data: JSON.stringify(agendamento),
    success: function(response){
      console.log("Quadra adicionada com sucesso");

    },
    error: function(xhr, status, erro){
      console.error("Erro ao adicionar quadra:", xhr.status);
  }
  })
  });
}
// Chamar a função para preencher o calendário
function agendarParaOutroCpf(){
  {
    const outroAgendamentoButton = document.getElementById('marcar-paraOutro-button');
    outroAgendamentoButton.addEventListener('click', function() {
      const quadraSelecionada = document.getElementById('select-quadras').value;
      const equipamentoSelecionado = document.getElementById('select-equips').value;
      const quantidade = document.getElementById('qtd').value;
  
      const horario_inicio = ultimoHorario + ':00';
      const horario_fim = (ultimoHorario + 1) + ':00';
      const data = '2023-' + ultimoDia;
      const cpf = document.getElementById('alter-cpf').value;
      var usuario ={
        cpf: cpf
      };
      var quadra = {
        id_quadra: quadraSelecionada
      };
      var agendamento = {
        id_agendamento: 1,
        horario_inicio: horario_inicio,
        horario_fim: horario_fim,
        data: data,
        status: 'ATIVO',
        usuario: usuario,
        quadra: quadra
      };
  
      console.log(agendamento);
    $.ajax({
      url: "http://localhost:8080/api/agendamento/" + equipamentoSelecionado + "/" + quantidade,
      type: "POST",
      dataType:"json",
      contentType: "application/json",
      data: JSON.stringify(agendamento),
      success: function(response){
        console.log("Quadra adicionada com sucesso");
  
      },
      error: function(xhr, status, erro){
        console.error("Erro ao adicionar quadra:", xhr.status);
    }
    })
    });
  }

}

function agendarEvento() {
  const marcarEventoButton = document.getElementById('marcar-evento-button');
  marcarEventoButton.addEventListener('click', function() {
    const nomeEvento = document.getElementById('evento-nome').value;

    const horario_inicio = ultimoHorario + ':00';
    const horario_fim = (ultimoHorario + 1) + ':00';
    const data = '2023-' + ultimoDia;
    const usuario = JSON.parse(sessionStorage.getItem('usuario'));
    
    var admin = {
      cpf: usuario.cpf,
      email: usuario.email,
      funcao: usuario.funcao,
      pnome: usuario.pnome,
      senha: usuario.senha,
      status: usuario.status,
      unome: usuario.unome,
      id_admin: 1
    }
    var quadra = {
      id_quadra: 1 
    };
    var evento = {
      id_evento: 1,
      data: data,
      horario_inicio: horario_inicio,
      horario_fim: horario_fim,
      status: 'ATIVO',
      nome: nomeEvento,
      usuario: admin,
      quadra: quadra
    };
    console.log(evento);

  $.ajax({
    url: "http://localhost:8080/api/evento/" + usuario.cpf,
    type: "POST",
    dataType:"json",
    contentType: "application/json",
    data: JSON.stringify(evento),
    success: function(response){
      console.log("Evento adicionada com sucesso");

    },
    error: function(xhr, status, erro){
      console.error("Erro ao adicionar evento:", xhr.status);
  }
  })
  });
}
getQuadrasSelect();
getEquipamentoSelect();
fazerAgendamento();
agendarParaOutroCpf();
agendarEvento();



function getMeusAgendamentos() {
  const usuario = JSON.parse(sessionStorage.getItem('usuario'));
  $.ajax({
    url: 'http://localhost:8080/api/agendamento',
    type: 'GET',
    dataType: 'json',
    success: function(response) {
      const agendamentos = []; // Move a declaração da variável para fora do loop

      response.forEach(function(agendamento) {
        console.log(agendamento.usuario.cpf);
        console.log(usuario.cpf);
        if (agendamento.usuario.cpf === usuario.cpf) {
          agendamentos.push(agendamento);
        }
      });
      console.log(agendamentos);

      exibirAgendamentos(agendamentos);
    },
    error: function(xhr, status, erro) {
      console.error('Erro ao obter agendamentos:', xhr.status);
    }
  });
}
function exibirAgendamentos(agendamentos) {
  const agendamentosContainer = $('.agendamentos-container');

  agendamentosContainer.empty();
  console.log(agendamentosContainer);
  
  agendamentos.forEach(function(agendamento) {
    const agendamentoDiv = $('<div>').addClass('agendamento');
    const dataSpan = $('<span>').text('Data: ' + agendamento.data).append($('<br>'));
    const inicioSpan = $('<span>').text('Horario Inicio: ' + agendamento.horario_inicio).append($('<br>'));
    const fimSpan = $('<span>').text('Horario Fim: ' + agendamento.horario_fim).append($('<br>'));
    const quadraSpan = $('<span>').text('Quadra: ' + agendamento.quadra.modalidade).append($('<br>'));
    const statusSpan = $('<span>').text('Status: ' + agendamento.status).append($('<br>'));
    if(agendamento.status === 'ATIVO'){
      const cancelaButton = $('<button>').text('Cancela').addClass('cancelar-agendamento');
      cancelaButton.data('agendamento', agendamento);
      agendamentoDiv.append(dataSpan, inicioSpan, fimSpan, quadraSpan, statusSpan, cancelaButton);
      agendamentosContainer.append(agendamentoDiv);
      console.log(agendamentosContainer);
    }else{
      agendamentoDiv.append(dataSpan, inicioSpan, fimSpan, quadraSpan, statusSpan);
      agendamentosContainer.append(agendamentoDiv);
      console.log(agendamentosContainer);
    }
  });
}
function cancelarAgendamento(){
  $(document).on('click', '.cancelar-agendamento', function() {
    const agendamento = $(this).data('agendamento');
    const url = 'http://localhost:8080/api/agendamento';

    const agenda = {
      id_agendamento: agendamento.id_agendamento,
      horario_inicio: agendamento.horario_inicio,
      horario_fim: agendamento.horario_fim,
      data: agendamento.data,
      status: 'CANCELADO',
      usuario: agendamento.usuario,
      quadra: agendamento.quadra

    };
    $.ajax({
      url: url,
      type: 'PUT',
      dataType: 'json',
      contentType: 'application/json',
      data: JSON.stringify(agenda),
      success: function(response) {
        console.log('Agendamento cancelado com sucesso');
      },
      error: function(xhr, status, erro) {
        console.error('Erro ao excluir agendamento:', xhr.status);
        getMeusAgendamentos(); // Atualiza a lista de blocos exibidos
      }
    });
  });
}


getMeusAgendamentos();
cancelarAgendamento();

function obterUsuarios() {
  $.ajax({
    url: "http://localhost:8080/api/usuario",
    type: "GET",
    dataType: "json",
    success: function(response) {
      console.log("Usuários obtidos com sucesso:", response);

      // Chame a função para exibir os usuários no console ou em outro lugar desejado
      exibirUsuarios(response);
    },
    error: function(xhr, status, error) {
      console.error("Erro ao obter usuários:", xhr.status);
    }
  });
}

function exibirUsuarios(usuarios) {
  var userList = $('#user-list');
  userList.empty();

  for (var i = 0; i < usuarios.length; i++) {
    var usuario = usuarios[i];

    // Verifica se a função do usuário é diferente de "admin"
    if (usuario.funcao !== 'admin') {
      var row = $('<tr>');
      var checkboxCell = $('<td>');
      var cpfCell = $('<td>').text(usuario.cpf);
      
      var funcaoCell = $('<td>').text(usuario.funcao);
      var pnomeCell = $('<td>').text(usuario.pnome);
      


      var checkbox = $('<input>')
        .attr('type', 'checkbox')
        .attr('name', 'bolsista-checkbox')
        .val(usuario.cpf);

      // Define o estado da checkbox com base na função do usuário
      if (usuario.funcao === 'bolsista') {
        checkbox.prop('checked', true);
      }

      checkboxCell.append(checkbox);
      row.append(checkboxCell, cpfCell, pnomeCell, funcaoCell );
      userList.append(row);
    }
  }

  // Adicionar evento de clique às checkboxes
  $('input[name="bolsista-checkbox"]').click(function() {
    var isChecked = $(this).prop('checked');
    var funcaoCell = $(this).closest('tr').find('td:eq(3)');

    if (isChecked) {
      funcaoCell.text('bolsista');
    } else {
      funcaoCell.text('aluno');
    }
  });
}


$(document).ready(function() {
  $('#gerenciar-bolsista').click(function() {
    obterUsuarios();
    $('.bolsista-container').show();
  });

  $('#submit-button').click(function() {
    var checkboxes = $('input[name="bolsista-checkbox"]');
    var usuarios = [];

    checkboxes.each(function() {
      var cpf = $(this).val();
      var funcao = $(this).prop('checked') ? 'bolsista' : 'aluno';
      var email = $(this).closest('tr').find('td:eq(3)').text();
      var nome = $(this).closest('tr').find('td:eq(2)').text();
      var senha = $(this).closest('tr').find('td:eq(4)').text();
      var status = $(this).closest('tr').find('td:eq(5)').text();
      var sobrenome = $(this).closest('tr').find('td:eq(6)').text();

      console.log($(this).closest('tr'))

      var usuario = {
        cpf: cpf,
        email: email,
        funcao: funcao,
        pnome: nome,
        senha: senha,
        status: status,
        unome: sobrenome
      };

 
      usuarios.push(usuario);
    });

    // Enviar as requisições individualmente em sequência
    enviarRequisicao(usuarios, 0);
  });
});

function enviarRequisicao(usuarios, index) {
  if (index >= usuarios.length) {
    console.log("Todas as requisições foram enviadas com sucesso!");
    return;
  }

  var usuario = usuarios[index];
  console.log(usuarios)

  $.ajax({
    url: "http://localhost:8080/api/usuario/update",
    type: "POST",
    dataType: "json",
    contentType: "application/json",
    data: JSON.stringify(usuarios),
    success: function(response) {
      console.log("Função atualizada com sucesso para o usuário:", usuario.cpf);

      // Chamar a próxima requisição
      enviarRequisicao(usuarios, index + 1);
    },
    error: function(xhr, status, error) {
      console.error("Erro ao atualizar função para o usuário:", usuario.cpf, xhr.status);
    }
  });
}

function getTodosAgendamento(){
  $.ajax({
    url: 'http://localhost:8080/api/agendamento',
    type: 'GET',
    dataType: 'json',
    success: function(response) {
      exibirTodosAgendamentos(response);
    },
    error: function(xhr, status, erro) {
      console.error('Erro ao obter agendamentos:', xhr.status);
    }
  });
}
function exibirTodosAgendamentos(agendamentos) {
  const todosAgendamentosContainer = $('.todo-agendamentos-container');

  todosAgendamentosContainer.empty();
  console.log(todosAgendamentosContainer);
  
  agendamentos.forEach(function(agendamento) {
    const agendamentosDiv = $('<div>').addClass('agendamentos');
    const datasSpan = $('<span>').text('Data: ' + agendamento.data).append($('<br>'));
    const iniciosSpan = $('<span>').text('Horario Inicio: ' + agendamento.horario_inicio).append($('<br>'));
    const fimsSpan = $('<span>').text('Horario Fim: ' + agendamento.horario_fim).append($('<br>'));
    const quadrasSpan = $('<span>').text('Quadra: ' + agendamento.quadra.modalidade).append($('<br>'));
    const statussSpan = $('<span>').text('Status: ' + agendamento.status).append($('<br>'));

    const cancelarButton = $('<button>').text('Cancela').addClass('cancelar-agendamentos');
    cancelarButton.data('agendamento', agendamento);

    const compareceuButton = $('<button>').text('Compareceu').addClass('compareceu-agendamento');
    compareceuButton.data('agendamento', agendamento);

    const naoCompareceuButton = $('<button>').text('Não Compareceu').addClass('nao-compareceu-agendamento');
    naoCompareceuButton.data('agendamento', agendamento);

    if(agendamento.status === 'ATIVO'){
      agendamentosDiv.append(datasSpan, iniciosSpan, fimsSpan, quadrasSpan, statussSpan, cancelarButton, compareceuButton,naoCompareceuButton);
      console.log(agendamentosDiv);
      todosAgendamentosContainer.append(agendamentosDiv);
    }else{
      agendamentosDiv.append(datasSpan, iniciosSpan, fimsSpan, quadrasSpan, statussSpan);
      console.log(agendamentosDiv);
      todosAgendamentosContainer.append(agendamentosDiv);
    }  
  });
}
// Função para modificar o status com base no botão clicado
function modificarStatus() {
  var status = 'ATIVO'
  // Código para modificar o status de acordo com o botão clicado
  $('.todo-agendamentos-container').on('click', 'button', function() {
    // Verifique o texto do botão clicado
    var buttonText = $(this).text();
  
    // Modifique o status com base no botão clicado
    switch (buttonText) {
      case 'Cancela':
        status = 'CANCELADO';
        break;
      case 'Compareceu':
        status = 'COMPARECEU';
        break;
      case 'Não Compareceu':
        status = 'NAO_COMPARECEU';
        break;
      default:
        break;
    }
    const agendamento = $(this).data('agendamento');
    const url = 'http://localhost:8080/api/agendamento' ;

    const agenda = {
      id_agendamento: agendamento.id_agendamento,
      horario_inicio: agendamento.horario_inicio,
      horario_fim: agendamento.horario_fim,
      data: agendamento.data,
      status: status,
      usuario: agendamento.usuario,
      quadra: agendamento.quadra

    };
    $.ajax({
      url: url,
      type: 'PUT',
      dataType: 'json',
      contentType: 'application/json',
      data: JSON.stringify(agenda),
      success: function(response) {
        console.log('Agendamento alterado com sucesso');
      },
      error: function(xhr, status, erro) {
        console.error('Erro ao excluir agendamento:', xhr.status);
        getTodosAgendamento(); // Atualiza a lista de blocos exibidos
      }
    });

  });
}

// Adicione o manipulador de eventos de clique aos botões


getTodosAgendamento();
modificarStatus();
