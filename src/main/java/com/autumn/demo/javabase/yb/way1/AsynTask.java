package com.autumn.demo.javabase.yb.way1;

/**
 * @author xql132@zcsmart.com
 * @date 2018/9/21 11:35
 * @description 定义异步实现类
 */
public class AsynTask {

    public void task(final MyCallBack myCallBack) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        // 模拟子线程处理时间过长
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int sum = 0;
                    for (int i = 0; i< 100; i++) {
                        sum+=i;
                    }
                // 将结果交给接口的实现类取处理
                myCallBack.callBack(sum);
                System.out.println("调用回调接口之后");
            }
        });

        thread.start();

    }
}
