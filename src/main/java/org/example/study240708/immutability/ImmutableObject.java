package org.example.study240708.immutability;

import org.example.study240708.Item;
import org.example.study240708.variabilty.VariableItem;

import java.util.ArrayList;
import java.util.List;

public class ImmutableObject {
    List<Item> immutableItemList;

    public ImmutableObject( List<Item> itemList ) {
        List<Item> newItemList = new ArrayList<>();
        for ( Item i : itemList ) {
            if ( i instanceof VariableItem ) {
                newItemList.add( new VariableItem( i.getStringValue(), i.getIntValue() ) );
            } else {
                newItemList.add( i );
            }
        }
        this.immutableItemList = newItemList;
    }

    public List<Item> getItemList() {
        return immutableItemList;
    }
}
