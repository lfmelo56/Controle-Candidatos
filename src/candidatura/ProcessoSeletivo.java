package candidatura;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {
    public static void main(String[] args) {
        selecaoCandidatos();
    }

    static void entrandoEmContato(String candidato) {
        int tentativasRealizadas = 1;
        boolean continuarTentando = true;
        boolean atendeu = false;
        
        do {
            atendeu = atender();
            continuarTentando = !atendeu;
            
            if (continuarTentando) {
                System.out.println("Tentativa " + tentativasRealizadas + " para contato com " + candidato);
                tentativasRealizadas++;
            } else {
                System.out.println("CONTATO REALIZADO COM SUCESSO com " + candidato);
            }

        } while (continuarTentando && tentativasRealizadas < 3);

        if (atendeu) {
            System.out.println("CONSEGUIMOS CONTATO COM " + candidato + " NA " + tentativasRealizadas + "ª TENTATIVA");
        } else {
            System.out.println("NÃO CONSEGUIMOS CONTATO COM " + candidato + ". NÚMERO MÁXIMO DE TENTATIVAS (" + tentativasRealizadas + ") REALIZADO");
        }
    }

    static boolean atender() {
        return new Random().nextInt(3) == 1; 
    }

    static void imprimirSelecionados(String[] selecionados) {
        System.out.println("Imprimindo a lista de candidatos selecionados:");
        for (String candidato : selecionados) {
            System.out.println("O candidato selecionado foi " + candidato);
        }
    }

    static void selecaoCandidatos() {
        String[] candidatos = {"FELIPE", "MARCIA", "JULIA", "PAULO", "AUGUSTO", "MONICA", "FABRICIO", "MIRELA", "DANIELA", "JORGE"};
        String[] selecionados = new String[5];
        
        int candidatosSelecionados = 0;
        int candidatosAtual = 0;
        double salarioBase = 2000.0;

        while (candidatosSelecionados < 5 && candidatosAtual < candidatos.length) {
            String candidato = candidatos[candidatosAtual];
            double salarioPretendido = valorPretendido();

            System.out.println("O candidato " + candidato + " solicitou este valor de salário: " + salarioPretendido);
            if (salarioBase >= salarioPretendido) {
                System.out.println("O candidato " + candidato + " foi selecionado para a vaga");
                selecionados[candidatosSelecionados] = candidato;
                candidatosSelecionados++;
            }
            candidatosAtual++;
        }

        imprimirSelecionados(selecionados);

        for (String candidato : selecionados) {
            entrandoEmContato(candidato);
        }
    }

    static double valorPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }

    static void analisarCandidato(double salarioPretendido) {
        double salarioBase = 2000.0;
        if (salarioBase > salarioPretendido)
            System.out.println("LIGAR PARA O CANDIDATO");
        else if (salarioBase == salarioPretendido)
            System.out.println("LIGAR PARA O CANDIDATO COM CONTRAPROPOSTA");
        else {
            System.out.println("AGUARDANDO RESULTADO DOS DEMAIS CANDIDATOS");
        }
    }
}
