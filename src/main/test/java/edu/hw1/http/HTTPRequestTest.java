package edu.hw1.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPRequestTest {
    private final static HTTPRequest connect = new HTTPRequest();
    //ip тестить точно нет смысла, не будет работать в другой сети

    @Test
    @DisplayName("Проверка запроса на user-agent")
    void testThatCheckUserAgentConnectReturnedSucceed() {
        String page = "https://httpbin.org/user-agent";

        String res = connect.getAnswer(page);

        String EXPECTED_VALUE = "Java-http-client/21.0.2";
        assertEquals(EXPECTED_VALUE, res);
    }

    @Test
    @DisplayName("Проверка запроса на anything")
    void testThatCheckAnythingConnectReturnedSucceed() {
        String page = "https://httpbin.org/anything";

        String res = connect.getAnswer(page);

        String EXPECTED_VALUE = "Returns anything that is passed to request";
        assertEquals(EXPECTED_VALUE, res);
    }

    @Test
    @DisplayName("Вывода всех хедеров из запроса")
    void testThatCheckHeadersConnectReturnedSucceed() {
        String page = "https://httpbin.org/headers";

        String res = connect.getAnswer(page);

        String EXPECTED_VALUE = "Accept, Content-Type, Host, User-Agent, X-Amzn-Trace-Id";
        assertEquals(EXPECTED_VALUE, res);
    }

    @Test
    @DisplayName("Проврка вывода хоста при запросе /get")
    void testThatCheckGetConnectReturnedSucceed() {
        String page = "https://httpbin.org/get";

        String res = connect.getAnswer(page);

        String EXPECTED_VALUE = "httpbin.org";
        assertEquals(EXPECTED_VALUE, res);
    }
}
