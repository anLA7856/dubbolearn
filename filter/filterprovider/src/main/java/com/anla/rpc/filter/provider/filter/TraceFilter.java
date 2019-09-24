package com.anla.rpc.filter.provider.filter;

import com.anla.rpc.filter.provider.api.Dog;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author anLA7856
 * @date 19-7-18 下午11:32
 * @description
 */
@Activate(group = {"provider"})
public class TraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = invoker.invoke(invocation);
        Object object = result.getValue();
        if (object instanceof Dog) {
            Dog dog = (Dog) object;
            dog.setName("traceFilter: " + dog.getName());
        }
        return result;
    }


}
