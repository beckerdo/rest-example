package com.example.rest.example.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.rest.example.dtos.ExceptionDTO;
import com.example.rest.example.dtos.MessageConverter;
import com.example.rest.example.dtos.MessageDTO;
import com.example.rest.example.dtos.MessageListDTO;
import com.example.rest.example.entities.Message;

import org.unix4j.Unix4j;


/** 
 * This is an example service that serves up the entities.
 * @author <a href="mailto:dabecker@paypal.com">Dan Becker</a>
 */
@Component
@Scope("request")
@Path("/messages/")
public class MessageService {

	/** A static in-memory repository. Some example data are provided. */
    protected static List<Message> messages = new ArrayList<Message>(
    	Arrays.asList(new Message("First message"), new Message("Second message")));

    /** 
     * Get/retrieve a list of all entities from the store.
     * Use Accept request header to specify format.
     * @return
     */
    @GET
    @Produces({"application/json","application/xml"})
    public MessageListDTO getMessages() {
    	return MessageConverter.toDTO(messages);
    }

    /**
     * Get/retrieve a single item from the store.
     * Use Accept request header to specify format.
     * @param index the index of the item in the store
     * @return
     */
    @GET
    @Produces({"application/json","application/xml"})
    @Path("/{index}")
    public Response getMessage(@PathParam("index") int index) {
        try {
            return Response.status(Status.OK).entity(
                    MessageConverter.toDTO(messages.get(index))).build();
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new ExceptionDTO(e)).build();
        }
    }

    /**
     * Post/create a new entity in th the store.
     * Use Content-Type request header to specify body format.
     * @param messageDTO
     * @return
     */
    @POST
    @Consumes({"application/json","application/xml"})
    public Response addMessage(MessageDTO messageDTO) {
        Message entity = MessageConverter.toEntity(messageDTO);
        messages.add(entity);
        return Response.status(Status.OK).build();
    }

    /**
     * Put/update an existing entity in the store.
     * Use Content-Type request header to specify body format.
     * <p>
     * The body of the entity specifies a SED command to
     * update the existing entity.
     * For example "s/Frotball/Football/g" will fix a spelling error.
     * @param messageDTO
     * @return
     */
    @PUT
    @Consumes({"application/json","application/xml"})
    @Path("/{index}")
    public Response updateMessage(@PathParam("index") int index, MessageDTO messageDTO) {
        try {
        	Message message = MessageConverter.toEntity(messageDTO);
        	String sedCommand = message.getContent();
        	if (( null != sedCommand) && (sedCommand.length() > 0 )) {
        		String content = messages.get( index ).getContent();
        		String update = Unix4j.fromString( content ).sed( sedCommand ).toStringResult();
        		// System.out.println( "Content=\"" + content + "\", update=\"" + update + "\"");
        		message.setContent( update );
                messages.set(index, message );
                return Response.status(Status.OK).build();        		
        	} else
                return Response.status(Status.BAD_REQUEST).build();        		
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new ExceptionDTO(e)).build();
        }
    }

    /**
     * Delete/remove a single item from the store.
     * The entity deleted is returned in the response body.
     * Use Accept request header to specify format.
     * @param index the index of the item in the store
     * @return
     */
    @DELETE
    @Produces({"application/json","application/xml","text/plain"})
    @Path("/{index}")
    public Response removeMessage(@PathParam("index") int index) {
        try {
            MessageDTO messageDTO = MessageConverter.toDTO(messages.get(index));
            messages.remove(index);
            return Response.status(Status.OK).entity(messageDTO).build();
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new ExceptionDTO(e)).build();
        }
    }
}