# Distribution

* Apache Karaf is a lightweight, easy-to-install container
* Containers can be run using the distibutions created from `mvn install`
* Distributions are available in .zip and .tar.gz filetypes

## How To Run Karaf With Distributions
This example is very similar to the setup shown for the "Hello, name!" service. Here, we will create a distribution using the resources in this folder. This distibution will contain preinstalled features/bundles/configurations.

* Run `mvn install` This will create a folder called **distribution**.
* Change your directory to the newly created **distribution/target**  and unzip the folder **distribution-1.0-SNAPSHOT.zip**
* This will create a folder with the same name as the zip folder, and it will hold a folder marked **bin**. 
* Within **bin** are files to run Karaf. 
    - If you are on Mac or Linux, run Karaf with `./distribution-1.0-SNAPSHOT/bin/karaf`
    - If you are on Windows, run Karaf with `distribution-1.0-SNAPSHOT/bin/karaf.bat`
    
Running karaf will bring up the Karaf terminal.

[Learn More]

[Learn More]: <https://karaf.apache.org/manual/latest/#_installation>