# Blueprints
The Blueprint Container specification defines a dependency injection framework for OSGI. It is designed
to handle the dynamic nature of OSGI bundles and services which can become available and unavailable
at anytime and allows for different addons to extend functionality (like JPA and CXF). Blueprints are 
written in an XML file located within `OSGI-INF/blueprints` folder of the bundle. By being in a separate 
file than the actual source code, it allows Plain Old Java Objects (POJOs) to be completely decoupled 
from the OSGI framework. Blueprint files declare components and services that are imported and exported 
by the bundle and wire everything together.

# Creating a Blueprint
To create a blueprint to be used in your bundle, you just need to create the following folder structure
in your bundle's resources folder: `OSGI-INF/blueprints`. Within this folder lies all of your blueprints.

## Basic Blueprint Structure
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<blueprint xmlns="http://www.osgi.org/xmlns/blueprint/v1.0.0">
    ...
</blueprint>
```

Inside of the blueprint structure there are a few basic elements to describe different components and
services.

### Common Attributes for all Blueprint elements
* `id`
    * Optional attribute that defines the id for this element that must be unique for this blueprint.
    If not specified it will automatically generate a unique id.
* `activation`
    * Optional attribute defines the activation mode for the element. Only two modes are supported.
    Default value is `eager`
    * `eager` will initialize this component when the blueprint container is initialized.
    * `lazy` will initialze this component when it's needed on demand.
* `dependsOn`
    * Optional attribute that lists the ids that needed to be activated first. These are explicit dependencies,
    implicit are defined by the references used in the component.

### Default blueprint elements
* `bean`
    * Beans are how you initialize new 'object'. They specify the class and arguments to configure it.
    * `class` attribute that specifies the implementation class
    * `arguement` elements pass in arguments to the constructor
    * `property` elements pass in arguments to the getter/setters
```xml
<blueprint>
    <bean id="example" class="com.example.SomeClass">
        <argument ref="constructorArgument"/>
        <property ref="getterSetterArgument"/>
    </bean>
</blueprint>
```
    
* `service`
    * Services export an implementation of an interface to the rest of the OSGI framework.
    * `ref` optional attribute declaring which bean is being exported. If not declared, then the service
    must define the bean inside itself
    * `interface` attribute for which this service exports under
    * `service-properties` element that specifies key-value pairs for this service. Used when filtering.
```xml
<blueprint>
    ...
    <service ref="example" interface="com.example.SomeInterface"/>
    <service interface="com.example.SomeInterface">
        <bean class="com.example.SomeClass"/>
        <service-properties>
            <entry key="key" value="value"/>
        </service-properties>
    </service> 
</blueprint>
```

* `reference`
    * Import a service from the OSGI framework, can add filter parameters to choose a specific type of service.
    * `interface` attribute that declares what interface to import a service for
    * `filter` optional attribute that filters out services using an LDAP filter
```xml
<blueprint>
    <reference id="reference" interface="com.example.SomeInterface"/>
    <reference id="anotherReference" interface="com.example.SomeInterface" filter="(someProperty=someValue)"/>
</blueprint>
```
    
* `reference-list`
    * Import a list of services, can add a filter parameters to choose a specific type of service.
    * `interface` attribute that declares what interface to import the services from
    * `filter` optional attribute that filters out services using an LDAP filter
```xml
<blueprint>
    <reference-list id="listOfServices" interface="com.example.SomeInterface"/>
    <reference-list id="filteredListOfServices" interface="com.example.SomeInterface" filter="(someProperty=someValue)"/>
</blueprint>
```