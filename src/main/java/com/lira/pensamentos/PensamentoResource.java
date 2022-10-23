package com.lira.pensamentos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.*;
import java.net.URI;
import java.util.Optional;

@Path("/pensamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PensamentoResource {

    @Inject
    PensamentoRepository repository;

    @GET
    public Response obterLista() {
        return Response.ok(repository.obterLista()).build();
    }

    @GET
    @Path("/{id}")
    public Response obter(@PathParam("id") Integer id) {
        Optional<Pensamento> pensamento = repository.obter(id);
        return pensamento.isPresent()
                ? Response.ok(pensamento.get()).build()
                : Response.status(NOT_FOUND).build();
    }

    @POST
    public Response adicionar(PensamentoRequest request) {
        Pensamento pensamento = new Pensamento(request);
        repository.adicionar(pensamento);
        URI uri = URI.create("http://localhost:8080/pensamentos/" + pensamento.id());
        return Response.created(uri).build();
    }

    @PUT
    @Path("/{id}")
    public Response subtituir(@PathParam("id") Integer id, PensamentoRequest request) {
        if (repository.obter(id).isPresent()) {
            repository.substituir(id, new Pensamento(id, request));
            URI uri = URI.create("http://localhost:8080/pensamentos/" + id);
            return Response.ok(uri).build();
        }
        return Response.status(NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Integer id) {
        Optional<Pensamento> pensamento = repository.obter(id);
        if (pensamento.isPresent()) {
            repository.remover(id);
            return Response.noContent().build();
        }
        return Response.status(NOT_FOUND).build();
    }

}