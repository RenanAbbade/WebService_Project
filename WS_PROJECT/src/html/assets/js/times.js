// Get Input Elements

const idInput       = document.getElementById('idInput');

const nomeInput     = document.getElementById('nomeInput');

const cidadeInput = document.getElementById('cidadeInput');

const estadoInput = document.getElementById('estadoInput');


const idUpdateInput       = document.getElementById('idUpdateInput');

const nomeUpdateInput     = document.getElementById('nomeUpdateInput');

const cidadeUpdateInput = document.getElementById('cidadeUpdateInput');

const estadoUpdateInput = document.getElementById('estadoUpdateInput');





// Get Content Elements

const loggerElement = document.getElementById('logger');

const timesElement = document.getElementById('Times');

const timesSelectedElement = document.getElementById('timesSelected');



// Store fetched times

var times = [];

var timesSelected = null;



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



function reloadTimes() {

  var listaTimes = '';

  var n = times.length;

  for (var i = 0; i < n; i++) {

    listaTimes += makeTimesRow(times[i]);

  }

  timesElement.innerHTML = listaTimes;

}



function makeTimesRow(times) {

  return `<tr  ><th  scope="row">${times.id}</th>

          <td >${times.nome}</td><td>${times.cidade}</td><td >${times.estado}</td>


          <td class="text-left" >

            <button type="button"class="btn btn-warning" style="float:left;"  onClick="prepareUpdateForm(${times.id})" data-toggle="modal" data-target="#updateModal">editar</button>

            <button type="button" class="btn btn-danger" style="float:left;" onClick="prepareForDelete(${times.id})" data-toggle="modal" data-target="#deleteModal">apagar</button>

          </td>`;

}



function resetCreateForm() {

  idInput.value = '';

  nomeInput.value = '';

  estadoInput.value = '';

  cidadeInput.value = '';

}



function resetUpdateForm() {

  idUpdateInput.value = '';

  nomeUpdateInput.value = '';

  estadoUpdateInput.value = '';

  cidadeUpdateInput.value = '';

  timesSelected = null;

}



function resetDeleteModal() {

  deleteTimes.innerHTML = "";

  timesSelected = null;

}





// =========================

// CREATE Methods

// =========================

async function createTimes() {

  const URL = `/api/times`;

  const timesData = {

    'nome': nomeInput.value,

    'cidade': cidadeInput.value,

    'estado': estadoInput.value

  };

  const postRequest = {

    method: 'POST',

    body: JSON.stringify(timesData),

    headers: {

      'Content-type': 'application/json;charset=UTF-8'

    }

  };

  try {

    const resp = await fetch(URL, postRequest);

    if (resp.status == 200) {

      addLog('Time ${TimesData.nome} criado com sucesso', LogEnum.INFO);

      await resetCreateForm();

      await readTimes();

    } else {

      addLog(`Criar Time: respota diferente de 200 - ${resp.status}`, LogEnum.WARNING);

      await readTimes();

    }

  } catch (e) {

    addLog(`Exception during "createTimes()" - ${e}`, LogEnum.DANGER);

  }

}



// =========================

// READ Methods

// =========================

async function readTimes() {

  timesElement.innerHTML = 'carregando...';

  const url = `/api/times`;

  try {

    const resp = await fetch(url);

    times = await resp.json();

    addLog('Times carregados com sucesso',LogEnum.INFO);

    await reloadTimes();

  } catch (e) {

    addLog(`Exception during "readTimes()" - ${e}`, LogEnum.DANGER);

  }

}





// =========================

// UPDATE Methods

// =========================

function prepareUpdateForm(id) {

  for(i in times) {

    if (times[i].id == id) {

      timesSelected = times[i];

      idUpdateInput.value = timesSelected.id;

      nomeUpdateInput.value = timesSelected.nome;

      cidadeUpdateInput.value = timesSelected.cidade;

      estadoUpdateInput.value = timesSelected.estado;

      return;

    }

  }

  timesSelected = null;

}



async function updateTimes() {

  if(!timesSelected) {

    addLog(`Não é possível atualizar o time Primeiro vocês deve selecione um time`, LogEnum.WARNING);

    return;

  }



  const URL = `/api/times/${timesSelected.id}`;

  const TimesData = {

    'nome': nomeUpdateInput.value,

    'cidade': cidadeUpdateInput.value,

    'estado': estadoUpdateInput.value

  };

  const putRequest = {

    method: 'PUT',

    body: JSON.stringify(TimesData),

    headers: {

      'Content-type': 'application/json;charset=UTF-8'

    }

  };

  try {

    const resp = await fetch(URL, putRequest);

    if (resp.status == 200) {

      addLog(`Time ${TimesData.nome} atualizado com sucesso`, LogEnum.INFO);

      resetUpdateForm();

      await readTimes();

    }

  } catch (e) {

    addLog(`Exception during "updateTimes()" - ${e}`, LogEnum.DANGER);

  }

}



// =========================

// DELETE Methods

// =========================

function prepareForDelete(id) {

  for(i in times) {

    if (times[i].id == id) {

      timesSelected = times[i];

      timesSelectedElement.innerHTML = timesSelected.nome;

      return;

    }

  }

  timesSelected = null;

}



async function deleteTimes() {

  if(!timesSelected) {

    addLog(`Não é possível apagar o time Primeiro vocês deve selecione um time`, LogEnum.WARNING);

    return;

  }

  const id = timesSelected.id;

  timesSelected = null;

  const URL = `/api/times/${id}`;

  const deleteRequest = {

    method: 'DELETE'

  };

  try {

    const resp = await fetch(URL, deleteRequest);

    if (resp.status == 200) {

      addLog(`Time ${id} apagado com sucesso`, LogEnum.INFO);

      await readTimes();

    } else {

      addLog(`Time ${id} não encontrado`, LogEnum.WARNING);

    }

  } catch (e) {

    addLog(`Exception during "deleteTimes()" - ${e}`, LogEnum.DANGER);

  }

}



// Load Times Table on init

readTimes();


      
                  