package pl.bdygasinski.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pl.bdygasinski.dto.ClientInputDTO;
import pl.bdygasinski.dto.ClientOutputDTO;
import pl.bdygasinski.exception.ClientNotValidException;
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
}
