package com.seastar.aigc.start;

import cn.kstry.framework.core.engine.StoryEngine;
import cn.kstry.framework.core.engine.facade.ReqBuilder;
import cn.kstry.framework.core.engine.facade.StoryRequest;
import cn.kstry.framework.core.engine.facade.TaskResponse;
import com.seastar.aigc.start.dto.GoodsDetail;
import com.seastar.aigc.start.dto.GoodsDetailRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = KstryDemoApplication.class)
@Slf4j
public class Demo {

    @Resource
    private StoryEngine storyEngine;

    @Test
    public void run() {

        GoodsDetailRequest request = new GoodsDetailRequest();
        request.setId(12);

        StoryRequest<GoodsDetail> req = ReqBuilder.returnType(GoodsDetail.class).startProcess(ProcessDiagramConfiguration::buildShowGoodsLink).request(request).build();
        TaskResponse<GoodsDetail> fire = storyEngine.fire(req);
        if (fire.isSuccess()) {
            log.info("getResult: {}", fire.getResult());
        }

        log.error("hahah");
    }


}
