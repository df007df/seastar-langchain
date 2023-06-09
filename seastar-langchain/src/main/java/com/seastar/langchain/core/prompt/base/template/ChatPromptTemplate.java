package com.seastar.langchain.core.prompt.base.template;


import com.seastar.langchain.core.model.chat.base.message.BaseChatMessage;
import com.seastar.langchain.core.prompt.base.ChatPromptValue;
import com.seastar.langchain.core.prompt.base.PromptValue;
import com.seastar.langchain.core.prompt.base.variable.BaseVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ChatPromptTemplate extends BasePromptTemplate {

    private List<BaseMessagePromptTemplate> messagePromptTemplates;

    private ChatPromptTemplate(List<BaseMessagePromptTemplate> messagePromptTemplates) {
        super("", null);
        this.messagePromptTemplates = messagePromptTemplates;
    }

    public static ChatPromptTemplate fromMessages(List<BaseMessagePromptTemplate> promptTemplates) {

        return new ChatPromptTemplate(promptTemplates);
    }

    @Override
    public BaseVariable getFirstVariable() {
        return Optional.ofNullable(this.messagePromptTemplates).orElse(new ArrayList<>()).stream().findFirst().map(BaseMessagePromptTemplate::getPromptTemplate).get().getFirstVariable();
    }

    @Override
    public PromptValue formatPrompt() {
        List<BaseChatMessage> chatMessages = Optional.ofNullable(this.messagePromptTemplates).orElse(new ArrayList<>()).stream().map((messagePromptTemplate) -> {
            PromptValue content = messagePromptTemplate.getPromptTemplate().formatPrompt();
            return BaseChatMessage.builder()
                    .role(messagePromptTemplate.getRole())
                    .content(content.getStr())
                    .build();

        }).collect(Collectors.toList());

        return PromptValue.builder().messages(chatMessages).build();
    }

    @Override
    public PromptValue formatPrompt(List<BaseVariable> variables) {

        List<BaseChatMessage> chatMessages = Optional.ofNullable(this.messagePromptTemplates).orElse(new ArrayList<>()).stream().map((messagePromptTemplate) -> {
            PromptValue content = messagePromptTemplate.formatPrompt(variables);
            return BaseChatMessage.builder()
                    .role(messagePromptTemplate.getRole())
                    .content(content.getStr())
                    .build();

        }).collect(Collectors.toList());

        return PromptValue.builder().messages(chatMessages).build();
    }


}
