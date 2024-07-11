package org.example.study240708.variabilty;

import org.example.study240708.Item;

public class VariableItem implements Item {
    private String stringValue;
    private int intValue;

    public VariableItem(String stringValue, int intValue ) {
        this.stringValue    = stringValue;
        this.intValue       = intValue;
    }

    public void setStringValue( String value ) {
        this.stringValue = value;
    }
    public void setIntValue( int value ) {
        this.intValue = value;
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
