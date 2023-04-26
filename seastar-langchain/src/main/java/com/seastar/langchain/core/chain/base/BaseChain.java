package com.seastar.langchain.core.chain.base;


import com.seastar.langchain.core.memory.BaseMemory;
import com.seastar.langchain.core.model.llm.base.BaseLLMModel;
import com.seastar.langchain.core.model.llm.base.BaseLLMResult;
import com.seastar.langchain.core.prompt.base.variable.BaseVariable;
import lombok.Data;

import java.util.List;


/**
 * @author df007df
 */
@Data
public abstract class BaseChain<P, R> {

    private Boolean verbose = false;

    private BaseLLMModel<P, R> llm;

    private BaseMemory memory;

    protected abstract BaseLLMResult<R> apply(List<BaseVariable> baseVariables);

}
