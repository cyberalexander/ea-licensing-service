# ea-licensing-service
Licensing Microservice of Eagle Eye Application. Under MIT License.


## Building the Docker Image 
To build the code as a docker image, open a command-line window change to the directory where you have downloaded the ea-licensing-service source code.

Run the following maven command. This command will execute the Spotify docker plugin defined in the pom.xml file.

```bash
$ mvn clean package docker:build
```

If everything builds successfully you should see a message indicating that the build was successful.
