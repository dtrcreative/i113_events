package com.micro.i113_events.service.sheduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Scheduler {

    private final TelegramShedule telegramShedule;

    @Scheduled(cron = "${scheduler.interval-in-cron-evening}")
    public void eveningScheduler() {
        telegramShedule.action();
    }

    @Scheduled(cron = "${scheduler.interval-in-cron-morning}")
    public void morningScheduler() {
        telegramShedule.action();
    }

//    @Scheduled(cron = "${interval-in-cron-test}")
//    public void timedTestSheduler() {
//        action();
//    }

//    @Scheduled(fixedRate = 10000)
//    public void testSheduler() {
//        action();
//    }


}