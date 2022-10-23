package com.lira.pensamentos;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class PensamentoRepository {

    private int id = 0;
    private Map<Integer, Pensamento> pensamentos = new HashMap<>();
    private String textoGrande = """
        Mussum Ipsum, cacilds vidis litro abertis. Posuere libero varius. Nullam a nisl ut ante blandit hendrerit. Aenean sit amet nisi. 
        Diuretics paradis num copo é motivis de denguis. Atirei o pau no gatis, per gatis num morreus. 
        Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis. Sapien in monti palavris qui num significa nadis i pareci latim.""";

    public PensamentoRepository() {
        pensamentos.put(++id, new Pensamento(id, "I love Angular", "Nay", "modelo1"));
        pensamentos.put(++id, new Pensamento(id, "I love Java", "Lira", "modelo2"));
        pensamentos.put(++id, new Pensamento(id, this.textoGrande, "Alexa", "modelo3"));
    }

    public void adicionar(Pensamento pensamento) {
        this.pensamentos.put(pensamento.id(), pensamento);
    }

    public List<Pensamento> obterLista() {
        return new ArrayList<>(pensamentos.values());
    }

    public Optional<Pensamento> obter(Integer id) {
        return this.pensamentos.values()
                .stream()
                .filter(p -> p.id().equals(id))
                .findFirst();
    }

    public void substituir(int id, Pensamento pensamento) {
        this.pensamentos.replace(id, pensamento);
    }
    public void remover(Integer id) {
        this.pensamentos.remove(id);
    }
}
