package kotlincode.com.cgrw.aop

import kotlincode.com.cgrw.ioc.beans.factory.BeanFac
import org.aopalliance.aop.Advice
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import java.lang.reflect.Method

/**
 * @author: cgrw
 **/
class AspectJAroundAdvice : Advice, MethodInterceptor {
    var beanFac: BeanFac? = null
    var aspectJAdviceMethod: Method? = null
    var aspectInstanceName: String? = null

    override fun invoke(invocation: MethodInvocation?): Any {
        return aspectJAdviceMethod!!.invoke(beanFac?.getBean(aspectInstanceName!!), invocation)
    }
}