package dto;

import entity.Candidato;

public class VotoCandidatoDTO {
    private Candidato candidato;
    private Long quantidadeVotos;

    public VotoCandidatoDTO(Candidato candidato, Long quantidadeVotos) {
        this.candidato = candidato;
        this.quantidadeVotos = quantidadeVotos;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public Long getQuantidadeVotos() {
        return quantidadeVotos;
    }

    public void setQuantidadeVotos(Long quantidadeVotos) {
        this.quantidadeVotos = quantidadeVotos;
    }
}
