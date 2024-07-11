package org.example.study240708.immutability;

import org.example.study240708.Item;

public class ImmutableItem implements Item {
    private final String stringValue;
    private final int intValue;

    public ImmutableItem( String stringValue, int intValue ) {
        this.stringValue    = stringValue;
        this.intValue       = intValue;
    }

    @Override
    public String getStringValue() {
        return this.stringValue;
    }

    @Override
    public int getIntValue() {
        return this.intValue;
    }

}
