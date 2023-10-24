# Mining Robots - parte 1

Mining Robots é um jogo em que, a equipe que extrair a maior quantidade de Hélio do terreno, dentro do tempo de jogo estipulado, ganha. 
Cada equipe possui uma quantidade pré-determinada de robôs que, em cada célula do terreno, podem extrair uma quantidade específica do material, levando em
consideração um fator de erro de extração. A cada turno os robôs executam suas ações até que não haja mais tempo de

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



