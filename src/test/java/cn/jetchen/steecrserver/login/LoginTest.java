package cn.jetchen.steecrserver.login;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: LoginTest
 * @Description: 登录测试
 * @Author: Jet.Chen
 * @Date: 2019/7/22 11:52
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles(value = "jet")
@Slf4j
public class LoginTest {

    // TODO

    @Test
    public void givenUser_whenGetHomePage_thenOK() {
        Response response = given().auth().basic("admin", "admin")
                .post("http://localhost:7777/login");

        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("Welcome"));
    }
}
