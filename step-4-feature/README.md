# Features

* A feature is a collection of bundles or a collection of features that should be installed together. 
* The bundles will contain all dependencies locally or it will contain a reference to a repository. 
* This automates the gathering of dependencies and saves time. 

## Process:

   1. Execute `mvn install` in the project location to build the feature module and install in the local maven repository. The pom.xml file includes instructions for the maven Karaf plugin. The plugin generates the features file based on information in the pom.xml file. Karaf consumes the features file to know what dependencies to download.
    
  2. Karaf maintains a list of features based on the feature repositories that it knows about. To add the repository that was created during the maven install, type this commands: feature:repo-add mvn:<groupId>/<artifactId>/<version>/xml/features. This feature repository is a maven artifact that is stored in the local maven repository. `feature:repo-add mvn:com.connexta/greeting-feature/1.0-SNAPSHOT/xml/features`
    
  3. To see the available features use the command: `feature:list | grep greeting`. `grep <keyword>` will narrow the results of the list. The first column is the name of the feature, the second column is the version, the fifth is the repository. For example, the greeting-feature repository is "greeting-feature-1.0-SNAPSHOT".
    
  4. To install the feature use the command: `feature:install <feature-name>`. For this example we use: `feature:install greeting-feature`. You can use `display` in Karaf to verify that the feature was installed.
   
