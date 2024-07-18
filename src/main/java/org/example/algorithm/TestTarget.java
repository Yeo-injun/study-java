package org.example.algorithm;

public interface TestTarget<I , O> {

    I getInput();
    O getExpected();
    boolean isEqualToExpected( O answer );
}
