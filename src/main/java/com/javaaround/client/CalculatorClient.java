package com.javaaround.client;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.javaaround.webservice.CalculatorService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CalculatorClient{

	public static void main(String[] args) throws Exception {

		/*URL url = new URL("http://localhost:8282/WebService/calculatorWS?wsdl");

        //1st argument service URI, refer to wsdl document above
	    //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://webservice.javaaround.com/", "calculatorService");

        Service service = Service.create(url, qname);

        CalculatorService calculatorService = service.getPort(CalculatorService.class);
		*/
		Client client = Client.create();

		WebResource webResource = client
		   .resource("http://localhost:8282/WebService/rest/calculatorRest/2/3");

		ClientResponse response = webResource.accept("text/plain")
                   .get(ClientResponse.class);
        System.out.println("sum of 2 and 3 is : " + response.getEntity(String.class));

    }

}