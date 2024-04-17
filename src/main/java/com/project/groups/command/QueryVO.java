package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryVO {
    private String korn_flnm;
    private Integer num;
    private String query_type;
}
