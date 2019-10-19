package com.anla.rpc.filter.consumer.filter;

import com.anla.rpc.filter.provider.api.Dog;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author anLA7856
 * @date 19-7-18 下午11:32
 * @description
 */
@Activate(group = {"consumer"})
public class ConsumerTraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = invoker.invoke(invocation);
        Object object = result.getValue();
        if (object instanceof Dog) {
            Dog dog = (Dog) object;
            dog.setName("consumerFilter: " + dog.getName());
        }
        System.out.println("traceFilter from consumer");
        return result;
    }


}
