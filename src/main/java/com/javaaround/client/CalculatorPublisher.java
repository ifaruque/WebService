package com.javaaround.client;

import javax.xml.ws.Endpoint;  
import com.javaaround.webservice.Calculator;
//Endpoint publisher  
public class CalculatorPublisher{  
    public static void main(String[] args) {  
       Endpoint.publish("http://localhost:7779/ws/calculator", new Calculator());  
    }  
}  