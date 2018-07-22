package com.tuyu.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

/**
 * 分布式锁测试
 *
 * @author tuyu
 * @date 7/22/18
 * Talk is cheap, show me the code.
 */
public class LockUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(LockUtilTest.class);

    private static Jedis jedis = new Jedis();
    private static String lockKey = "lock";
    private static String requestId = "scutuyu-redis-distributed-lock";
    private static int expireTime = 5000;

    /**
     * 尝试获取分布式锁
     */
    @Test
    public void testGetAndReleaseLock() {
        logger.info("测试开始....");
        boolean b = LockUtil.tryGetDistributedLock(jedis, lockKey, requestId, expireTime);
        if (b) {
            System.out.println("加锁成功!---->");
        } else {
            System.out.println("加锁失败!--->");
        }


        boolean b1 = LockUtil.releaseDistributedLock(jedis, lockKey, requestId);
        if (b1) {
            System.out.println("解锁成功!---->");
        } else {
            System.out.println("解锁失败!---->");
        }
        logger.info("测试结结束....");
    }

}