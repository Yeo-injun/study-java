package org.example.algorithm.core;

public interface Testable<I, O> {
    I getInput();
    O getExpected();
    boolean isPass( O answer );
}
