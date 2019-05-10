function submitRespostas(){
    
            var totalPerguntas = 10; 
            var pontuacaoTotal = 0;
            
           
            // variavel referente as questoes do quiz, op1 = questao1, op2 == questao2...
    
            //primeiro parametro seleciamos o formulario depois o name e por fim o value que seria o valor do formulario... 
            //Exemplo ai:

            //  <input type="radio"  esses caras>>>>(name="opcoes1" value="b") <<<<<esses caras  > Bill Gates e Steve Jobs<br />
    
            var op1 = document.forms["perguntas"]["opcoes1"].value; 
            var op2 = document.forms["perguntas"]["opcoes2"].value;
            var op3 = document.forms["perguntas"]["opcoes3"].value;
            var op4 = document.forms["perguntas"]["opcoes4"].value;
            var op5 = document.forms["perguntas"]["opcoes5"].value;
            var op6 = document.forms["perguntas"]["opcoes6"].value;
            var op7 = document.forms["perguntas"]["opcoes7"].value;
            var op8 = document.forms["perguntas"]["opcoes8"].value;
            var op9 = document.forms["perguntas"]["opcoes9"].value;
            var op10 = document.forms["perguntas"]["opcoes10"].value;
    
    
   
            // validação
            for (pergunta = 1; pergunta <= totalPerguntas; pergunta++){
                
                if(eval('op' + pergunta) == null || eval('op' + pergunta) == ''){ //esse eval vc passa o nome da variavel "op" e o proximo elemento como 1,2,3,4... que no caso seria a variavel pergunta que iniciei no laço for
                    
                    alert("voce esqueceu a questao " + pergunta);
                    return false;
                    
                }
            }
    
          
            //defini repostas corretas
            var repostasCorretas = ["c","a","d","a","a","d","a","c","b","b"]; // definindo a resposta correta de cada questao

            //verifica respostas
            if(op1 == repostasCorretas[0]){ // se resposta da questao 1 == op1 for igual a resposta na posição 0 do array que é a C
                //entao o fdp acertou a questao.
                pontuacaoTotal ++
            }
            if(op2 == repostasCorretas[1]){
               pontuacaoTotal ++
            }
            if(op3 == repostasCorretas[2]){
                pontuacaoTotal ++
            }
            if(op4 == repostasCorretas[3]){
                pontuacaoTotal ++
            }
            if(op5 == repostasCorretas[4]){
                pontuacaoTotal ++
            }
            if(op6 == repostasCorretas[5]){
                pontuacaoTotal ++
            }
            if(op7 == repostasCorretas[6]){
                pontuacaoTotal ++
            }
            if(op8 == repostasCorretas[7]){
                pontuacaoTotal ++
            }
            if(op9 == repostasCorretas[8]){
                pontuacaoTotal ++
            }
            if(op10 == repostasCorretas[9]){
                pontuacaoTotal ++
            }

            if (pontuacaoTotal==0){
           alert("acertou "+pontuacaoTotal+" de 10! FAMOSO PERNA DE PAU!!! Dá rolinho nem em cadeira!");
           }else if (pontuacaoTotal>0 && pontuacaoTotal<=4){
           alert("acertou "+pontuacaoTotal+" de 10! Você levou um dibre desconcertante! Treine mais, e quem sabe na próxima!");
           }else if (pontuacaoTotal>4 && pontuacaoTotal<=7){
           alert("acertou "+pontuacaoTotal+" de 10! Bais ou Benos, não é um Biro Biro, mas também não é um Garrincha né!");
           }else if (pontuacaoTotal>7 && pontuacaoTotal<=9){
           alert("acertou "+pontuacaoTotal+" de 10! Passou pertinho! Você é craque, falta pouco pra ser uma lenda!");
           }else if (pontuacaoTotal == 10){
           alert("acertou "+pontuacaoTotal+" de 10! Sabe tudo de bola..Ou melhor, de game da bola! Nivel PELÉ do FIFA!!!");
           }
            
            
    }

  

    
    
  
    
   
    


                                
    
