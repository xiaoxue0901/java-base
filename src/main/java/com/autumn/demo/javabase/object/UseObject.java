package com.autumn.demo.javabase.object;

import com.autumn.demo.javabase.bean.Employee;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/30
 * @time 10:20
 * @description Object: 所有类的超类
 */
@Slf4j
public class UseObject {

    public static void useObject() {
        // 可以用Object类型的变量引用任何类型的对象
        Object obj = new Employee("嬴政", 0);
        // 类型转换
        Employee employee = (Employee) obj;
        log.info("实例化对象:{}, string: {}", obj, obj.toString());
        // 基本类型不是对象, 其他的都是对象(数组类型也是对象,无论是基本类型的数组还是对象数组)

        // equals(): 检测一个对象是否等于另外一个对象. 判断两个对象是否具有相同的引用.
        // 子类中定义equals(), 首先调用超类的equals. 检测成功后, 再比较子类中的实例域
        boolean result = obj.equals(employee);

        log.info("2个对象比较后结果:{}", result);

        Object obj2 = new Employee("嬴政2", 5233);
        log.info("obj2:{}, obj2.toString():{}", obj2, obj2.toString());

        // hashCode
        int hashCode = employee.hashCode();

        // 数组的hashCode
        int arrayHashCode = Arrays.hashCode(new int[]{1,2,3,4});

        // toString()
        String emp = employee.toString();
    }

}
