package com.seastar.langchain.core.memory.buffer;


import cn.hutool.core.collection.CollectionUtil;
import com.seastar.langchain.core.memory.BaseChatMemory;
import com.seastar.langchain.core.model.chat.base.message.BaseChatMessage;
import com.seastar.langchain.core.prompt.base.variable.BaseVariable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author df007df
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConversationBufferWindowMemory extends BaseChatMemory {

    @Builder.Default
    private Integer k = 6;

    public ConversationBufferWindowMemory(Integer k) {
        this.k = k;
    }

    @Override
    public List<BaseVariable> loadMemoryVariables() {

        List<BaseChatMessage> messages = getChatHistory().getMessages();
        messages = Optional.ofNullable(messages).orElse(new ArrayList<>()).stream().skip(CollectionUtil.size(messages) - this.k * 2).collect(Collectors.toList());
        return Arrays.asList(BaseVariable.builder()
                .field(MEMORY_KEY)
                .value(getBufferString(messages))
                .build());
    }


}
