package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExVO {
    private Integer ex_no;
    private String input;
    private String output;
    private String ex_ct;
    private Integer homework_no;
}
