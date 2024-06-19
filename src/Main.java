import entity.Candidato;
import enums.StatusVotacao;
import service.UrnaService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UrnaService urna = new UrnaService(List.of(
                new Candidato(51, "Jorge"),
                new Candidato(55, "Helena")
        )/*, StatusVotacao.EM_TESTE */);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o seu voto:");
        System.out.println("51 - Jorge");
        System.out.println("55 - Helena");
        System.out.println("1 - Voto em branco");
        System.out.println("2 - Voto nulo");
        System.out.println("99 - Encerrar votação");

        do {
            if (scanner.hasNextInt()) {
                var codigo = scanner.nextInt();
                if (codigo == 99) {
                    urna.encerrarVotacao();
                } else {
                    urna.votar(codigo);
                }
            } else {
                System.out.println("Por favor, insira um número válido!");
                scanner.next();
            }
        } while (urna.getStatus() == StatusVotacao.ABERTO);
    }
}