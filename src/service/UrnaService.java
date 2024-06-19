package service;

import dto.VotoCandidatoDTO;
import entity.Candidato;
import enums.StatusVotacao;
import enums.TipoVoto;

import java.util.ArrayList;
import java.util.List;

public class UrnaService {
    private StatusVotacao status;
    private List<Candidato> candidatos;
    private List<VotoCandidatoDTO> votosPorCandidato;
    private Long totalDeVotosNulos;
    private Long totalDeVotosBrancos;
    private Long totalDeVotos;

    public UrnaService(List<Candidato> candidatos) {
        this.status = StatusVotacao.ABERTO;
        this.candidatos = candidatos;
        this.votosPorCandidato = new ArrayList<>();
        this.totalDeVotosNulos = 0L;
        this.totalDeVotosBrancos = 0L;
        this.totalDeVotos = 0L;
    }

    public UrnaService(List<Candidato> candidatos, StatusVotacao statusInicial) {
        this.status = statusInicial;
        this.candidatos = candidatos;
        this.votosPorCandidato = new ArrayList<>();
        this.totalDeVotosNulos = 0L;
        this.totalDeVotosBrancos = 0L;
        this.totalDeVotos = 0L;
    }

    public void votar(int voto) {
        if (this.status == StatusVotacao.FECHADO) {
            System.out.println("Votação encerrada, não é possível votar");
        }

        if (voto == TipoVoto.BRANCO.getCodigo()) {
            this.totalDeVotos++;
            this.totalDeVotosBrancos++;
            this.votoFeedback();
            return;
        } else if (voto == TipoVoto.NULO.getCodigo()) {
            this.totalDeVotos++;
            this.totalDeVotosNulos++;
            this.votoFeedback();
            return;
        }

        Candidato candidato = candidatos.stream()
            .filter(c -> c.getNumero() == voto)
            .findFirst()
            .orElse(null);

        if (candidato == null) {
            this.totalDeVotos++;
            this.totalDeVotosNulos++;
            this.votoFeedback();
            return;
        }

        VotoCandidatoDTO votoCandidatoDTO = votosPorCandidato.stream()
            .filter(v -> v.getCandidato().getNumero() == voto)
            .findFirst()
            .orElse(null);

        if (votoCandidatoDTO == null) {
            votoCandidatoDTO = new VotoCandidatoDTO(candidato, 1L);
            this.votosPorCandidato.add(votoCandidatoDTO);
        } else {
            votoCandidatoDTO.setQuantidadeVotos(votoCandidatoDTO.getQuantidadeVotos() + 1L);
        }

        this.totalDeVotos++;
        this.votoFeedback();

        if (this.status == StatusVotacao.EM_TESTE) {
            System.out.println("Resultado parcial da votação:\n");
            this.mostrarResultado();
        }
    }

    public void mostrarResultado() {
        System.out.printf("Total de votos: %d\n", totalDeVotos);
        System.out.printf("Total de votos nulos: %d\n", totalDeVotosNulos);
        System.out.printf("Total de votos brancos: %d\n", totalDeVotosBrancos);
        System.out.println("Total de votos por candidatos:");
        this.votosPorCandidato.forEach(v -> {
            System.out.println("Candidato: " + v.getCandidato().getNome() + " - Votos: " + v.getQuantidadeVotos());
        });
    }

    public void encerrarVotacao() {
        this.status = StatusVotacao.FECHADO;
        System.out.println("Votação encerrada!");
        this.mostrarResultado();
    }

    public StatusVotacao getStatus() {
        return this.status;
    }

    private void votoFeedback() {
        System.out.println("Voto computado com sucesso!");
    }
}
