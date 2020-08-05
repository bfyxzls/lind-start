package com.lind.elasticsearch.entity;

import com.lind.common.util.SnowFlakeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsBaseFileEntity {

    @Id
    private String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());

    private String name;

    private String type;
}
