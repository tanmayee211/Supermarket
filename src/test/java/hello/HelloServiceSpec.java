package hello;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yashawant and Tanmayee on 28/7/15.
 */
public class HelloServiceSpec {
    @Test
    public void it_shouldReturnHello(){
        HelloService helloService = new HelloService();
        String actual = helloService.getHello("cartName1");
        Assert.assertEquals("cartName1 Not Available", actual);
    }
}
