package com.seastar.langchain.core.prompt.base.template;

import com.seastar.langchain.core.prompt.base.template.BaseMessagePromptTemplate;
import com.seastar.langchain.core.prompt.base.template.BasePromptTemplate;

public class SystemMessagePromptTemplate extends BaseMessagePromptTemplate {

    public SystemMessagePromptTemplate(BasePromptTemplate promptTemplate) {
        super(promptTemplate);
        this.setRole("system");
    }
}
