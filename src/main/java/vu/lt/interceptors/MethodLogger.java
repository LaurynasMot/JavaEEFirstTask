package vu.lt.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@LoggedInvocation
public class MethodLogger implements Serializable {
    @AroundInvoke
    public Object logMethodInvocation(InvocationContext invocationContext) throws Exception {
        System.out.println("Method called: " + invocationContext.getMethod().getName());
        return invocationContext.proceed();
    }
}
