package com.example.codeautosys.service.impl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @项目名: democloud
 * @文件名: BotPool
 * @作者: LuoTianYu
 * @日期:2022/11/28 20:40
 */
public class BotPool extends Thread{



    private final static ReentrantLock reentrantLock = new ReentrantLock(); // 锁
    private final Condition condition = reentrantLock.newCondition();

    private final Queue<Bot> bots = new LinkedList<>();

    public void addBot(Integer userId,String botCode,String input){
        reentrantLock.lock();
        try {
            bots.add(new Bot(userId,botCode,input));
            // 唤醒所有线程 - All 无All - 唤醒一个线程 两者都可以
            condition.signalAll();
        }finally {
            reentrantLock.unlock();
        }
    }
    private void consume(Bot bot){
    //    目前只是支持java代码优化
        Consumer consumer = new Consumer();
        consumer.startTimeout(2000,bot); // 2 s 执行
    }

    @Override
    public void run() {
        // 消息队列
        while (true){
            reentrantLock.lock();

                if(bots.isEmpty()){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        reentrantLock.unlock(); // 自动 会执行
                        break;
                    }
                }else{
                //    如果不是空的  自动将suo资方
                    Bot bot = bots.remove(); // 返回并移除
                    reentrantLock.unlock();

                    // 比较耗时
                    consume(bot);
                }


        }
    }
}
