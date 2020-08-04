package com.lind.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsBaseFileEntity {

    private String id;

    private String name;

    private String type;
}
