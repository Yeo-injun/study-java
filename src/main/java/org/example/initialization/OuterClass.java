package org.example.initialization;

public class OuterClass {

    public static String outerField = "static field";
    static {
        System.out.println( "==========================" );
        System.out.println( "OuterClass :: static block" );
        System.out.println( "OuterClass :: " + outerField );
        outerField = "static field changed";
        System.out.println( "==========================" );
    }
    public static void printField() {
        System.out.println( "OuterClass :: static field :: " +  outerField );
    }

    public OuterClass() {
        System.out.println( "OuterClass :: constructor init" );
    }

    public enum InnerEnum {

        PROP( "첫번째 enum" );

        static {
            System.out.println( "InnerEnum :: static block" );
        }

        private String name;
        InnerEnum( String name ) {
            this.name = name;
            System.out.println( "InnerEnum :: constructor init" );
        }
    }
}
