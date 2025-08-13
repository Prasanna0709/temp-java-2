package org.prasanna.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloController {

	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public String helloWorld() {
		return "Hello I am from quarkus sample deployment app";
	}
	
}
