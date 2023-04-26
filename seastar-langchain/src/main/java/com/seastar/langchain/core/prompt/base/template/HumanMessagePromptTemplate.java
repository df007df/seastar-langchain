package com.seastar.langchain.core.prompt.base.template;

public class HumanMessagePromptTemplate extends BaseMessagePromptTemplate {

    public HumanMessagePromptTemplate(BasePromptTemplate promptTemplate) {
        super(promptTemplate);
        this.setRole("user");
    }
}
