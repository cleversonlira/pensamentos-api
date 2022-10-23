package com.lira.pensamentos;

import java.util.Random;

public record Pensamento(Integer id, String conteudo, String autoria, String modelo) {
    public Pensamento(Integer id, PensamentoRequest request) {
        this(id, request.conteudo(), request.autoria(), request.modelo());
    }

    public Pensamento(PensamentoRequest request) {
        this(IdGenerator.id(), request.conteudo(), request.autoria(), request.modelo());
    }
}
