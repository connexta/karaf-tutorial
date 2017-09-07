## Part 2 Instructions

#### Create a service provider and register it with Blueprint

Blueprint is component management container. That probably doesn't mean much to you; it doesn't mean much to me. 

Blueprint is used to to create Java objects and publish them to the OSGi service registry. Instead of writing Java code to do this, Blueprint is written in XML. When the OSGi runtime loads a bundle, the Blueprint container looks for the blueprint.xml file and uses that information to construct objects and register them.

###### Create the service provider

1. (Step 1 is optional). [Read more about Blueprint](./step-2-blueprint/README.md)
1. In IntelliJ, create a new module named **impl**
1. In the new module, create a Java class named **GreetingImpl** that implements the **Greeting** interface (Add the api module as a maven dependency so that the class `GreetingImpl` can see the interface `Greeting`).
1. Implement the `hello` method. Copy it from the source code [here](./step-2-blueprint/greeting-impl/src/main/java/com/connexta/impl/GreetingImpl.java)
1. Do not leave the class in the default package. OSGi relies heavily on package names. Use the `package` directive to make the interface part of a real package. For simplicity, use the package name `impl`.
1. Add the maven bundle plugin to the POM. Feel free to copy it from [here](./step-2-blueprint/greeting-impl/pom.xml)
1. Test your work by building the module with `mvn install`

**NOTE:** I had two errors in my `POM.xml` file. 
1. First, I forgot to add `<packaging>bundle</packaging>` to `POM.xml`. The results was the the `MANIFEST.MF` file in the JAR was missing the OSGi headers and the JAR could not be installed 
in Karaf. 
1. Second, I was missing `<extensions>true</extensions>` from the maven bundle plugin definition. The result was 
the maven did not recognize the `bundle` packaging type.
`

###### Create the blueprint specification file

1. Locate the `resources` directory under the `impl/src/main/...` path. 
1. Create a subdirectory of `resources` named `OSGI-INF`. Spelling matters.
1. Create a subdirectory of `OSGI-INF` named `blueprint`. (IntelliJ might annoyingly display the nested directories as `OSGI-INF.blueprint`)
1. Now create a file named `blueprint.xml` in the `blueprint` directory.
1. Open the `blueprint.xml` file and paste in this boilerplate:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<blueprint xmlns="http://www.osgi.org/xmlns/blueprint/v1.0.0">
            
</blueprint>
```

Congratulations! You've succeeded doing busy-work. Gold star for diligence!

###### Define the `GreeterImpl` bean and register the service

A bean is just a Java object. But some Java library referred to as a _container_ decides when to create the object, and when to destroy the object. That is all. 

Tell the Blueprint container to create (and name) and instance of the `GreetingImpl` class. Add this XML snippet in the `<blueprint>` element.
 
```xml
<bean id="greetingImpl" class="impl.GreetingImpl"/>
```

WARNING: The first time I did this, I messed it up. I copied and pasted `class="com.connexta.impl.GreetingImpl"`,
 but my package name was `impl`; I had dropped the `com.connexta` part in my Java code. This lead to 
unsatisfied requirements when I tried to start the packager in Karaf. Karaf was looking for `com.connexta.impl.GreetingImpl` when it should have been looking for `impl.Greeting`.

Blueprint only needs two pieces of information to register a bean: the ID to use for the bean, and the class. Blueprint is more flexible than this. You can specify constructor arguments, default values, and other information. None of this is necessary for this bean.

To turn the bean into an OSGi service, a single line of XML needs to be added:
```xml
<service ref="greetingImpl" interface="api.Greeting"/>
```
Three things going on there:
1. The keyword `service` tells Blueprint to register a service provider
1. The `ref="greetingImpl"` tells Blueprint the `greetingImpl` bean provides (implements) the service.
1. The `interface="com.connexta.api.Greeting"` tells Blueprint that the bean is providing the service defined by the interface `Greeting`. 

The big take aways are:
1. The **service** is the contract or interface.
1. The **bean** is the Java object that implements the interface.
1. There is no limit to number of classes that can implement the service interface and register themselves as service providers.
1. When you consume an OSGi service, OSGi will ultimately hand the bean named `greetingImpl` to the consumer.
1. Regardless of who asks for the service, the requester will always get the same instance of `GreeterImpl`; a service consumer has to share the same object with every other service consumer. Be aware this can cause issues for multi-threaded applications. 
1. Services are not uniquely named. A service has its interface type. A service consumer typically tells the OSGi runtime to "give me all the providers that implement service X". In more advanced use, properties are defined when the service is registered, and the OSGi runtime can be be given a filter that looks at a service provider's properties to decide if the service provider should be handed to a consumer.

### Exercise
- Use `mvn install` to create the bundle
- Start Karaf and use `install` or the _deploy_ folder to start the bundle. 
- The bundle should install successfully, but **fail to start**


- Read the **entire** failure message. What does it tell you? (If you could start the bundle 
successfully, find someone who did get an error message and try to determine why it succeeded for you).
- Use Karaf console commands list `headers` to probe the bundle failure. It's much error to read 
than the debug message on the console. 


- Solve the problem and then start the bundle
- Probe the bundle(s) with the commands `headers` and `capabilities` 


<p>
<p>
<p>
<p>
<p>
<p>
<p>
<p>

ALTERNATE SOLUTION (**spoiler alert**)
- When I coded the solution, I didn't bother to turn the `api` module into a bundle. When I hit 
the error, I solved it a different way.
- Instead, I _embeded_ it in the `impl` bundle. I added the this instruction to the maven bundle
 plugin configuration fro the `impl` module: `<Embed-Dependency>api</Embed-Dependency>`
- The word _embed_ is a bit clue about what this instructions does. If you have time, 
try this out. Unzip the `impl` JAR file and have a look. Can you see what the embed 
instruction changed inside the JAR.