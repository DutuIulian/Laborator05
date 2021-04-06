package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import ro.pub.cs.systems.eim.lab05.startedservice.general.Constants;

public class StartedService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constants.TAG, "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constants.TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Constants.TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.TAG, "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "onStartCommand() method was invoked");
        Thread processingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
                while(true) {
                    Intent intent = new Intent();
                    intent.setAction(Constants.ACTION_STRING);
                    intent.putExtra(Constants.DATA, Constants.STRING_DATA);
                    getApplicationContext().sendBroadcast(intent);
                    try {
                        Thread.sleep(Constants.SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent2 = new Intent();
                    intent2.setAction(Constants.ACTION_INTEGER);
                    intent2.putExtra(Constants.DATA, Constants.INTEGER_DATA);
                    getApplicationContext().sendBroadcast(intent2);
                    try {
                        Thread.sleep(Constants.SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent3 = new Intent();
                    intent3.setAction(Constants.ACTION_ARRAY_LIST);
                    intent3.putExtra(Constants.DATA, Constants.ARRAY_LIST_DATA);
                    getApplicationContext().sendBroadcast(intent3);
                    try {
                        Thread.sleep(Constants.SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        processingThread.start();
        return START_REDELIVER_INTENT;
    }
}
