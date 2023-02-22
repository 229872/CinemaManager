package pl.bdygasinski.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.Status;

import pl.bdygasinski.dto.TicketInputDTO;
import pl.bdygasinski.dto.TicketOutputDTO;
import pl.bdygasinski.exception.manager.ClientNotFoundManagerException;
import pl.bdygasinski.exception.manager.MovieNotFoundManagerException;
import pl.bdygasinski.exception.manager.TicketNotFoundManagerException;
import pl.bdygasinski.exception.manager.WrongTicketException;
import pl.bdygasinski.manager.TicketManager;

import java.util.List;

@Path("tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketRest {
    @Inject
    private TicketManager ticketManager;

    @GET
    public Response getAllTickets() {
        List<TicketOutputDTO> allTickets = ticketManager.getAllTickets();

        return Response.ok(allTickets).build();
    }

    @GET
    @Path("/{id}")
    public Response getTicketById(@PathParam("id") Long id) {
        try {
            TicketOutputDTO ticket = ticketManager.findTicketById(id);

            return Response.ok(ticket).build();
        } catch (TicketNotFoundManagerException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTicketById(@PathParam("id") Long id) {
        try {
            ticketManager.deleteTicketById(id);

            return Response.noContent().build();
        } catch (TicketNotFoundManagerException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createTicket(@NotNull @Valid TicketInputDTO dto) {
        try {
            TicketOutputDTO ticket = ticketManager.createTicket(dto);

            return Response.status(Status.CREATED).entity(ticket).build();
        } catch (ClientNotFoundManagerException | MovieNotFoundManagerException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (WrongTicketException e) {
            return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
