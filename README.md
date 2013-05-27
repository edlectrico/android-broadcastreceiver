android-broadcastreceiver
=========================

Several problems trying to make this BroadcastReceiver work in Android drove me to upload this example. 

It is quite simple: 

1. There is a BroadcastReceiver which is waiting for a RECEIVE_ON_BOOT system command. 
2. Once it receives it launches a background service. 
3. Then we can launch our own application and communicate with the service via Intent.
