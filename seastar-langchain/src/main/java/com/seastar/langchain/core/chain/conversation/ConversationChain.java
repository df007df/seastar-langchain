package com.seastar.langchain.core.chain.conversation;

import com.seastar.langchain.core.chain.LLMChain;
import com.seastar.langchain.core.chain.base.BaseChain;
import com.seastar.langchain.core.memory.BaseMemory;
import com.seastar.langchain.core.memory.buffer.ConversationBufferMemory;
import com.seastar.langchain.core.model.llm.base.BaseLLMModel;
import com.seastar.langchain.core.model.llm.base.BaseLLMResult;
import com.seastar.langchain.core.prompt.base.PromptValue;
import com.seastar.langchain.core.prompt.base.template.BasePromptTemplate;
import com.seastar.langchain.core.prompt.base.variable.BaseVariable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author df007df
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConversationChain<P, R> extends LLMChain<P, R> {

    private static final String DEFAULT_TEMPLATE = "The following is a friendly conversation between a human and an AI. The AI is talkative and provides lots of specific details from its context. If the AI does not know the answer to a question, it truthfully says it does not know." +
            "\n" +
            "Current conversation:\n" +
            "{history}\n" +
            "Human: {input}\n" +
            "AI:";

    public static BasePromptTemplate basePromptTemplate = new BasePromptTemplate(DEFAULT_TEMPLATE, Arrays.asList(BaseVariable.newString("history"), BaseVariable.newString("input")));


    public ConversationChain(BaseLLMModel<P, R> llm) {
        super(llm, basePromptTemplate);
        this.setMemory(new ConversationBufferMemory());
    }

    public ConversationChain(BaseLLMModel<P, R> llm, BaseMemory baseMemory) {
        super(llm, basePromptTemplate);
        this.setMemory(Optional.ofNullable(baseMemory).orElse(new ConversationBufferMemory()));
    }
}
