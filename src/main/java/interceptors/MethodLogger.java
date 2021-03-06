package interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@Logged
public class MethodLogger implements Serializable{
    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        System.out.println("MYPREFIX | Called method: " + context.getMethod().getName());
        return context.proceed();
    }
}
