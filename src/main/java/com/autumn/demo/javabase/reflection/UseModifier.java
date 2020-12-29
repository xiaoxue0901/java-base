package com.autumn.demo.javabase.reflection;

import com.autumn.demo.javabase.bean.Employee;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/29
 * @time 16:24
 * @description Modifier: 修饰符类
 */
public class UseModifier {

    public static void useModifier() {
        // 获取域"name"对应的Field
        Field field = null;
        try {
            field = Employee.class.getDeclaredField("name");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        int mod = field.getModifiers();
        // 返回对应modifiers中位设置的修饰符的字符串表示
        String modifier = Modifier.toString(field.getModifiers());
        // 下列方法检测方法名中对应的修饰符在modifiers值中的位.
        boolean isAbstract = Modifier.isAbstract(mod);
        boolean isFinal = Modifier.isFinal(mod);
        boolean isInterface = Modifier.isInterface(mod);
        boolean isNative = Modifier.isNative(mod);
        boolean isPrivate = Modifier.isPrivate(mod);
        boolean isProtect = Modifier.isProtected(mod);
        boolean isPublic = Modifier.isPublic(mod);
        boolean isStatic = Modifier.isStatic(mod);
        boolean isStrict = Modifier.isStrict(mod);
        boolean isSynchronized = Modifier.isSynchronized(mod);
        boolean isVolatile = Modifier.isVolatile(mod);
        System.out.println("是否是抽象:"+ isAbstract);
        System.out.println("是否是final:"+ isFinal);
        System.out.println("是否是接口:"+ isInterface);
        System.out.println("是否是私有:"+ isPrivate);
        System.out.println("是否是公共:"+ isPublic);
        System.out.println("是否是静态:"+ isStatic);
    }

}
