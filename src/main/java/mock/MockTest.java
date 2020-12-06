package mock;

import junit.framework.Assert;
import org.junit.Test;

import java.util.List;
import static org.mockito.Mockito.*;
/**
 * Created by nick on 2020/12/6.
 * mock 测试就是在测试过程中，对于某些不容易构造或者不容易获取的对象，
 * 用一个虚拟的对象来创建以便测试的测试方法。这个虚拟的对象就是mock对象。
 * mock对象就是真实对象在调试期间的代替品。
 */
public class MockTest {
    @Test
    public void myTest() {
        /* 创建 Mock 对象 */
        List list = mock(List.class);
        /* 设置预期，当调用 get(0) 方法时返回 "111" */
        when(list.get(0)).thenReturn("111");

        Assert.assertEquals("asd", 1, 1);
        /* 设置后返回期望的结果 */
        System.out.println(list.get(0));
        /* 没有设置则返回 null */
        System.out.println(list.get(1));

        /* 对 Mock 对象设置无效 */
        list.add("12");
        list.add("123");
        /* 返回之前设置的结果 */
        System.out.println(list.get(0));
        /* 返回 null */
        System.out.println(list.get(1));
        /* size 大小为 0 */
        System.out.println(list.size());

        /* 验证操作，验证 get(0) 调用了 2 次 */
        verify(list, times(2)).get(0);

        /* 验证返回结果 */
        String ret = (String)list.get(0);
        Assert.assertEquals(ret, "111");
    }
}
