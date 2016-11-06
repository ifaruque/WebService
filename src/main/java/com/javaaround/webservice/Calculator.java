package com.javaaround.webservice;


import javax.jws.WebMethod;
import javax.jws.WebService;

//Service Endpoint Interface
@WebService(endpointInterface = "com.javaaround.webservice.CalculatorService",serviceName="calculatorService")
public class Calculator implements CalculatorService{

	@Override
	public int add(int num1 , int num2){
		return num1 + num2;
	}

}