package com.autumn.demo.javabase.operator;

/**
 * @author xql132@zcsmart.com
 * @date 2019/10/16 20:23
 * @description 控制流程
 */
public class C8ControlProcess {
    public static void breakProcess_break() {
        // 语句 break
        int i = 0;
        while (true) {
            i = +1;
            if (i == 14) {
                break;
            }
        }

        // 带标签的break语句
        a:
        for (; ; ) {
            ++i;
            if (i > 20) {
                while (true) {
                    if (i == 32) {
                        break a;
                    }
                }
            }
        }

        // 语句continue:
        int j = 21;
        while (true) {
            --j;
            if (j % 2 == 0) {
                continue;
            } else {
                break;
            }
        }

        // 带标签语句continue:
        b:
        for (;;) {
            while (true) {
                --j;
                if (j % 2 == 1) {
                    continue b;
                }
            }
        }
    }

}

