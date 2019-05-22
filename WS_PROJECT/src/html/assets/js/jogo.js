// Get Input Elements

const idInput       = document.getElementById('idInput');

const nameInput     = document.getElementById('nameInput');

const registryInput = document.getElementById('registryInput');

const idUpdateInput       = document.getElementById('idUpdateInput');

const nameUpdateInput     = document.getElementById('nameUpdateInput');

const registryUpdateInput = document.getElementById('registryUpdateInput');



// Get Content Elements

const loggerElement = document.getElementById('logger');

const JogoElement = document.getElementById('Jogo');

const JogoSelectedElement = document.getElementById('jogosSelected');



// Store fetched jogos

var jogos = [];

var jogosSelected = null;



// =========================

// UTIL Methods

// =========================



var LogEnum = {

  INFO: "table-primary",

  WARNING: "table-warning",

  DANGER: "table-danger",

};



function addLog(msg, logEnum) {

  loggerElement.innerHTML = `<tr class="${logEnum}"><th scope="row">${new Date().toUTCString()}</th><td>${msg}</td>` + loggerElement.innerHTML;

}



function reloadJogo() {

  var listaJogo = '';

  var n = jogos.length;

  for (var i = 0; i < n; i++) {

    listaJogo += makeJogoRow(Jogo[i]);

  }

  JogoElement.innerHTML = listaJogos;

}



function makeJogoRow(jogo) {

  return `<tr><th scope="row">${jogoid}</th>

          <td>${jogonome}</td><td>${jogonome}</td>

          <td class="text-center">

            <button type="button" class="btn btn-warning" onClick="prepareUpdateForm(${jogoid})" data-toggle="modal" data-target="#updateModal">editar</button>

            <button type="button" class="btn btn-danger" onClick="prepareForDelete(${jogoid})" data-toggle="modal" data-target="#deleteModal">apagar</button>

          </td>`;

}



function resetCreateForm() {

  idInput.value = '';

  nameInput.value = '';

  registryInput.value = '';

}



function resetUpdateForm() {

  idUpdateInput.value = '';

  nameUpdateInput.value = '';

  registryUpdateInput.value = '';

  JogoSelected = null;

}



function resetDeleteModal() {

  deleteJogo.innerHTML = "";

  JogoSelected = null;

}





// =========================

// CREATE Methods

// =========================

async function createJogo() {

  const URL = `/api/jogos`;

  const JogoData = {

    'nome': nameInput.value,

    'nome': registryInput.value

  };

  const postRequest = {

    method: 'POST',

    body: JSON.stringify(JogoData),

    headers: {

      'Content-type': 'application/json;charset=UTF-8'

    }

  };

  try {

    const resp = await fetch(URL, postRequest);

    if (resp.status == 200) {

      addLog(`Jogo ${JogoData.nome} criado com sucesso`, LogEnum.INFO);

      await resetCreateForm();

      await readJogo();

    } else {

      addLog(`Criar Jogo: resposta diferente de 200 - ${resp.status}`, LogEnum.WARNING);

      await readJogo();

    }

  } catch (e) {

    addLog(`Exception during "createJogo()" - ${e}`, LogEnum.DANGER);

  }

}



// =========================

// READ Methods

// =========================

async function readJogo() {

  JogoElement.innerHTML = 'carregando...';

  const url = `/api/jogos`;

  try {

    const resp = await fetch(url);

    Jogo = await resp.json();

    addLog('Jogoes carregados com sucesso',LogEnum.INFO);

    await reloadJogo();

  } catch (e) {

    addLog(`Exception during "readJogo()" - ${e}`, LogEnum.DANGER);

  }

}





// =========================

// UPDATE Methods

// =========================

function prepareUpdateForm(id) {

  for(i in Jogo) {

    if (Jogo[i].id == id) {

      JogoSelected = Jogo[i];

      idUpdateInput.value = JogoSelected.id;

      nameUpdateInput.value = JogoSelected.nome;

      registryUpdateInput.value = JogoSelected.nome;

      return;

    }

  }

  JogoSelected = null;

}



async function updateJogo() {

  if(!JogoSelected) {

    addLog(`Não é possível atualizar o jogo Primeiro vocês deve selecione um jogo`, LogEnum.WARNING);

    return;

  }



  const URL = `/api/jogos/${JogoSelected.id}`;

  const JogoData = {

    'nome': nameUpdateInput.value,

    'nome': registryUpdateInput.value

  };

  const putRequest = {

    method: 'PUT',

    body: JSON.stringify(JogoData),

    headers: {

      'Content-type': 'application/json;charset=UTF-8'

    }

  };

  try {

    const resp = await fetch(URL, putRequest);

    if (resp.status == 200) {

      addLog(`Jogo ${JogoData.nome} atualizado com sucesso`, LogEnum.INFO);

      resetUpdateForm();

      await readJogo();

    }

  } catch (e) {

    addLog(`Exception during "updateJogo()" - ${e}`, LogEnum.DANGER);

  }

}



// =========================

// DELETE Methods

// =========================

function prepareForDelete(id) {

  for(i in Jogo) {

    if (Jogo[i].id == id) {

      JogoSelected = Jogo[i];

      JogoSelectedElement.innerHTML = JogoSelected.nome;

      return;

    }

  }

  JogoSelected = null;

}



async function deleteJogo() {

  if(!JogoSelected) {

    addLog(`Não é possível apagar o jogo Primeiro vocês deve selecione um jogo`, LogEnum.WARNING);

    return;

  }

  const id = JogoSelected.id;

  JogoSelected = null;

  const URL = `/api/jogos/${id}`;

  const deleteRequest = {

    method: 'DELETE'

  };

  try {

    const resp = await fetch(URL, deleteRequest);

    if (resp.status == 200) {

      addLog(`Jogo ${id} apagado com sucesso`, LogEnum.INFO);

      await readJogo();

    } else {

      addLog(`Jogo ${id} não encontrado`, LogEnum.WARNING);

    }

  } catch (e) {

    addLog(`Exception during "deleteJogo()" - ${e}`, LogEnum.DANGER);

  }

}



// Load Jogo Table on init

readJogo();


      
                  