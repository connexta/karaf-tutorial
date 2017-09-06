## Instructions

1. Create new project
   1. Open IDE (IntelliJ)
   1.  File --> New --> Project
   1. Make sure it is a "maven" project and select **Next** 
   1. Enter a group ID (e.g. **com.connexta**)
   1. Enter an artifact ID (e.g. **karaf-turorial**)
   1. Select **Next**
   1. Change project name and location if desired, or just select **Next**
  
1. Add an module for the service contract (i.e.the interface or API)
   1. Add a new module called **api**
   1. Open the module and add a Java `interface` named `Greeting`
   1. Define one method for the interface. The method should be named `hello`, accept a `String` as a parameter and return a `String`
   
1. Update the Project Object Model (POM.xml) for the module
   1. Add the `maven-bundle-plugin` to generate the OSGi manifest headers
   1. For the sake of time, copy and paste this [POM](https://github.com/connexta/karaf-tutorial/blob/master/step-1-bundle/greeting-api/pom.xml) up on GitHub.
   
1. Execute a `mvn install` to build the bundle. Look for warnings and errors in the output.
   


Let's review what we did.
- Created a Java inteface. big whoop
- Added the maven bundle plugin
- Used maven to create the bundle (e.g. `target/api-1.0-SNAPSHOT`)

Let's take a quick look at the instructions to the maven bundle plugin:


```xml
<Bundle-SymbolicName>${project.artifactId}</Bundle-SymbolicName>
<Bundle-Version>${project.version}</Bundle-Version>
<Bundle-Activator/>
<Export-Package>com.connexta.api;version=${project.version}</Export-Package>
<Import-Package> * </Import-Package>
```

The symbolic name and bundle version are just the name and version of the bundle. Together, they make a unique key-- no two bundles can have the same symbolic name and version.

Why is there an empty bundle activator element? No idea. Would it would without it? Probably.

**Export** `Export-Package` is important. In OGSi land, bundles can only see packages inside of their own JAR file, or packages that have been imported from other bundles. Because this is our API, we want the whole world to have access to it. So we export it. Now is is visible inside the runtime OSGi container.

**Import** `Import-Package` can be important. It is great that the Java package for the API was exported. But what about THIS bundle's dependencies? We need to import them. Thanks to the magic of the maven bundle plugin (and, under the covers, BND), we don't have to type out the package names we depend on. The plugin and BND exames the generated _byte code_ and adds the correct manifest headers.

### Exercise
- Unzip the JAR file in the `target/` directory. 
- Find the manifest file
- What instructions from the POM made it into the manifests? Which didn't?
- which instruction could we remove from the POM file and still get a the same manifest headers?