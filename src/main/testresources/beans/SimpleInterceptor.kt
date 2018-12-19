package testresources.beans

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

/**
 * @author: cgrw
 **/

public class SimpleInterceptor : MethodInterceptor {
    override fun invoke(invocation: MethodInvocation?): Any? {
        println("Before ...")
        var rval = invocation!!.proceed()
        println("After ...")
        return rval
    }
}
