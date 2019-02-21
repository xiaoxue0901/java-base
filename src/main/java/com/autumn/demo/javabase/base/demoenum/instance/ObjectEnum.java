package com.autumn.demo.javabase.base.demoenum.instance;

/**
 * @author xql132@zcsmart.com
 * @date 2019/2/21 15:26
 * @description 枚举的新用法. 可以是枚举实例
 */
public enum ObjectEnum {
    OBJECT{
        @Override
        public User getUser() {
            return new User("李四", "北京西路");
        }
    },OBJECT2{
        @Override
        public User getUser() {
            return new User("小明");
        }
    };

    private User user;

    ObjectEnum() {
        this.user = new User();
    }

    public abstract User getUser() ;
}
