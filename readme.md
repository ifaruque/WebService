### Web Service ###

A Web service is a service offered by an electronic device to another electronic device, communicating with each other via the Internet.It can be used any operating system or programming language Independently

### Web Service Design Way ###

1. SOAP Based Way
2. REST Way

### SOAP Based ###

 SOAP(Simple object access protocal) is a protocal that internally use HTTP POST.it is XML based used for sending and receiving messages.

 `WSDL(Web Services Description Language)` : standard format for describing a web service

 `<types>`	Defines the (XML Schema) data types used by the web service <br/>
 `<message>`	Defines the data elements for each operation<br/>
 `<portType>`	Describes the operations that can be performed and the messages involved.<br/>
 `<binding>`	Defines the protocol and data format for each port type

### UDDI ###

 UDDI(Universal Description, Discovery, and Integration.) is an XML-based standard for describing, publishing, and finding web services

### How to start ###
  
1. create java maven project <br/>
	mvn archetype:generate -DgroupId=com.javaaround -DartifactId=WebService -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
 Java design `jax-ws` api to work on soap based web service so add dependency at pom.xml
	```xml
	 <dependency>
        <groupId>com.sun.xml.ws</groupId>
        <artifactId>jaxws-rt</artifactId>
        <version>2.2.10</version>
     </dependency>
	```
3. create CalculatorService.java

```java
package com.javaaround.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

//Service Endpoint Interface
@WebService
public interface CalculatorService{

	@WebMethod 
	public int add(int num1 , int num2);

}
```	
create Calculator.java

```java
package com.javaaround.webservice;

import com.javaaround.webservice.CalculatorService;
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
```

4. update web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_0.xsd"
    id="WebApp_ID" version="3.0">
    <display-name>JAXWS-Tomcat</display-name>
 
    <listener>
        <listener-class>
            com.sun.xml.ws.transport.http.servlet.WSServletContextListener</listener-class>
    </listener>
     
    <servlet>
        <servlet-name>JAXWSServlet</servlet-name>
        <servlet-class>com.sun.xml.ws.transport.http.servlet.WSServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>JAXWSServlet</servlet-name>
        <url-pattern>/calculatorWS</url-pattern>
    </servlet-mapping>
    <session-config>
        <session-timeout>30</session-timeout>
    </session-config>
     
</web-app>
```
create sun-jaxws.xml under `WEB-INF`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<endpoints xmlns="http://java.sun.com/xml/ns/jax-ws/ri/runtime" version="2.0">
  <endpoint
     name="calculatorWS"
     implementation="com.javaaround.webservice.Calculator"
     url-pattern="/calculatorWS"/>
</endpoints> 
```

5. create war file by following command

`mvn clean package`
deploy WebService.war file on Tomcat(container)<br>

### How to get WSDL File ###
http://localhost:8080/WebService/calculatorWS?wsdl

![Image of Yaktocat](images/1.png)

Yes.  web service is ready.We can use Now

### Usage of own server ###

create CalculatorClient.java

```java
package com.javaaround.client;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.javaaround.webservice.CalculatorService;

public class CalculatorClient{

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://localhost:8282/WebService/calculatorWS?wsdl");

        //1st argument service URI, refer to wsdl document above
	    //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://webservice.javaaround.com/", "CalculatorService");

        Service service = Service.create(url, qname);

        CalculatorService calculatorService = service.getPort(CalculatorService.class);

        System.out.println("sum of 2 and 5 are : " + calculatorService.add(2,5));

    }

}
```

`QName qname = new QName(targetNamespace, name);` that is marked above image

add plugin pom.xml

```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>exec-maven-plugin</artifactId>
  <version>1.4.0</version>
  <executions>
    <execution>
      <id>my-execution</id>
      <!-- if skip phase: none -->
      <phase>package</phase>
      <goals>
        <goal>java</goal>
      </goals>
    </execution>
  </executions>
  <configuration>
    <mainClass>com.javaaround.client.CalculatorClient</mainClass>
    <!--<classpathScope>main</classpathScope> defualt-->
     <arguments>  
       <argument>arg0</argument>  
       <argument>arg1</argument>  
     </arguments>  
  </configuration>
</plugin>
```

Run `mvn clean package` to see output

### Usage of outside from server ###
`wsimport` is used generate java code  based on wsdl file.By the help of java code we can use web service
it is located on $JAVA_HOME\bin\wsimport

1. create desktop App java
mvn archetype:generate -DgroupId=com.javaaround.webservice.clientApp -DartifactId=WSclientApp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

2.  use the command under src/main/java directory `wsimport -keep -p com.javaaround.webservice.clientApp  http://localhost:8282/WebService/calculatorWS?wsdl` 

this will generate App.java , AddResponse.java , Calculatore.java etc

3. Update App.java
```java
package com.javaaround.webservice.clientApp;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {
        CalculatorService_Service service = new CalculatorService_Service();
    CalculatorService calculatorService = service.getCalculatorPort();
        System.out.println("sum of 2 and 5 are : " + calculatorService.add(2,5));
    }
}

```
