package com.seastar.langchain.core.prompt.base;

import com.seastar.langchain.core.model.chat.base.message.BaseChatMessage;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class PromptValue {

    private String str;

    private List<BaseChatMessage> messages;

}
