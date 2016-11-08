package com.javaaround.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType;  

@Path("/calculatorRest")
public class CalculatorRestService {

	// This method is called if TEXT_PLAIN is requested
	//.it is defualt handler if not browser  request 
	@GET
	@Produces(MediaType.TEXT_PLAIN)  
	@Path("/{num1}/{num2}")
	public String add1(@PathParam("num1") Integer num1,@PathParam("num2") Integer num2) {
		return String.valueOf(num1 + num2);
	}

	// This method is called if XML is requested 
	@GET
	@Produces(MediaType.TEXT_XML)  
	@Path("/{num1}/{num2}")
	public String add2(@PathParam("num1") Integer num1,@PathParam("num2") Integer num2) {
		return "<?xml version=\"1.0\"?>" + "<sum>"+ (num1 + num2) + "</sum>";  
    } 
	// This method is called if TEXT_HTML is requested
	//.it is defualt handler if browser  request
	@GET
	@Produces(MediaType.TEXT_HTML)  
	@Path("/{num1}/{num2}")
	public String add3(@PathParam("num1") Integer num1,@PathParam("num2") Integer num2) {
		return "<html> " + "<title>" + "Hello Jersey" + "</title>"  
        + "<body><h1>" + "Sum : " +(num1 + num2) + "</h1></body>" + "</html> ";
	}

}