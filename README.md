# Tutorial Java Device Channel WebSocket

Let's build a Java console application that communicates to ARTIK Cloud [device channel WebSocket (/websocket)](https://developer.artik.cloud/documentation/data-management/rest-and-websockets.html##device-channel-websocket) endpoint using ARTIK Cloud Java SDK. This app emulates a smart light, which turns on or off based on Actions from ARTIK Cloud and sends the latest state (on or off) back to ARTIK Cloud.

After completing this sample, you will learn how to:

- establish WebSocket device channel
- receive Actions
- send messages to report the latest state.

## Requirements

- Java version >= 1.8
- Apache Maven >= 3.3.9

## Setup / Installation

### Setup at ARTIK Cloud

 1. At My ARTIK Cloud, [connect a device](https://my.artik.cloud/new_device/cloud.artik.example.simple_smartlight) of the type "Example Simple Smart Light" (unique type name `cloud.artik.example.simple_smartlight`). You can use the one that you already own.
 
 2. Get the [device ID and token](https://developer.artik.cloud/documentation/tools/web-tools.html#managing-a-device-token). You will need them when running the console app (smart light emulator). 

### Setup Java Project

1. Clone this repository if you haven't already done so.

2. At the root directory and run the command:

   ~~~shell
   mvn clean package
   ~~~
   The executable `websocket-dc-smartlight-x.x.jar` is generated under the target directory.

3. Run the command at the target directory to learn the usage:
   ~~~shell
   java -jar websocket-dc-smartlight-x.x.jar
   ~~~

## Demo:

 1. Start and run the following command in the target directory:
    ~~~
    java -jar websocket-dc-smartlight-x.x.jar -d YOUR_DEVICE_ID -t YOUR_DEVICE_TOKEN
    ~~~
    After the channel is successfully estabilished, the app should receive registration acknowledgement as following:
    ~~~
    Connecting to: wss://api.artik.cloud/v1.1/websocket
    Status: CONNECTING
    DeviceChannelWebSocket::onOpen: registering device 81788 with token 830
    DeviceChannelWebSocket::onPing: 1514922438372
    DeviceChannelWebSocket::onAck: class Acknowledgement {
         mid: null
         cid: myRegisterMessage
         message: OK
         code: 200
     }
    ~~~

 2. Send Actions to the emulator using the [web tool](https://developer.artik.cloud/documentation/tutorials/an-iot-remote-control.html#test-the-light) at My ARTIK Cloud.

 3. The emulator terminal should show that Actions are received from the subscribed topic and updated states (on or off) are published back to ARTIK Cloud. The following is the example:
    ~~~shell
    DeviceChannelWebSocket::onAction: class ActionOut {
        data: class ActionDetailsArray {
            actions: [class ActionDetails {
                parameters: {}
                name: setOn
            }]
        }
        type: action
        mid: aab80ef5656a4e3687ebfd7f40586ad4
        ....
    }
    DeviceChannelWebSocket sendMessage: class MessageIn {
        data: {state=true}
        sdid: 81788
        ts: 1514920563273
        type: message
    }
    DeviceChannelWebSocket::onAck: class Acknowledgement {
        mid: aea1fa60d89d4c2c9fae71bf7184577c
    }
    ~~~

## More about ARTIK Cloud

If you are not familiar with ARTIK Cloud, we have extensive documentation at <https://developer.artik.cloud/documentation>

The full ARTIK Cloud API specification can be found at <https://developer.artik.cloud/documentation/api-reference/>

Peek into advanced sample applications at [https://developer.artik.cloud/documentation/tutorials/code-samples](https://developer.artik.cloud/documentation/tutorials/code-samples/)

To create and manage your services and devices on ARTIK Cloud, visit the Developer Dashboard at [https://developer.artik.cloud](https://developer.artik.cloud/)

## License and Copyright

Licensed under the Apache License. See [LICENSE](./LICENSE).

Copyright (c) 2018 Samsung Electronics Co., Ltd.
