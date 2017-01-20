# spark-playground
Small playground for trying out Spark Java. See develop branch.

## Installation


### Build client sources
The client is built with Angular (aka angular2). angular-cli was used to create the project and it is recommended to use it to build it and start it for development purposes. 
#### 1 Install angular-cli
`` npm install -g angular-cli ``

#### 2 Build
```bash 
cd client

ng build
```

Notice that the built code is located in src/main/resources/client

#### 3 Start the Server
The server can be started by running the main function in com.palmithor.sparkplayground.App
