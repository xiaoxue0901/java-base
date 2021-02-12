package com.autumn.demo.javabase.thread.atomic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/12
 * @time 10:28 下午
 * @description
 */
@Slf4j
public class UseAtomicReference {
    static AtomicReference<UseInfo> userRef = new AtomicReference<UseInfo>();

    public static void main(String[] args) {
        UseInfo user = new UseInfo("mark", 15);
        userRef.set(user);
        UseInfo updUser = new UseInfo("mark", 17);
        userRef.compareAndSet(user, updUser);
        log.info("ref name: {}, age: {}", userRef.get().getName(), userRef.get().getAge());
        log.info("name: {}, age:{}", user.getName(), user.getAge());


    }

    @Data
    static class UseInfo{
        private String name;
        private int age;

        public UseInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
