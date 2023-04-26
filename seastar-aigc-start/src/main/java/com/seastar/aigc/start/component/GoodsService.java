package com.seastar.aigc.start.component;

import cn.kstry.framework.core.annotation.NoticeResult;
import cn.kstry.framework.core.annotation.ReqTaskParam;
import cn.kstry.framework.core.annotation.TaskComponent;
import cn.kstry.framework.core.annotation.TaskService;
import com.seastar.aigc.start.dto.GoodsDetail;
import com.seastar.aigc.start.dto.GoodsDetailRequest;

@TaskComponent
public class GoodsService {

    @NoticeResult
    @TaskService
    public GoodsDetail initBaseInfo(@ReqTaskParam(reqSelf = true) GoodsDetailRequest request) {
        return GoodsDetail.builder().id(request.getId()).name("商品").build();
    }
}

