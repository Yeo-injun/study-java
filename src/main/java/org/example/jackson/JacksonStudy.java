package org.example.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.study.StudyLauncher;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class JacksonStudy implements StudyLauncher {

    @Override
    public void launch() throws IOException {
        ObjectMapper om = new ObjectMapper();
        ResultMap[] resultMap = om.readValue(
                new File( "C:\\dev\\study-java\\src\\main\\java\\org\\example\\jackson\\mapping.json")
                , ResultMap[].class
        );
        Arrays.stream(resultMap).forEach( r -> {
            System.out.println( r.toString() );
        });
//        ResultMap resultMap = om.readValue(
//                new File( "C:\\dev\\study-java\\src\\main\\java\\org\\example\\jackson\\mapping.json")
//                , ResultMap.class
//        );
//        System.out.println( resultMap.toString() );
    }
}
