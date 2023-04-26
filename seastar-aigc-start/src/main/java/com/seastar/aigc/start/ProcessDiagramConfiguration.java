package com.seastar.aigc.start;

import cn.kstry.framework.core.component.bpmn.link.ProcessLink;
import cn.kstry.framework.core.component.bpmn.link.StartProcessLink;
import com.seastar.aigc.start.component.GoodsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessDiagramConfiguration {

    @Bean
    public ProcessLink buildShowGoodsLink() {
        StartProcessLink bpmnLink = StartProcessLink.build(ProcessDiagramConfiguration::buildShowGoodsLink, "展示商品详情");
        bpmnLink.nextService(GoodsService::initBaseInfo).build().end();
        return bpmnLink;
    }
}
