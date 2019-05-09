var saudação = alert("Bem-vindo ao FIFA THE QUIZ!");
var nome = "";
var mostraNome = document.querySelector("#user-name"); // aqui seleciono o ELEMENTO(<span id=user-name>) do nome do usuario la no quiz.html

while (nome == null || nome == ""){
    nome = prompt("Digite o seu nome: ");
    document.getElementById("user-name").innerHTML = ("Boa sorte " + nome);
    mostraNome.classList.remove("invisivel"); // como o comportamento patrao do elemento é ficar invisvel, assim que o nome for == preenchido ele remove a classe invivel do span lá, e deixa o conteudo visivel, assim sendo inserido na pagina a frase "boa sorte renan" no caso :)
}




      
                  