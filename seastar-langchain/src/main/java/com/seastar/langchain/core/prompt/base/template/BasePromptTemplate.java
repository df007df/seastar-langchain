package com.seastar.langchain.core.prompt.base.template;

import cn.hutool.core.util.StrUtil;
import com.seastar.langchain.core.model.chat.base.message.AIMessage;
import com.seastar.langchain.core.model.chat.base.message.HumanMessage;
import com.seastar.langchain.core.prompt.base.PromptValue;
import com.seastar.langchain.core.prompt.base.StringPromptTemplate;
import com.seastar.langchain.core.prompt.base.variable.BaseVariable;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author df007df
 */
@Data
public class BasePromptTemplate {

    private List<BaseVariable> inputVariables;

    private StringPromptTemplate template;

    public BasePromptTemplate(String prompt, List<BaseVariable> inputVariables) {
        this.template = new StringPromptTemplate(prompt);
        this.inputVariables = inputVariables;
    }

    public BaseVariable getFirstVariable() {
        return Optional.ofNullable(this.inputVariables).orElse(new ArrayList<>()).stream().findFirst().get();
    }

    @Deprecated
    public PromptValue formatPrompt() {

        return formatPrompt(this.inputVariables);
    }

    public PromptValue formatPrompt(Map<String, Object> maps) {

        List<BaseVariable> variables = new ArrayList<>();
        maps.forEach((key, value) -> {
            variables.add(BaseVariable.builder()
                    .field(key)
                    .value(value)
                    .build());
        });

        return this.formatPrompt(variables);
    }


    public PromptValue formatPrompt(List<BaseVariable> variables) {
        Map maps = Optional.ofNullable(variables).orElse(new ArrayList<>()).stream().collect(Collectors.toMap(BaseVariable::getField, (variable) -> {
            return Optional.ofNullable(variable.getValue()).orElse("");
        }));

        String str = StrUtil.format(this.getTemplate().getPrompt(), maps);
        HumanMessage message = HumanMessage.builder().content(str).build();

        return PromptValue.builder().str(str).messages(
                Arrays.asList(message)
        ).build();
    }


}

