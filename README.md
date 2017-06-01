# Getting started with Karaf
This is a tutorial is for developers new to Apache Karaf. Fundamental Karaf concepts will be introduced, as well
as best practices set by Connexta's developers. If you would like to know something not covered in this tutorial,
you can refer to the [Apache Karaf Documentation] for more information.

# "Hello name!"
This tutorial creates a simple REST service that can be called with a URL and a single parameter (your name): 

`http://localhost:8181/cxf/greeting/hello/{name}`

The service will return a greeting with your name.
For example, `http://localhost:8181/cxf/greeting/hello/joseph` will return "*Hello, joseph!*" in your browser

# Setup
First, clone this repository:
`git clone https://github.com/AzGoalie/connexta-karaf-tutorial/`

Then, `cd` into **connexta-karaf-tutorial** and run `mvn install`. 

This will build the project and place a zip folder here:
**connexta-karaf-tutorial/distribution/target**

Change your directory to **target** and unzip the folder with `unzip distribution-1.0-SNAPSHOT.zip`

This will create a folder with the same name as the zip folder, and it will hold a folder marked **bin**.

Within **bin** are files to run Karaf.

* If you are on Mac or Linux, run Karaf with `./distribution-1.0-SNAPSHOT/bin/karaf`

* If you are on Windows, run Karaf with `distribution-1.0-SNAPSHOT/bin/karaf.bat`

You will then enter the Karaf terminal. Using terminal commands will be covered later, but for now, you can try the 
"Hello name!" REST service in your browser.


[Apache Karaf Documentation]: <http://karaf.apache.org/manual/latest/>