package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestVO {
    private Integer test_no;
    private String test_input;
    private String test_output;
    private Integer homework_no;
}
