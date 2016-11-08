package com.javaaround.webservice;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import com.javaaround.webservice.CalculatorService;
import com.javaaround.webservice.Calculator;
import javax.xml.ws.Service;

@WebServlet(name="HelloServlet", urlPatterns={"/HelloServlet"})
public class CalClientServlet extends HttpServlet {
    //@WebServiceRef(wsdlLocation = "http://localhost:8282/WebService/calculatorWS?wsdl")
    //private CalculatorService service;
   
    
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //Calculator calculatorService = service.getCalculatorPort();
        try {
            
           out.print("calculatorService.add(2,5)");
            
        } finally { 
            out.close();
        }
    } 
    
}    