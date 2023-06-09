package com.seastar.langchain.core.prompt.base.template;

import com.seastar.langchain.core.prompt.base.PromptValue;
import com.seastar.langchain.core.prompt.base.variable.BaseVariable;
import lombok.Data;

import java.util.List;


@Data
public class BaseMessagePromptTemplate {

    private String role;

    private BasePromptTemplate promptTemplate;

    public BaseMessagePromptTemplate(BasePromptTemplate promptTemplate) {
        this.setPromptTemplate(promptTemplate);
    }

    public PromptValue formatPrompt(List<BaseVariable> variables) {

        return promptTemplate.formatPrompt(variables);
    }
}
