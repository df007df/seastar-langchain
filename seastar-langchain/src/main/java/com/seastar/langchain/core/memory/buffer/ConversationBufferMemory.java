package com.seastar.langchain.core.memory.buffer;

import com.seastar.langchain.core.memory.BaseChatMemory;
import com.seastar.langchain.core.model.chat.base.message.BaseChatMessage;
import com.seastar.langchain.core.prompt.base.variable.BaseVariable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * @author df007df
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConversationBufferMemory extends BaseChatMemory {

    @Override
    public List<BaseVariable> loadMemoryVariables() {

        List<BaseChatMessage> messages = getChatHistory().getMessages();
        return Collections.singletonList(BaseVariable.builder()
                .field(MEMORY_KEY)
                .value(getBufferString(messages))
                .build());
    }


}
