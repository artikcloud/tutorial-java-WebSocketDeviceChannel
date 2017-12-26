package cloud.artik.example;

import cloud.artik.model.Acknowledgement;
import cloud.artik.model.ActionOut;
import cloud.artik.model.MessageIn;
import cloud.artik.model.MessageOut;
import cloud.artik.model.RegisterMessage;
import cloud.artik.model.WebSocketError;
import cloud.artik.websocket.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


/**
* WebSocket starter code to connect to ARTIK Cloud WebSocket (/websocket) endpoint.
* This app emulates a smart light, which turns on or off based on Actions from 
* ARTIK Cloud and sends the latest state (on or off) back to ARTIK Cloud.
* 
* ARTIK Cloud Java SDK:
* https://github.com/artikcloud/artikcloud-java
* 
* Additional information available in our documentation:
* https://developer.artik.cloud/documentation/api-reference/websockets-api.html
* 
*/

public class WebSocketDeviceChannel {
	
    // Device of "Example Simple Smart Light" (unique name cloud.artik.example.simple_smartlight)
    static private String deviceId     = null;
    static private String deviceToken  = null;

	private static final int MIN_EXPECTED_ARGUMENT_NUMBER = 4;

	static ArrayList<String> queryParams = new ArrayList<String>();
	
	DeviceChannelWebSocket mDeviceChannelWS = null;
	
	private void connectDeviceChannelWS() {
		try {
			mDeviceChannelWS = new DeviceChannelWebSocket(true, new ArtikCloudWebSocketCallback() {
	            @Override
	            public void onOpen(int i, String s) {
	                System.out.println("Registering " + deviceId);
	
	                RegisterMessage registerMessage = new RegisterMessage();
	                registerMessage.setAuthorization("bearer " + deviceToken);
	                registerMessage.setCid("myRegisterMessage");
	                registerMessage.setSdid(deviceId);
	
	                try {
	                	System.out.println("DeviceChannelWebSocket::onOpen: registering" + deviceId);
	                    mDeviceChannelWS.registerChannel(registerMessage);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	
	            @Override
	            public void onMessage(MessageOut messageOut) {
	               System.out.println("DeviceChannelWebSocket::onMessage(" + messageOut.toString());
	            }
	
	            @Override
	            public void onAction(ActionOut actionOut) {
	                System.out.println("DeviceChannelWebSocket::onAction(" + actionOut.toString());
	                
	                //Send back message with random value
	                MessageIn messageIn = new MessageIn();
	                messageIn.sdid(deviceId);
	                messageIn.getData().put("state", true);
	                messageIn.setTs(System.currentTimeMillis());
	                try {
	                    mDeviceChannelWS.sendMessage(messageIn);
	                    System.out.println("DeviceChannelWebSocket sendMessage:" + messageIn.toString());
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }

	            }
	
	            @Override
	            public void onAck(Acknowledgement acknowledgement) {
	                System.out.println("DeviceChannelWebSocket::onAck(" + acknowledgement.toString());
	           }
	
	            @Override
	            public void onClose(int code, String reason, boolean remote) {
	            	System.out.println("WebSocket is closed. code: " + code + "; reason: " + reason);
	
	            }
	
	            @Override
	            public void onError(WebSocketError error) {
	               System.out.println("WebSocket error: " + error.getMessage());
	            }
	
	            @Override
	            public void onPing(long timestamp) {
	            	System.out.println("DeviceChannelWebSocket::onPing: " + timestamp);
	            }
	 
		    });
	    
    		System.out.println(String.format("Connecting to: wss://api.artik.cloud/v1.1/websocket with", deviceToken));
    		
    		System.out.println("With query parameters:");
    		
    		for(String params: queryParams ) {
    			 System.out.println("  "  + params);
    		}
            mDeviceChannelWS.connect();
    				
    		System.out.println("Status: " + mDeviceChannelWS.getConnectionStatus());
        } catch (URISyntaxException|IOException e) {
            e.printStackTrace();
        }

		
	}
	public static void main(String args[]) throws URISyntaxException, IOException {
		
		if (!succeedParseCommand(args)) {
            printUsage();
             return;
        }
		
		WebSocketDeviceChannel ws = new WebSocketDeviceChannel();
		ws.connectDeviceChannelWS();
	}
	
	private static boolean succeedParseCommand(String args[]) {
	       if (args.length < MIN_EXPECTED_ARGUMENT_NUMBER) {
	           return false; 
	       }
	       
	       int index = 0;
	       while (index < args.length) {
	           String arg = args[index];
	           
	           if ("-d".equals(arg)) {
	        	   ++index; // Move to the next argument, value for parameter 
	        	   deviceId = args[index];
	           } else if ("-t".equals(arg)) {
	               ++index; // Move to the next argument, value for parameter
	               deviceToken = args[index];
	           } else {
	        	   return false;
	           }
	           
	           //store as query param format for printing without the '-' flag prefix
	           queryParams.add(arg.substring(1) + "=" + args[index]);
	           
	           ++index;
	       }
	       
	       // must provide a token
	       if (deviceToken == null ) {
	    	   System.out.println("access token null");
	           return false;
	       }
	       
	       //  device (device id) must be set
	       if (deviceId == null) {
	    	   System.out.println("device id null");
	    	   return false;
	       }
	       
	       return true;
	   }
	
	private static void printUsage() {
	       System.out.println("Usage: java -jar websocket-dc-smartlight-x.x.jar" + " -d YOUR_DEVICE_ID -t YOUR_DEVICE_TOKEN");
	       System.out.println("       You must use ID and token of a device of 'Example Simple Smart Light' type (unique name cloud.artik.example.simple_smartlight)");
	}

}

