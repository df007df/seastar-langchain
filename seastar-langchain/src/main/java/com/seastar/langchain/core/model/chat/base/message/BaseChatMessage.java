package com.seastar.langchain.core.model.chat.base.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseChatMessage {

    private String role;

    private String content;

    private Long tokens;

    public BaseChatMessage(String content) {
        this.content = content;
    }
}
