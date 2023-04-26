package com.seastar.langchain.core.model.llm;

import com.seastar.langchain.configuration.OpenAIConfig;
import com.seastar.langchain.core.model.chat.base.message.BaseChatMessage;
import com.seastar.langchain.core.model.llm.base.BaseLLMModel;
import com.seastar.langchain.core.model.llm.base.BaseLLMResult;
import com.seastar.langchain.core.model.llm.base.BaseLLMUsage;
import com.seastar.langchain.core.model.llm.base.BaseOpenAI;
import com.seastar.langchain.core.prompt.base.PromptValue;
import com.seastar.langchain.core.prompt.base.template.BasePromptTemplate;
import com.seastar.langchain.core.prompt.base.variable.BaseVariable;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.List;

/**
 * @author df007df
 */
public class OpenAI extends BaseOpenAI<CompletionRequest, CompletionResult> {

    private CompletionRequest completionRequest;

    public OpenAI() {
        this.completionRequest = new CompletionRequest();
        this.completionRequest.setMaxTokens(500);
        this.completionRequest.setTemperature(0.5);
        this.completionRequest.setN(1);
        this.completionRequest.setModel("text-davinci-003");
    }


    @Override
    public BaseLLMResult<CompletionResult> call(CompletionRequest completionRequest) {

        OpenAiService openAiService = new OpenAiService(OpenAIConfig.apiKey, Duration.ofSeconds(OpenAIConfig.timeOut));

        CompletionResult completionResult = openAiService.createCompletion(completionRequest);
        String text = completionResult.getChoices().get(0).getText();

        BaseLLMUsage usage = BaseLLMUsage.builder()
                .promptTokens(completionResult.getUsage().getPromptTokens())
                .completionTokens(completionResult.getUsage().getCompletionTokens())
                .totalTokens(completionResult.getUsage().getTotalTokens())
                .build();

        return BaseLLMResult.success(text, completionResult, usage);
    }


    @Override
    public BaseLLMResult<CompletionResult> generatePrompt(PromptValue promptValue) {

        this.completionRequest.setPrompt(promptValue.getStr());
        return this.call(this.completionRequest);
    }


}
