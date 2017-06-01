
# Web Service
A web service exposes a public api to communicate with. Usually they follow a standard specification
such as REST or SOAP. For this example we expose a simple REST endpoint. These services are usually
commentate with xml and json instead of human readable html.

# JAX-RS
JAX-RS: Java API for RESTful Web Services (JAX-RS) is a Java programming language API spec that provides 
support in creating web services according to the Representational State Transfer (REST) architectural 
pattern. JAX-RS uses annotations for declaring what HTTP verbs and paths are associated with each method.

# CXF
Apache CXF™ is an open source services framework. CXF helps you build and develop services using frontend 
programming APIs, like JAX-WS and JAX-RS. These services can speak a variety of protocols such as SOAP, 
XML/HTTP, RESTful HTTP, or CORBA and work over a variety of transports such as HTTP, JMS or JBI.
## How It Works

### Components

1. `@GET/POST/PUT/PATCH/DELETE`  
    * States what HTTP verb is used for this method.
2. `@PATH`
    * The web server will invoke this method when the request matches this path and verb.
3. `@PRODUCES`
    * The type of output the web service will display. This example will just be plain text.
4. `@PATHPARAM`
    * The parameter taken from the URI that will be used by the program. In this case it is "name".
 
 
 
### Setting Up a Web Service

1. Specify a private Greeting interface variable and pass it into the public constructor as a 
parameter. 

    * ```java
        private Greeting greetingService;
        
        public GreetingRest(Greeting greetingService) {
            this.greetingService = greetingService;
        } 
       ```
2. Specify JAX-RS annotations

    * ```java
        @GET
        @Path("/hello/{name}")
        @Produces(MediaType.TEXT_PLAIN)
        ```
3. Use the given parameter "name" to return and pass it as a parameter into the hello function. 
This function then returns the the formatted string using "name".
    * ```java
        public String hello(@PathParam("name") String name) {
            return greetingService.hello(name);
        }
        ```
        
### Blueprint definition
#### Add CXF and JAX-RS to the blueprint namespace
```xml
<blueprint xmlns="http://www.osgi.org/xmlns/blueprint/v1.0.0"
           xmlns:cxf="http://cxf.apache.org/blueprint/core"
           xmlns:jaxrs="http://cxf.apache.org/blueprint/jaxrs">
           ...
</blueprint>
```

#### Create the CXF Bus
The bus element is where you would configure CXF. You can add features and interceptors such as an 
authentication interceptor or a logging feature.
```xml
<cxf:bus>
    <cxf:features>
        <cxf:logging/>
    </cxf:features>
</cxf:bus>
```

#### Create the bean that will handle the web requests
```xml
<blueprint>
    <reference id="greetingService" interface="com.connexta.api.Greeting"/>
    <bean id="greetingServiceProxy" class="com.connexta.rest.GreetingRest">
        <argument refe="greetingService"/>
    </bean>
</blueprint>
```

### Create the JAX-RS Server and tell it which bean handles requests
```xml
<jaxrs:server address="/greeting" id="greetingRest">
    <jaxrs:serviceBeans>
        <ref component-id="greetingServiceProxy"/>
    </jaxrs:serviceBeans>
</jaxrs:server>
```

[Learn More About JAX-RS]

[Learn More About CXF]

[Learn More About JAX-RS]: <https://www.tutorialspoint.com/restful/restful_jax_rs.htm>
[Learn More About CXF]: <http://cxf.apache.org/>