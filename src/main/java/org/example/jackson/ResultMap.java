package org.example.jackson;

import lombok.*;

import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultMap {
    private String name;
    private String contents;
    private List<ResultMap> children;
}
