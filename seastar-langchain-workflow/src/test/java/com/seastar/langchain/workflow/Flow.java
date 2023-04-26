package com.seastar.langchain.workflow;

import com.seastar.langchain.core.chain.LLMChain;
import com.seastar.langchain.core.chain.conversation.ConversationChain;
import com.seastar.langchain.core.memory.ChatMessageHistory;
import com.seastar.langchain.core.memory.buffer.ConversationBufferMemory;
import com.seastar.langchain.core.model.chat.ChatOpenAI;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Slf4j
public class Flow extends SpringBootApplicationTests {

    @Test
    public void run() {


        ConversationBufferMemory memory = new ConversationBufferMemory();

        memory.getChatHistory().addAiMessage("ai messs");
        memory.getChatHistory().addUserMessage("user messs");

        log.info("sdsd: {}", memory.loadMemoryVariables().get(0).getValue());

    }


    @Test
    public void predictTest() {

        ChatOpenAI chatOpenAI = new ChatOpenAI();

        LLMChain<ChatCompletionRequest, ChatCompletionResult> conversationChain = new ConversationChain<>(chatOpenAI);

        conversationChain.predict("hahah");

    }

}
