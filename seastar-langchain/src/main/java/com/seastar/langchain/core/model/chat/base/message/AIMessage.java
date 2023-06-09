package com.seastar.langchain.core.model.chat.base.message;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AIMessage extends BaseChatMessage {

    @Builder.Default
    private String role = "assistant";
}
