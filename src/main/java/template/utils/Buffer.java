package template.utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Buffer<T> {
    private Deque<T> deque = new ArrayDeque<>();
    private Supplier<T> supplier;
    private Consumer<T> cleaner;

    public Buffer(Supplier<T> supplier) {
        this(supplier, (x) -> { });
    }

    public Buffer(Supplier<T> supplier, Consumer<T> cleaner) {
        this.supplier = supplier;
        this.cleaner = cleaner;
    }

    public T alloc() {
        return deque.isEmpty() ? supplier.get() : deque.removeFirst();
    }

    public void release(T e) {
        cleaner.accept(e);
        deque.addLast(e);
    }
}