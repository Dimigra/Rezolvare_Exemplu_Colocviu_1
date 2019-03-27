package ro.pub.cs.systems.eim.practicaltest01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Service extends Service {
    SlaveThread processingThread = null;

    public PracticalTest01Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int valueA = intent.getIntExtra("val1", -1);
        int valueB = intent.getIntExtra("val2", -1);
        processingThread = new SlaveThread(this, valueA, valueB);
        processingThread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
