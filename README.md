android-broadcastreceiver
=========================

Several problems trying to make this BroadcastReceiver work in Android drove me to upload this example. It is quite simple: There is a BroadcastReceiver which is waiting for a RECEIVE_ON_BOOT system command. Once it receives it launches a background service. Then we can launch our own application and communicate with the service via Intent.
