# Intuit Self Assessment


## Installation

The code include three pieces:

client file and using curl to send a RESTFul.  Download curl and run it.

Java Spring Boot is a proxy that translate RESTFul messages to gRPC messages. Download the code, build.

```bash
$ mvn compile
```

C++ at backend printed logs. Download the source code, build it using cmake.


```bash
$ cd examples/cpp/helloworld
```

Build the code using cmake:
```bash
$ mkdir -p cmake/build
$ pushd cmake/build
$ cmake -DCMAKE_PREFIX_PATH=$MY_INSTALL_DIR ../..
$ make
```

## Usage

### Start C++ server first


```bash
cd cmake/build
./greeter_server

```

Server lisening on 0.0.0.0:50051

### Start Java Spring Boot server
Run Java code in IntelJ.

### Run curl to send a request to Java Spring Boot server
```bash
curl -X POST --data "@intuit.json" -H "Content-Type:application/json" http://localhost:8080/streams
```

## Result
The output file is located at /tmp/myfile.log.

```text
First name: Tony
Last name: Du
DOB: 2000-06-01
Email: example@intuit.com
Phone: 858-831-8743
TimeStamp: 2022-06-06T19:19:43.274707362Z
```

## Contributing
title Intuit example
Client->Java Spring server : RESTFul request
Java Spring server->C++ server:gRPC request
C++ server -> /tmp/myfile.log : Output log 

Java Spring server<-C++ server:gRPC response
Client<-Java Spring server : No content response

Sequence diagram was prepared using https://sequencediagram.org/.

## License
[MIT](https://choosealicense.com/licenses/mit/)