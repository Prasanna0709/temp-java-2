package org.prasanna.controller;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
public class UsersController {
	
	String[] arr = {"Prasanna","Kumar","Suresh"};
	
	@Path("/users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RestResponse<String[]> getAllUsers(){
		return RestResponse.status(Status.OK, arr);
	}

}
