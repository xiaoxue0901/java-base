package com.autumn.junit4;

import org.junit.*;

/**
 * @author xql132@zcsmart.com
 * @date 2019/10/12 22:24
 * @description 1. Junit4的基础注解
 */
public class Junit4_BasicAnnotationsTest {

    @BeforeClass
    public static void setUpBeforeClass() {
        // 对所有方法执行一次
        //Method annotated with `@BeforeClass` will execute once before any of the test methods in this class.

        //This method could be used to set up any test fixtures that are computationally expensive and shared by several test methods. e.g. establishing database connections

        //Sometimes several tests need to share computationally expensive setup (like logging into a database). While this can compromise the independence of tests, sometimes it is a necessary optimization. From http://junit.sourceforge.net/javadoc/org/junit/BeforeClass.html
    }

    @AfterClass
    public static void tearDownAfterClass() {
        // 对所有方法执行一次
        //Method annotated with `@AfterClass` will execute once after all of the test methods are executed in this class.

        //If you allocate expensive external resources in a BeforeClass method you need to release them after all the tests in the class have run. Annotating a public static void method with @AfterClass causes that method to be run after all the tests in the class have been run. All @AfterClass methods are guaranteed to run even if a BeforeClass method throws an exception. From http://junit.sourceforge.net/javadoc/org/junit/AfterClass.html
    }

    @Before
    public void setUp() {
        // 对每个方法执行一次
        //Method annotated with `@Before` will execute before each test method in this class is executed.

        //If you find that several tests need similar objects created before they can run this method could be used to do set up those objects (aka test-fixtures).
    }

    @After
    public void tearDown() {
        // 对每个方法执行一次
        //Method annotated with `@After` will execute after each test method in this class is executed.

        //If you allocate external resources in a Before method you must release them in this method.
    }

    @Test
    public void test1() {
        //A public void method annotated with @Test will be executed as a test case.
    }

    @Test
    public void test2() {
        // Another test cases
    }
}
