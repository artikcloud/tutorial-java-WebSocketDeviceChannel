# Tutorial Java Firehose WebSocket

This is a starter code to connect to ARTIK Cloud [Firehose WebSocket (/live)](https://developer.artik.cloud/documentation/data-management/rest-and-websockets.html#firehose-websocket) endpoint using ARTIK Cloud Java SDK.  By running this sample you will learn to:

- Connect to the ARTIK Cloud Firehose WebSocket url.
- Monitor realtime messages sent to ARTIK Cloud by the specified source device.

Consult [An IoT remote control](https://developer.artik.cloud/documentation/tutorials/an-iot-remote-control.html#develop-an-android-app) for how to develop an Android monitor app.

## Requirements

- Java 7 or higher 
- ARTIK Cloud Java SDK (version >= 2.1.2)
- [Apache Maven](https://maven.apache.org/download.cgi)

## Setup

### Setup at ARTIK Cloud

1. At My ARTIK Cloud, [connect a device](https://my.artik.cloud/new_device/cloud.artik.example.simple_smartlight) of the type "Example Simple Smart Light" (unique type name `cloud.artik.example.simple_smartlight`). You can use the one that you already own.


2. Get the device ID for your newly created device in the [Device Info](https://developer.artik.cloud/documentation/tools/web-tools.html#managing-a-device-token) screen.
3. Retrieve a [user token](https://developer.artik.cloud/documentation/tools/api-console.html#find-your-user-token) using our api-console, or via oauth2 authentication with your own application. 

### Setup Java Project

1. Clone this repository if you haven't already done so.

2. At the root directory and run the command:

   ```
   mvn clean package
   ```

   The executable `websocket-monitor-x.x.jar` is generated under the target directory.

3. Run the command at the target directory to learn the usage:

   ```
   java -jar websocket-monitor-x.x.jar
   ```

## Demo:

1. Start and run the following command in the target directory:

```
java -jar websocket-monitor-x.x.jar -device YOUR_DEVICE_ID -token YOUR_USER_TOKEN
```

2. Send messages to your Example Simple Smart Light using the [Online Device Simulator](https://developer.artik.cloud/documentation/tools/web-tools.html#using-the-online-device-simulator).   Simulate the device with following settings:

```
* Simulate data on the boolean "state" field with default 5000 ms (5 secs).   
* Alternative between true/false value by setting the data pattern to "alternating boolean".
```

2. In your running sample application you will see the received messages.    Here is the example output:

```bash
Connecting to: wss://api.artik.cloud/v1.1/live?authorization=bearer+aa176...&device=bb101...
Status: CONNECTING ...
Connection successful with code:[101]

Received ping with ts:[1507328677960]

Received message:[class MessageOut {
    data: {state=true}
    cid: null
    ddid: null
    sdid: bb101...
    ts: 1507328710000
    type: message
    mid: ba31d3eb38e342a49226828ff1dac58d
    uid: ff123...
    sdtid: dtd1d3e0934d9348b783166938c0380128
    cts: 1507328699982
    mv: 1
}]

Received message:[class MessageOut {
    data: {state=false}
    cid: null
    ddid: null
    sdid: bb101...
    ts: 1507328715000
    type: message
    mid: 8070d27294f04620a82b389c9e768aab
    uid: ff123...
    sdtid: dtd1d3e0934d9348b783166938c0380128
    cts: 1507328704710
    mv: 1
}]
Received ping with ts:[1507328707969]
```

3. Stop the simulation so it does not accrue any more data usage.

## More about ARTIK Cloud

If you are not familiar with ARTIK Cloud, we have extensive documentation at <https://developer.artik.cloud/documentation>

The full ARTIK Cloud API specification can be found at <https://developer.artik.cloud/documentation/api-reference/>

Peek into advanced sample applications at [https://developer.artik.cloud/documentation/tutorials/code-samples](https://developer.artik.cloud/documentation/tutorials/code-samples/)

To create and manage your services and devices on ARTIK Cloud, visit the Developer Dashboard at [https://developer.artik.cloud](https://developer.artik.cloud/)

## License and Copyright

Licensed under the Apache License. See [LICENSE](./LICENSE).

Copyright (c) 2017 Samsung Electronics Co., Ltd.
