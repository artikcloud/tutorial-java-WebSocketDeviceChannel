# Tutorial Java Device Channel WebSocket

This is a starter code to connect to ARTIK Cloud [Device Channel WebSocket (/websocket)](https://developer.artik.cloud/documentation/data-management/rest-and-websockets.html##device-channel-websocket) endpoint using ARTIK Cloud Java SDK. This code emulates a smart light, which turns on or off based on Actions from ARTIK Cloud and sends the latest state (on or off) back to ARTIK Cloud. By running this sample you will learn to:

- Connect to the ARTIK Cloud Firehose WebSocket url
- Establish WebSocket Device Channel
- Receive Actions 
- Send messages to report the latest state

Consult [An IoT remote control](https://developer.artik.cloud/documentation/tutorials/an-iot-remote-control.html#develop-an-android-app) for how to develop an Android monitor app.

## Requirements

- Java 7 or higher 
- [Apache Maven](https://maven.apache.org/download.cgi)

## Setup

### Setup at ARTIK Cloud

1. At My ARTIK Cloud, [connect a device](https://my.artik.cloud/new_device/cloud.artik.example.simple_smartlight) of the type "Example Simple Smart Light" (unique type name `cloud.artik.example.simple_smartlight`). You can use the one that you already own.

2. Get the [device ID and token](https://developer.artik.cloud/documentation/tools/web-tools.html#managing-a-device-token). You will need them when running the console app (smart light emulator). 

### Setup Java Project

1. Clone this repository if you haven't already done so.

2. At the root directory and run the command:

   ```
   mvn clean package
   ```

   The executable `websocket-dc-smartlight-x.x.jar` is generated under the target directory.

3. Run the command at the target directory to learn the usage:

   ```
   java -jar websocket-dc-smartlight-x.x.jar
   ```

## Demo:

 1. Start and run the following command in the target directory:

```
java -jar websocket-monitor-x.x.jar -d YOUR_DEVICE_ID -t YOUR_USER_TOKEN
```

 2. Send Actions to the emulator using the [web tool](https://developer.artik.cloud/documentation/tutorials/an-iot-remote-control.html#test-the-light) at My ARTIK Cloud.

 3. The emulator terminal should show that Actions are received from the subscribed topic and updated states (on or off) are published back to ARTIK Cloud.

## More about ARTIK Cloud

If you are not familiar with ARTIK Cloud, we have extensive documentation at <https://developer.artik.cloud/documentation>

The full ARTIK Cloud API specification can be found at <https://developer.artik.cloud/documentation/api-reference/>

Peek into advanced sample applications at [https://developer.artik.cloud/documentation/tutorials/code-samples](https://developer.artik.cloud/documentation/tutorials/code-samples/)

To create and manage your services and devices on ARTIK Cloud, visit the Developer Dashboard at [https://developer.artik.cloud](https://developer.artik.cloud/)

## License and Copyright

Licensed under the Apache License. See [LICENSE](./LICENSE).

Copyright (c) 2017 Samsung Electronics Co., Ltd.
