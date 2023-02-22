package pl.bdygasinski.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pl.bdygasinski.dto.ClientInputDTO;
import pl.bdygasinski.dto.ClientOutputDTO;
import pl.bdygasinski.dto.ClientUpdateDTO;
import pl.bdygasinski.exception.manager.ClientNotFoundManagerException;
import pl.bdygasinski.exception.manager.ClientNotValidException;
import pl.bdygasinski.manager.ClientManager;

import java.util.List;

@Path("clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientRest {

    @Inject
    private ClientManager clientManager;

    @GET
    public Response getAll() {
        List<ClientOutputDTO> allClients = clientManager.getAll();

        return Response.ok(allClients).build();
    }

    @POST
    public Response createClient(@NotNull @Valid ClientInputDTO dto) {
        try {
            ClientOutputDTO client = clientManager.createClient(dto);

            return Response.status(Response.Status.CREATED).entity(client).build();
        } catch (ClientNotValidException e) {

            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClientById(@PathParam("id") Long id) {
        try {
            clientManager.deleteClientById(id);

            return Response.noContent().build();
        } catch (ClientNotFoundManagerException e) {

            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findClientById(@PathParam("id") Long id) {
        try {
            ClientOutputDTO client = clientManager.findClientById(id);

            return Response.ok(client).build();
        } catch (ClientNotFoundManagerException e) {

            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateClient(@PathParam("id") Long id, @NotNull @Valid ClientUpdateDTO dto) {
        try {
            ClientOutputDTO client = clientManager.updateClient(id, dto);

            return Response.ok(client).build();
        } catch (ClientNotFoundManagerException e) {

            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (ClientNotValidException e) {

            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
