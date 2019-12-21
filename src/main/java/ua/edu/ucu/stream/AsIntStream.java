package ua.edu.ucu.stream;


import ua.edu.ucu.function.*;
import java.util.ArrayList;
import java.util.Collections;

public class AsIntStream implements IntStream {
    private ArrayList<Integer> array;
    private int length;

    private AsIntStream() {
        array = new ArrayList<>();
        length = array.size();
    }

    public static IntStream of(int... values) {
        AsIntStream intStream = new AsIntStream();
        for (int i : values) {
            intStream.array.add(i);
            intStream.length += 1;

        }
        return intStream;
    }

    private void check() {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Double average() {
        check();
        return (double) sum() / length;

    }

    @Override
    public Integer max() {
        check();
        return Collections.max(array);
    }

    @Override
    public Integer min() {
        check();
        return Collections.min(array);
    }

    @Override
    public long count() {
        return length;
    }

    @Override
    public Integer sum() {
        check();
        int res = 0;
        for (int el : array) {
            res += el;
        }
        return res;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        AsIntStream resulting = new AsIntStream();
        for (int el : array) {
            if (predicate.test(el)) {
                resulting.array.add(el);
                resulting.length += 1;
            }
        }
        return resulting;
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int el : array) {
            action.accept(el);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        AsIntStream resulting = new AsIntStream();
        for (int el : array) {
            int res = mapper.apply(el);
            resulting.array.add(res);
            resulting.length += 1;
        }
        return resulting;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        AsIntStream resulting = new AsIntStream();
        for (int el : array) {
            int[] newStream = func.applyAsIntStream(el).toArray();
            for (int i : newStream) {
                resulting.array.add(i);
                resulting.length += 1;
            }
        }
        return resulting;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
    int left = identity;
    for (int right : array) {
        left = op.apply(left, right);
    }
    return left;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[length];
        int i = 0;
        for (int el : array) {
            result[i] = el;
            i++;
        }
        return result;
    }
}
