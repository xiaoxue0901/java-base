package com.autumn.mockito.project;

import com.autumn.demo.javabase.JavaBaseApplication;
import com.autumn.mockito.project.impl.LoginServiceImpl;
import com.autumn.mockito.project.impl.UserServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/21
 * @time 17:42
 * @description Mockito测试: 3. 使用Mockito InjectMocks
 * Spring test默认会重用bean。如果另有一个测试也使用注入的SampleService并在这个测试之后运行，那么拿到service中的dependencyA仍然是mock对象。一般这是不期望的。所以需要用@DirtiesContext修饰上面的测试避免这个问题。

 *
 */
@SpringBootTest(classes = {JavaBaseApplication.class})
@DirtiesContext
@RunWith(SpringRunner.class)
public class LoginService3Test {

    /**
     * MockitoRule的作用是初始化mock对象和进行注入的.。有三种方式做这件事。
     *测试@RunWith(MockitoJUnitRunner.class)
     * 使用rule @Rule public MockitoRule rule = MockitoJUnit.rule();
     * 调用 MockitoAnnotations.initMocks(this)，一般在setup方法中调用
     */
    @Rule public MockitoRule role = MockitoJUnit.rule();
    /**
     * 对应于实现代码中的每个@Autowired字段，测试中可以用一个@Mock声明mock对象
     * ，并用@InjectMocks标示需要注入的对象。
     */
    @Mock UserServiceImpl userService;
    @InjectMocks LoginServiceImpl loginService;

    /**
     * 使用注解
     */
    @Test
    public void login() {
        Mockito.when(userService.getUserName()).thenReturn("灵灵");
        assertEquals("灵灵", loginService.login());
    }

}