## Part 3 Instructions

### Turn the OSGi service into a web service

Does the `GreeterImpl` correctly provide the `Greeter` service? Who knows? Let's connect 
the Greeter service to a web server to create an endpoint that allows HTTP requests 
to invoke the service.

#### Catch up on your reading
- [Read the REAMDE](./step-3-rest/README.md). It is chock-full of goodness.

#### Use annotations to create the web service

Add the JAX-RS annotations to `hello` method to make it a web service. (You will also need 
to add new dependencies to the POM file so that the annotations are recognized)
1. `@GET`
1. `@Path("/hello/{name}")`
1. `@Produces(MediaType.TEXT_PLAIN)`
   
##### Exercise
   - Turn to a neighbor and explain what each of the three JAX-RX annotations is supposed to do.
   
#### Update the blueprint specification
Add Blueprint instructions to `blueprint.xml` that create the web server
- Update the `<blueprint>` element's attributes to include some new XML namespaces. The new 
namespaces are `cxf` (the application server) and `jaxrs` the annotations. The `blueprint` tag
should look like:

```xml
 <blueprint xmlns="http://www.osgi.org/xmlns/blueprint/v1.0.0"
            xmlns:cxf="http://cxf.apache.org/blueprint/core"
            xmlns:jaxrs="http://cxf.apache.org/schemas/jaxrs.xsd">
```
- Add an element to create the something called the _bus_". I _think_ this is the transport layer
that all of your CXF applications (servers) will use to communicate using HTTP.

```xml
    <cxf:bus id="greetingServiceBus">
        <cxf:features>
            <cxf:logging/>
        </cxf:features>
    </cxf:bus>
```   
   
- Register the _bean_ that provides the _service_ with the application:

```xml
<jaxrs:server address="/greeting" id="greetingRest">
        <jaxrs:serviceBeans>
            <ref component-id="greetingImpl"/>
        </jaxrs:serviceBeans>
    </jaxrs:server> 
```


<hr>
NOTE TO SELF: I don't think we need the <servcie ref...> directive at this point. We aren't really
using an OSGi service lookup anywhere. We needed it in the original Karaf tutorial because we 
created a class called `GreetingRest` that needed the service. We injected the service provider
into `GreetingRest` with Blueprint, in the directive `<reference id="greetingService" interface="com.connexta.api.Greeting"/>`. Mabye change up the tutorial to first create and use multiple providers **then** expose
all that as a webservice?

##### Exercise
- What do you think would happen if you built the bundle and installed in Karaf?
- Try it and find out.
  