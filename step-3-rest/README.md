# Web Service

   * Some functionality that can be accessed over the web.
   * Communication is aimed at a program rather than html directed at the user.
  

## How It Works

### Components

1. `@GET` 
    
    * The action the web service performs. In this case it takes in the "name" from the URI.
2. `@PATH`
    
    * The path that the web service will be referencing.
3. `@PRODUCES`

    * The type of output the web service will display. This example will just be plain text.
4. `@PATHPARAM`

    * The parameter taken from the URI that will be used by the program. In this case it is "name".
 
 
 
### Setting Up a Web Service

1. Specify a private Greeting interface variable and pass it into the public constructor as a parameter. 

    * ```java
            private Greeting greetingService;
        
            public GreetingRest(Greeting greetingService) {
                this.greetingService = greetingService;
            } 
       ```
2. Specify JAX-RS standards

    * ```java
        @GET
        @Path("/hello/{name}")
        @Produces(MediaType.TEXT_PLAIN)
        ```
3. Use the given parameter "name" to return and pass it as a parameter into the hello function. This function then returns the the formatted string using "name".
    * ```java
        public String hello(@PathParam("name") String name) {
            return greetingService.hello(name);
        }
        ```