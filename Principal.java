import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {
        
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário "João"
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com formatação
        System.out.println("--- Lista de Funcionários ---");
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat formatadorNumero = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        formatadorNumero.setMinimumFractionDigits(2);

        for (Funcionario funcionario : funcionarios) {
            String dataFormatada = funcionario.getDataNascimento().format(formatadorData);
            String salarioFormatado = formatadorNumero.format(funcionario.getSalario());
            System.out.println("Nome: " + funcionario.getNome() + " | Nascimento: " + dataFormatada + 
                               " | Salário: R$ " + salarioFormatado + " | Função: " + funcionario.getFuncao());
        }

        // 3.4 - Aumento de 10%
        for (Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        }

        // 3.5 - Agrupar por função em um MAP
        Map<String, List<Funcionario>> agrupadosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            agrupadosPorFuncao.putIfAbsent(funcionario.getFuncao(), new ArrayList<>());
            agrupadosPorFuncao.get(funcionario.getFuncao()).add(funcionario);
        }

        // 3.6 - Imprimir os funcionários agrupados por função
        System.out.println("\n--- Funcionários Agrupados por Função ---");
        for (Map.Entry<String, List<Funcionario>> entry : agrupadosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println("  - " + f.getNome());
            }
        }

        // 3.8 - Imprimir funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\n--- Aniversariantes dos meses 10 e 12 ---");
        for (Funcionario funcionario : funcionarios) {
            int mes = funcionario.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                System.out.println(funcionario.getNome() + " (Mês " + mes + ")");
            }
        }

        // 3.9 - Imprimir o funcionário com a maior idade (nome e idade)
        System.out.println("\n--- Funcionário Mais Velho ---");
        Funcionario maisVelho = null;
        int maiorIdade = -1;
        for (Funcionario funcionario : funcionarios) {
            int idade = Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
            if (idade > maiorIdade) {
                maiorIdade = idade;
                maisVelho = funcionario;
            }
        }
        
        System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + maiorIdade + " anos");

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\n--- Funcionários em Ordem Alfabética ---");
        funcionarios.sort(Comparator.comparing(Pessoa::getNome));
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }

        // 3.11 - Imprimir o total dos salários
        System.out.println("\n--- Total da Folha de Pagamento ---");
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("Total: R$ " + formatadorNumero.format(totalSalarios));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        System.out.println("\n--- Quantidade de Salários Mínimos ---");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario funcionario : funcionarios) {
            // Usando RoundingMode.DOWN para arredondar para baixo
            BigDecimal qtdSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.DOWN);
            System.out.println(funcionario.getNome() + " ganha " + qtdSalariosMinimos + " salários mínimos.");
        }
    }
}
