package com.seastar.langchain.core.memory;

import com.seastar.langchain.core.model.llm.base.BaseLLMResult;
import com.seastar.langchain.core.prompt.base.variable.BaseVariable;
import lombok.Data;

import java.util.List;

@Data
public abstract class BaseMemory {

    public abstract List<BaseVariable> loadMemoryVariables();

    public abstract void saveContext(List<BaseVariable> baseVariables, BaseLLMResult result);
}
