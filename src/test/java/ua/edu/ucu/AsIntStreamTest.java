package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class AsIntStreamTest {
    private IntStream strm;

    @Before
    public void init() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        strm = AsIntStream.of(arr);
    }

    @Test
    public void testOf() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        IntStream expResult = AsIntStream.of(arr);
        assertNotEquals(expResult, strm);
    }

    @Test
    public void testAverage() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Double expResult = 5.5;
        assertEquals(expResult, AsIntStream.of(arr).average());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyStream() {
        int[] arr = {};
        Double expResult = AsIntStream.of(arr).average();
    }

    @Test
    public void testMax() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer expResult = 10;
        assertEquals(expResult, AsIntStream.of(arr).max());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyStream() {
        int[] arr = {};
        Integer expResult = AsIntStream.of(arr).max();
    }

    @Test
    public void testMin() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer expResult = 1;
        assertEquals(expResult, AsIntStream.of(arr).min());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyStream() {
        int[] arr = {};
        Integer expResult = AsIntStream.of(arr).min();
    }

    @Test
    public void testCount() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long expResult = 10;
        assertEquals(expResult, AsIntStream.of(arr).count());
    }

    @Test
    public void testSum() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer expResult = 55;
        assertEquals(expResult, AsIntStream.of(arr).sum());
    }

    @Test
    public void testFilter() {
        int[] arr = {-2, -1, 0, 1, 2};
        int[] expResult = {-2, -1, 0};
        int[] actResult = AsIntStream.of(arr).filter(x -> x <= 0).toArray();
        assertArrayEquals(expResult, actResult);
    }

    @Test
    public void testMap() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expResult = {2, 3, 4, 5, 6};
        int[] actResult = AsIntStream.of(arr).map(x -> x + 1).toArray();
        assertArrayEquals(expResult, actResult);
    }

    @Test
    public void testForEach() {
        StringBuilder str = new StringBuilder();
        strm.forEach(x -> str.append(x));
        String actResult = str.toString();
        String expResult = "12345678910";
        assertEquals(expResult, actResult);
    }

    @Test
    public void testFlatMap() {
        int[] arr = {1, 2};
        int[] expResult = {2, 1, 2, 4, 2, 3};
        int[] actResult = AsIntStream.of(arr).flatMap(x -> AsIntStream.of(x * 2, x, x + 1)).toArray();
        assertArrayEquals(expResult, actResult);
    }

    @Test
    public void testReduce() {
        int[] arr = {1, 2, 3, 4, 5};
        int expResult = 16;
        int actResult = AsIntStream.of(arr).reduce(1, (sum, x) -> sum += x);
        assertEquals(expResult, actResult);
    }

    @Test
    public void testToArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] actResult = AsIntStream.of(arr).toArray();
        assertArrayEquals(arr, actResult);
    }




}
