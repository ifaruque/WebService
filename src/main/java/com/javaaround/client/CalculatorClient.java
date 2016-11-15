package com.javaaround.client;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.javaaround.webservice.CalculatorService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;

public class CalculatorClient{

	public static void main(String[] args) throws Exception {

		/*URL url = new URL("http://localhost:8282/WebService/calculatorWS?wsdl");

        //1st argument service URI, refer to wsdl document above
	    //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://webservice.javaaround.com/", "calculatorService");

        Service service = Service.create(url, qname);

        CalculatorService calculatorService = service.getPort(CalculatorService.class);
		*/
		
			/*
		Client client = Client.create(config);	
		WebResource webResource = client
		   .resource("http://localhost:8282/WebService/rest/calculatorRest/2/3");

		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);
        System.out.println("sum of 2 and 3 is : " + response.getEntity(String.class));
        */
      
        String CRUD_REST_URI = "http://localhost:8282/WebService/rest/";
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);  
	 
	    WebResource service = client.resource(UriBuilder.fromUri(CRUD_REST_URI).build());
		//read json format   
		ClientResponse response = service
			.path("employees")
			.accept("application/json")
            .get(ClientResponse.class);
        System.out.println(response.getEntity(String.class));

        //add employee
        Form form = new Form();
        form.add("id", "5");
        form.add("name","Programmer");
        form.add("salary",2222);


		ClientResponse response1 = service
			.path("employees")
			.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
  			.post(ClientResponse.class, form);
  		//2nd way
  		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("id", "6");
		formData.add("name", "2nd employee");
		form.add("salary",2555);
		
		ClientResponse response2 = service
			.path("employees")
			.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
  			.post(ClientResponse.class, formData);	
        

    }

}