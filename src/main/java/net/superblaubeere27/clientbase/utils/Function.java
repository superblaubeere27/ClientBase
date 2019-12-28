package net.superblaubeere27.clientbase.utils;

public abstract class Function<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    public abstract R apply(T t);

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of input to the {@code before} function, and to the
     *           composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     *
     * @see #andThen(Function)
     */
    public <V> Function<V, R> compose(final Function<? super V, ? extends T> before) {
        if (before == null) throw new NullPointerException();

        return new Function<V, R>() {
            @Override
            public R apply(V v) {
                return apply((V) before.apply(v));
            }
        };
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     *
     * @see #compose(Function)
     */
    public <V> Function<T, V> andThen(final Function<? super R, ? extends V> after) {

        return new Function<T,V>() {
            @Override
            public V apply(T t) {
                return after.apply((R) apply(t));
            }
        };
    }

}
