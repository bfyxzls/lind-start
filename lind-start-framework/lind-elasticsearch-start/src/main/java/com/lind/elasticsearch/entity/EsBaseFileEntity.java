package com.lind.elasticsearch.entity;

import com.lind.common.util.SnowFlakeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsBaseFileEntity {

    @Id
    private String id = String.valueOf(SnowFlakeUtils.getFlowIdInstance().nextId());

    private String name;

    private String type;
}
