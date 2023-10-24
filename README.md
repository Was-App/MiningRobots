# Mining Robots - parte 1

Mining Robots é um jogo em que, a equipe que extrair a maior quantidade de Hélio do terreno, dentro do tempo de jogo estipulado, ganha. 
Cada equipe possui uma quantidade pré-determinada de robôs que, em cada célula do terreno, podem extrair uma quantidade específica do material, levando em
consideração um fator de erro de extração. A cada turno os robôs executam suas ações até que não haja mais tempo de

## Entrada

A entrada do programa é composta por dois arquivos no formato ".json". Um arquivo contém as informações sobre o terreno a ser utilizado dentro do jogo, sendo elas: comprimento e largura do terreno. O outro arquivo contém as informações sobre o jogo em si e as equipes, sendo elas: tempo de jogo, nome de cada equipe, quantidade de robôs, nome e conjunto de instruções para cada robô.

### Instruções

As instruções podem ser simples, como virar a direita (turnLeft), virar a esquerda (turnRight), andar(walk), e prospectar Helium (prospectHelium), ou complexas, como andar para o caminho de menor resistência (walkToBestDirection) ou prospectar apenas se a concentração de helium for maior que a metade (prospectIfGreaterThenHalf).

## Saída

A cada iteração do programa, é exibido, no terminal, o nome das equipes participantes, a quantidade de Hélio extraído por cada equipe e o tempo atual de jogo, para que seja possível acompanhar o andamento da partida.

## 🖥 Running the program
**Primeiro, será necessário ter o pacote json-simple-1.1.jar instalado em sua máquina. <br>**
Para compilar o programa, será necessário:
- `{path/to/json-sinple-1.1.jar}` <br>

Em seguida, execute:
```bash
javac -classpath {path/to/json-simple-1.1.jar} {todos os arquivos com extensão .java}
```
---

Para executar o jogo, será necessário:

- `{inputGamePath}`
- `{inputTerrainPath}`

```bash
java -classpath {path/to/json-simple-1.1.jar} {todos os arquivos com extensão .class} {inputGamePath} {inputTerrainPath}
```



