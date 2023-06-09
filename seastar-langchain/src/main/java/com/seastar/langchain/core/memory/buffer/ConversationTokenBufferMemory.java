package com.seastar.langchain.core.memory.buffer;

import com.seastar.langchain.core.memory.BaseChatMemory;
import com.seastar.langchain.core.model.chat.base.message.BaseChatMessage;
import com.seastar.langchain.core.model.llm.base.BaseLLMResult;
import com.seastar.langchain.core.model.llm.base.BaseOpenAI;
import com.seastar.langchain.core.prompt.base.variable.BaseVariable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author df007df
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConversationTokenBufferMemory extends BaseChatMemory {

    private Integer maxTokenLimit = 2000;

    private BaseOpenAI llm;

    public ConversationTokenBufferMemory(BaseOpenAI llm, Integer maxTokenLimit) {
        this.maxTokenLimit = maxTokenLimit;
        this.llm = llm;
    }

    protected List<BaseChatMessage> getBuffer() {
        return this.getChatHistory().getMessages();
    }


    @Override
    public List<BaseVariable> loadMemoryVariables() {

        if (this.getReturnMessages()) {

            return Collections.singletonList(BaseVariable.builder()
                    .field(MEMORY_KEY)
                    .value(this.getBuffer())
                    .build());
        } else {

            return Collections.singletonList(BaseVariable.builder()
                    .field(MEMORY_KEY)
                    .value(getBufferString(this.getChatHistory().getMessages()))
                    .build());
        }

    }

    @Override
    public void saveContext(List<BaseVariable> baseVariables, BaseLLMResult result) {

        super.saveContext(baseVariables, result);

        List<BaseChatMessage> messages = this.getBuffer();

        Long sum = this.llm.getNumTokensFromMessages(messages);

        if (sum > this.maxTokenLimit) {

            List<BaseChatMessage> prunedMemory = new ArrayList<>();

            while (sum > this.maxTokenLimit) {
                prunedMemory.add(this.getBuffer().remove(0));
                sum = llm.getNumTokensFromMessages(this.getBuffer());
            }
        }
    }


}
