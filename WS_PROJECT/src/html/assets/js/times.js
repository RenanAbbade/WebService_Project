// Get Input Elements

const idInput       = document.getElementById('idInput');

const nameInput     = document.getElementById('nameInput');

const registryInput = document.getElementById('registryInput');

const idUpdateInput       = document.getElementById('idUpdateInput');

const nameUpdateInput     = document.getElementById('nameUpdateInput');

const registryUpdateInput = document.getElementById('registryUpdateInput');



// Get Content Elements

const loggerElement = document.getElementById('logger');

const TimesElement = document.getElementById('Times');

const TimesSelectedElement = document.getElementById('timesSelected');



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

    listaTimes += makeTimesRow(Times[i]);

  }

  TimesElement.innerHTML = listaTimes;

}



function makeTimesRow(time) {

  return `<tr><th scope="row">${timeid}</th>

          <td>${timenome}</td><td>${timenome}</td>

          <td class="text-center">

            <button type="button" class="btn btn-warning" onClick="prepareUpdateForm(${timeid})" data-toggle="modal" data-target="#updateModal">editar</button>

            <button type="button" class="btn btn-danger" onClick="prepareForDelete(${timeid})" data-toggle="modal" data-target="#deleteModal">apagar</button>

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

  TimesSelected = null;

}



function resetDeleteModal() {

  deleteTimes.innerHTML = "";

  TimesSelected = null;

}





// =========================

// CREATE Methods

// =========================

async function createTimes() {

  const URL = `/api/times`;

  const TimesData = {

    'nome': nameInput.value,

    'nome': registryInput.value

  };

  const postRequest = {

    method: 'POST',

    body: JSON.stringify(TimesData),

    headers: {

      'Content-type': 'application/json;charset=UTF-8'

    }

  };

  try {

    const resp = await fetch(URL, postRequest);

    if (resp.status == 200) {

      addLog(`Time ${TimesData.nome} criado com sucesso`, LogEnum.INFO);

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

  TimesElement.innerHTML = 'carregando...';

  const url = `/api/times`;

  try {

    const resp = await fetch(url);

    Times = await resp.json();

    addLog('Timees carregados com sucesso',LogEnum.INFO);

    await reloadTimes();

  } catch (e) {

    addLog(`Exception during "readTimes()" - ${e}`, LogEnum.DANGER);

  }

}





// =========================

// UPDATE Methods

// =========================

function prepareUpdateForm(id) {

  for(i in Times) {

    if (Times[i].id == id) {

      TimesSelected = Times[i];

      idUpdateInput.value = TimesSelected.id;

      nameUpdateInput.value = TimesSelected.nome;

      registryUpdateInput.value = TimesSelected.nome;

      return;

    }

  }

  TimesSelected = null;

}



async function updateTimes() {

  if(!TimesSelected) {

    addLog(`Não é possível atualizar o time Primeiro vocês deve selecione um time`, LogEnum.WARNING);

    return;

  }



  const URL = `/api/times/${TimesSelected.id}`;

  const TimesData = {

    'nome': nameUpdateInput.value,

    'nome': registryUpdateInput.value

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

  for(i in Times) {

    if (Times[i].id == id) {

      TimesSelected = Times[i];

      TimesSelectedElement.innerHTML = TimesSelected.nome;

      return;

    }

  }

  TimesSelected = null;

}



async function deleteTimes() {

  if(!TimesSelected) {

    addLog(`Não é possível apagar o time Primeiro vocês deve selecione um time`, LogEnum.WARNING);

    return;

  }

  const id = TimesSelected.id;

  TimesSelected = null;

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


      
                  