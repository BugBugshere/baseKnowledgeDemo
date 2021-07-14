package com.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock {

    //自定义内部AQS实现
    private static class CustomSync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int acquire) {
            //尝试获取资源，立即返回。成功则为true，失败则为false
            assert acquire == 1;//限定量为1
            //CAS自旋比较
            if (compareAndSetState(0, 1)) {
                //设置当前线程抢占
                setExclusiveOwnerThread(Thread.currentThread());
                return true;

            }
            return false;
        }

        @Override
        protected boolean tryRelease(int release) {
            // 尝试释放资源，立即返回。成功则为true，否则false。
            assert release == 1;//限定量为1
            //多重判断
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    private final CustomSync customSync = new CustomSync();


    @Override
    public void lock() {
        customSync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return customSync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return customSync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        customSync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
