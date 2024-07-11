package org.example.study240708;

import org.example.study.StudyLauncher;
import org.example.study240708.immutability.ImmutableItem;
import org.example.study240708.immutability.ImmutableObject;
import org.example.study240708.variabilty.VariableItem;


import java.util.ArrayList;
import java.util.List;

public class StudyImmutability implements StudyLauncher {

    @Override
    public void launch() {
        Item item1 = new ImmutableItem( "Immutable Value", 1 );
        Item item2 = new VariableItem( "Variable Value2", 2 );

        List<Item> itemList = new ArrayList<>();
        itemList.add( item1 );
        itemList.add( item2 );

        ImmutableObject object = new ImmutableObject( itemList );

        VariableItem variableItem = ( VariableItem ) item2;
        variableItem.setStringValue( "Is This Immutable?" );
        variableItem.setIntValue( 9 );

        Item newItem = new ImmutableItem( "newImmutable Item", 3 );
        itemList.add( newItem );

        List<Item> immutableList = object.getItemList();
        System.out.println( "-----------------------------------------------" );
        System.out.println( "> 1-1. List의 Item중 불변객체의 불변여부 확인 " );
        System.out.println( immutableList.get( 0 ).getStringValue() );
        System.out.println( immutableList.get( 0 ).getIntValue() );
        System.out.println( "-----------------------------------------------" );

        System.out.println( "-----------------------------------------------" );
        System.out.println( "> 1-2. List의 Item중 가변객체의 불변여부 확인 " );
        System.out.println( immutableList.get( 1 ).getStringValue() );
        System.out.println( immutableList.get( 1 ).getIntValue() );
        System.out.println( "-----------------------------------------------" );

        System.out.println( "-----------------------------------------------" );
        System.out.println( "> 2. List 자체의 불변성이 보장되는지 확인 " );
        if ( immutableList.size() == 2 ) {
            System.out.println( "List 자체의 불변성이 보장된다. ImmutableObject 생성시 인자로 넘긴 List에 새로 추가한 객체가 존재하지 않음." );
        } else {
            Item getNewItem = immutableList.get( 2 );
            System.out.println( getNewItem.getStringValue() );
            System.out.println( getNewItem.getIntValue() );
        }
        System.out.println( "-----------------------------------------------" );

    }
}
