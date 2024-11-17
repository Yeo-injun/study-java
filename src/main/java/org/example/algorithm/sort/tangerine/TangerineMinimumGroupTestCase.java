package org.example.algorithm.sort.tangerine;

import org.example.algorithm.core.Testable;

public class TangerineMinimumGroupTestCase implements Testable< Object[], Integer > {

    private final Object[] input;
    private final Integer expected;

    public TangerineMinimumGroupTestCase(Object[] input, Integer expected ) {
        this.input      = input;
        this.expected   = expected;
    }
    @Override
    public Object[] getInput() {
        return this.input;
    }

    @Override
    public Integer getExpected() {
        return this.expected;
    }

    @Override
    public boolean isPass( Integer answer ) {
        return answer.intValue() == expected.intValue();
    }
}
