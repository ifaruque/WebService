package com.javaaround.webservice;
import java.util.List;
 
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
 
import com.javaaround.webservice.dao.EmployeeDAO;
import com.javaaround.webservice.model.Employee;
import com.javaaround.webservice.service.EmployeeService;
import java.net.URI;
 
@Path("/employees")
public class EmployeeResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    EmployeeService employeeService = new EmployeeService();
    // This default constructor is required if there are other constructors.
    public EmployeeResource(){

    }
    public EmployeeResource(UriInfo uriInfo, Request request) {
        this.uriInfo = uriInfo;
        this.request = request;
       
    }
    // URI:
    // /contextPath/servletPath/employees
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Employee> getEmployees_JSON() {
        List<Employee> listOfCountries = employeeService.getAllEmployees();
        return listOfCountries;
    }
    
    // URI:
    // /contextPath/servletPath/employees/{id}
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Employee getEmployee(@PathParam("id") Integer id) {
        return employeeService.getEmployee(id);
    }
 
    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response  addEmployee(@FormParam("id") Integer id,
            @FormParam("name") String name,
            @FormParam("salary") Double salary) {
        Employee emp = new Employee(id, name, salary);    
        Employee emp2 = employeeService.addEmployee(emp);
        try{
            URI location = new URI(String.valueOf(uriInfo.getAbsolutePath()));
            return Response.seeOther(location).build();
        }catch(Exception e){

        }
       
        return Response.status(200).entity("employee add failed").build();
    }
 
    // URI:
    // /contextPath/servletPath/employees
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Employee updateEmployee(@FormParam("id") Integer id,
            @FormParam("name") String name,
            @FormParam("salary") Double salary) {
        Employee emp = new Employee(id, name, salary); 
        return employeeService.updateEmployee(emp);
    }
 
    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteEmployee(@PathParam("id") Integer id) {
        employeeService.deleteEmployee(id);
    }
 
}