package com.seastar.langchain.core.model.chat.base;

import com.seastar.langchain.core.model.chat.base.message.BatchChatMessage;
import com.seastar.langchain.core.model.llm.base.BaseLLMModel;
import com.seastar.langchain.core.model.llm.base.BaseLLMResult;
import com.seastar.langchain.core.prompt.base.PromptValue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseChatModel<P, R> extends BaseLLMModel<P, R> {


    protected abstract BaseLLMResult<R> generate(BatchChatMessage batchChatMessage);


    @Override
    public BaseLLMResult<R> generatePrompt(PromptValue promptValue) {

        BatchChatMessage batchChatMessage = BatchChatMessage.builder()
                .messages(promptValue.getMessages())
                .build();

        return this.generate(batchChatMessage);
    }


}
