package com.seastar.aigc.start;

import cn.kstry.framework.core.engine.StoryEngine;
import cn.kstry.framework.core.engine.facade.ReqBuilder;
import cn.kstry.framework.core.engine.facade.StoryRequest;
import cn.kstry.framework.core.engine.facade.TaskResponse;
import com.seastar.aigc.start.dto.GoodsDetail;
import com.seastar.aigc.start.dto.GoodsDetailRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private StoryEngine storyEngine;

    @PostMapping("/show")
    public GoodsDetail showGoods(@RequestBody GoodsDetailRequest request) {

        StoryRequest<GoodsDetail> req = ReqBuilder.returnType(GoodsDetail.class).startProcess(ProcessDiagramConfiguration::buildShowGoodsLink).request(request).build();
        TaskResponse<GoodsDetail> fire = storyEngine.fire(req);
        if (fire.isSuccess()) {
            return fire.getResult();
        }
        return null;
    }
}
