# Teste Técnico Prático - Java ☕

Olá! Meu nome é Isabelle e este é o repositório com a minha solução para o teste prático de programação.

## 🚀 Como rodar este projeto 🚀

Este projeto foi desenvolvido em **Java 11**, e busquei garantir simplicidade e foco na lógica.

**Passo a passo:**

1. Faça o download deste projeto ou clone o repositório.
2. Abra a sua IDE de preferência.
3. Crie um novo **Java Project**.
4. Copie as três classes do projeto (`Pessoa.java`, `Funcionario.java` e `Principal.java`) para dentro da pasta `src`.
5. Execute a classe `Principal.java`.
6. O resultado de todas as operações e requisitos será impresso no console da IDE em ordem sequencial.

## Como resolvi os requisitos do enunciado

Busquei manter o código o mais limpo, legível e direto possível, explorando recursos do Java 8 para evitar verbosidade desnecessária.
Abaixo, explico a lógica que usei nas etapas:

- **1 e 2. Classes e Herança:** Criei a estrutura utilizando o pilar da herança com a palavra `extends`. A classe `Funcionario` herda os atributos de `Pessoa` e os inicializa chamando o construtor da classe mãe via `super()`. Para o salário, foi pedido no enunciado a utilização da classe `BigDecimal` em vez do tipo primitivo `double`. (evita imprecisões no arredondamento)

- **3.1 e 3.2. Listas e Remoção Segura:** Agrupei os funcionários em um `ArrayList`. Para a remoção segura do funcionário "João", fiz uso da função lambda `removeIf(funcionario -> funcionario.getNome().equals("João"))`.

- **3.3. Formatação Regional:** Para as datas, utilizei o `DateTimeFormatter` padronizando para `dd/MM/yyyy`. Para os valores numéricos, utilizei o `NumberFormat` configurado especificamente para o Brasil. (`pt-BR`).

- **3.4. Cálculos com BigDecimal:** Como utilizei `BigDecimal` para o salário, as operações matemáticas exigem métodos próprios. Calculei o aumento utilizando o método `.multiply()` com `0.10` para extrair os 10% e o método `.add()` para incorporar esse valor ao salário base atual.

- **3.5 e 3.6. Agrupamento de Dados (Map):** Estruturei o agrupamento em um `HashMap`. Para evitar blocos `if/else` verbosos verificando se a função já existia no Map, utilizei o método `putIfAbsent()`, que inicializa uma nova lista automaticamente caso a chave da função ainda não tenha sido registrada.

- **3.8 e 3.9. Manipulação de Datas (LocalDate):** Para os aniversariantes, peguei o mês de nascimento através da função `.getMonthValue()`. Para encontrar a pessoa mais velha, utilizei a classe `Period.between()` para calcular a diferença de anos entre a data de nascimento e a data atual (`LocalDate.now()`).

- **3.10. Ordenação Alfabética:** A ordenação foi feita utilizando `funcionarios.sort(Comparator.comparing(Pessoa::getNome))`.

- **3.11 e 3.12. Métricas Salariais:** O somatório da folha de pagamento foi feito iterando a lista e acumulando os valores em uma variável inicializada com `BigDecimal.ZERO`. A quantidade de salários mínimos foi extraída pelo método `.divide()`, utilizando a regra `RoundingMode.DOWN` com 2 casas decimais.

---

💡 **Nota:** Busquei desenvolver um código claro e com uma estrutura simples de acompanhar.
Estou à disposição para conversar!
Obrigada pelo seu tempo. ❤
