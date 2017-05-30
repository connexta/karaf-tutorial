package com.connexta.rest;

import com.connexta.api.Greeting;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class GreetingRest {
    private Greeting greetingService;

    public GreetingRest(Greeting greetingService) {
        this.greetingService = greetingService;
    }

    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
        return greetingService.hello(name);
    }
}
