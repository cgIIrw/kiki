package kotlincode.com.cgrw.aop

import kotlincode.com.cgrw.ioc.beans.BeanPostProcessor
import kotlincode.com.cgrw.ioc.beans.factory.AbsBeanFac
import kotlincode.com.cgrw.ioc.beans.factory.BeanFac
import org.aopalliance.intercept.MethodInterceptor

/**
 * @author: cgrw
 **/
class AspectJAwareAdvisorAutoProxyCreator : BeanPostProcessor, BeanFactoryAware {
    var beanFac: AbsBeanFac? = null

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        return bean
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        if (bean is AspectJExpressionPointcutAdvisor)
            return bean

        if (bean is MethodInterceptor)
            return bean

        var advisors = beanFac!!.getBeansForType(AspectJExpressionPointcutAdvisor::class.java)
                as List<AspectJExpressionPointcutAdvisor>

        advisors.forEach {
            if (it.getPointcut().getClassFilter().matches(bean::class.java)) {
                var advisedSupport = AdvisedSupport()
                advisedSupport.methodInterceptor = it.getAdvice() as MethodInterceptor
                advisedSupport.methodMatcher = it.getPointcut().getMethodMatcher()

                var targetSource = TargetSource(bean, bean::class.java.interfaces as Class<*>)
                advisedSupport.targetSource = targetSource

                return JdkDynamicAopProxy(advisedSupport).getProxy()!!
            }
        }
        return bean
    }

    override fun setBeanFactory(beanFac: BeanFac) {
        this.beanFac = beanFac as AbsBeanFac
    }
}