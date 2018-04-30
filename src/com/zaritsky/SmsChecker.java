package com.zaritsky;

public class SmsChecker implements Observer {
    @Override
    public void update(Object subject, Object arg) {
        System.out.println("Start service of sms-tracking");
    }
}
