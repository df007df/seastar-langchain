package com.seastar.aigc.start.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoodsDetail{
    private long id;
    private String name;
}