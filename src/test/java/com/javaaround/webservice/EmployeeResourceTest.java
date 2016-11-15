package com.javaaround.webservice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.UriBuilder;

public class EmployeeResourceTest {
  private WebResource service;
  private static final String CRUD_REST_URI = "http://localhost:8282/WebService/rest/";
  @Before
  public void setup() throws Exception {
    ClientConfig config = new DefaultClientConfig();
    Client client = Client.create(config);  
 
    service = client.resource(UriBuilder.fromUri(CRUD_REST_URI).build());
  }
  @Test
  public void testaddEmployee() {
    //add employee
    Form form = new Form();
    form.add("id", "5");
    form.add("name","Programmer");
    form.add("salary",2222);


    ClientResponse response1 = service
        .path("employees")
        .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
        .post(ClientResponse.class, form);
  }  
  
}