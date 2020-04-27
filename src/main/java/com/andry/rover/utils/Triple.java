package com.andry.rover.utils;

public class Triple<L, M, R> {

    public final L left;
    public final M middle;
    public final R right;

    public Triple(L left, M middle, R right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public M getMiddle() {
        return middle;
    }

    public R getRight() {
        return right;
    }
}
