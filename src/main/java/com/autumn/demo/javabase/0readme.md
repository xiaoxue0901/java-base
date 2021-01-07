 javabase包的说明
 ========
## operator
1. `C5Operator`  运算符: 前置加加, 先加后用; 后置加加, 先用后加.
2. `C8ControlProcess` 控制流程: while, break, continue, 等用法.

## enumerate
1. `com.autumn.demo.javabase.enumerate.base 包`
    - 1.1 FoodEnum: 包含一个参数枚举类
    - 1.2 RainBowEnum: 不包含参数的枚举类
    - 1.3 FoodEnumTest: 枚举类的用法
```java
   @Slf4j
   public class FoodEnumTest {
   
       @Test
       public void values() {
           // values(): 返回包含全部枚举值的数组
           FoodEnum[] foodEnums = FoodEnum.values();
           for (FoodEnum food : foodEnums) {
               // toString(): 返回枚举常量名(逆方法:valueOf); ordinal(): enum声明中枚举常量的值
               log.info("food name:{} , ordinal:{}", food.toString(), food.ordinal());
           }
   
       }
   
       @Test
       public void valueOf() {
           // 返回指定名字, 给定类的枚举常量
           FoodEnum foodEnum = FoodEnum.valueOf("RICE");
           FoodEnum foodEnum1 = Enum.valueOf(FoodEnum.class, "RICE");
           log.info("FoodEnum.valueOf() :{}" , foodEnum.toString());
           log.info("Enum.valueOf() :{}" , foodEnum1.toString());
   
       }
   
       @Test
       public void ordinal() {
           // enum中枚举常量的位置, 从0计数
           log.info("RICE ordinal: {}", FoodEnum.RICE.ordinal());
       }
   
       @Test
       public void compareTo() {
           int res = FoodEnum.RICE.compareTo(FoodEnum.RICE);
           log.info("res (-1: 枚举常量RICE出现在'肉'之前; 0: RICE==other; 正值: RICE的ordinal>肉的ordinal):{}", res);
       }
   } 
   ```
2. `com.autumn.demo.javabase.enumerate.advanced`
* Color: 定义接口
* RainBow2Enum: 实现Color接口的枚举类, 枚举类可以实现自定义方法
```java
public enum RainBow2Enum implements Color {
    /**
     * 彩虹颜色枚举
     */
    RED(0, "彩虹-红色"),
    ORANGE(1, "彩虹-橙色"),
    YELLEOW(2, "彩虹-黄色"),
    GREEN(3, "彩虹-绿色"),
    BLUE(4, "彩虹-蓝色");

    RainBow2Enum(int flag, String name) {
        this.flag = flag;
        this.name = name;
    }

    private int flag;
    private String name;

    @Override
    public String getRainBowColor() {
        System.out.println("彩虹的标记是::" + flag + "颜色是::" + name);
        return name;
    }

}
```

3. `com.autumn.demo.javabase.enumerate.instance`
* ObjectEnum: 枚举实例
* SingletonEnum : 用枚举实现单例模式, 原理见: [为什么用枚举实现单例模式](https://www.cnblogs.com/chiclee/p/9097772.html)
总结2点:
    1. 枚举类反编译后是static final 修饰的, jdk的类加载器会保证唯一性
    2. 避免了反射和序列化的问题.
    3. 心得-定义 User类可以用new User();获取很多不同的实例对象. 枚举是列举出有穷序列集.
    4. 定义枚举类, 里面只列举出一个值, 此值代表一个User类的实例. 通过getInstance()方法可以获取到INSTANCE的值: 一个User类的实例.
    5. Enum: 自由序列化, 线程安全, 保证单例
    6. Enum是由class实现的->enum作为一个类来实现单例;
    7. Enum是通过继承Enum类实现的, enum不能作为子类继承其他类.也不能被继承, 是final修饰的. 但是可以用来实现接口
    8. Enum有且仅有private的构造器. 防止外部的额外构造.

```java
public enum SingletonEnum {
    /**
     * 1. 理解: INSTANCE是DemoEnum的实例.
     */
    INSTANCE;

    /**
     * 2. 定义INSTANCE包含参数User.*.
     */
    private User instance;

    /**
     * 3. 枚举类的私有构造方法. 默认是private的.
     */
    SingletonEnum() {
        instance = new User();
    }

    /**
     * 4. 获取枚举类的具体参数user
     */
    public User getInstance() {
        return instance;
    }
}
```

## reflection: 反射
`Class, Field, Method,Constructor,Modifier, Array的用法`

## object
1. equals(): 步骤如下
 ```java
@Override
     public boolean equals(Object o) {
         // 1. 比较两个对象的存储地址.this就是this.hashCode()的值.
         if (this == o) return true;
         // 2. o对象非空, 并且要比较的两个对象是同一个类产生的对象
         if (o == null || getClass() != o.getClass()) return false;
         // 3. 比较2个对象的每个实例域是否相等.
         Employee employee = (Employee) o;
         return Double.compare(employee.salary, salary) == 0 &&
                 name.equals(employee.name) &&
                 hireDay.equals(employee.hireDay);
     }
 ```
  
2. hashCode():Object对象是默认存储地址.
3. toString(): Object对象默认: 类名+@+hashCode(), 重写toString(): 类名加各个域的命名+域值拼接而来.
```java
public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
```

## interface
`接口不是类, 而是对类的一组需求描述, 这些类要遵从接口描述的统一格式进行定义`
特点:
1. 没有实例域. 不能实现方法.
2. 可以声明接口的变量
3. 接口可以继承接口, 类可以实现多个接口.

`接口与回调`
`定义: 回调是一种常见的程序设计模式, 这种模式中, 可以指出某个特定事件发生时应该采取的动作.`

## set : 集合
### Queue接口的作用
1. 可以在队列的尾部添加元素,
2. 在队列的头部删除元素, 
3. 可以查找队列中元素的个数.
4. 按照"先进先出"的规则检索对象

### 队列的实现
1. 使用循环数组: ArrayDeque; 优点:效率比链表实现高; 缺点: 循环数组是有界集合, 即容量有限.
2. 使用链表: LinkedList
3. 自定义实现Queue接口, 推荐使用实现AbstractQueue

集合类基本接口:Collection
===
特点
1.不允许出现重复的对象.

迭代器: Iterator接口
===

Iterable
===





