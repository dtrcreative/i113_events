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
//        telegramShedule.action();
//    }

//    The fixedDelay property makes sure that there is a delay of n millisecond between the finish time of an execution of a task and the start time of the next execution of the task.
//    @Scheduled(fixedRate = 10000) // 1000 = 1 seconds
//    public void testRateSheduler() {
//        System.out.println("shedule");
//        telegramShedule.action();
//    }

//    The fixedRate property runs the scheduled task at every n millisecond.
//    @Scheduled(fixedDelay = 1000)
//    public void testDelaySheduler() {
//        System.out.println("shedule");
//    }


}