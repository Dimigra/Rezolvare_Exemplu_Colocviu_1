package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Context;
import android.content.Intent;

public class SlaveThread extends Thread {

    private Context context;
    private boolean isRunning = true;
    private int valueA;
    private int valueB;

    public SlaveThread(Context context, int A, int B) {
        this.context = context;
        valueA = A;
        valueB = B;
    }

    @Override
    public void run() {
        int i = 0;
        while(isRunning) {
            sendMessage(i);
            i = (i + 1) % 3;
            sleep();
        }
    }

    private void sendMessage(int messageType) {
        Intent intent = new Intent();
        intent.setAction(String.valueOf(messageType));
        intent.putExtra("message","dima "+ String.valueOf(messageType) + " " + String.valueOf(valueA) + " " + String.valueOf(valueB));
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread()
    {
        isRunning = false;
    }
}
