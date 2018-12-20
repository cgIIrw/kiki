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
        if (bean is AspectJExpressionPointcutAdvisor) {
//            beanFac!!.applyPropertyValues(bean, beanFac!!.beanDefMap!![beanName]!!)
            return bean
        }

        if (bean is MethodInterceptor)
            return bean

        if (bean is Pointcut)
            return bean

        var advisors = beanFac!!.getBeansForType(AspectJExpressionPointcutAdvisor::class.java)
//                as ArrayList<AspectJExpressionPointcutAdvisor>

        advisors.forEach {
            var it = it as AspectJExpressionPointcutAdvisor
            if (it.getMyPointcut()?.getClassFilter()!!.matches(bean::class.java)) {
                var advisedSupport = AdvisedSupport()
                advisedSupport.methodInterceptor = it.getMyAdvice() as MethodInterceptor
                advisedSupport.methodMatcher = it.getMyPointcut()?.getMethodMatcher()

                var targetSource = TargetSource(bean, *bean::class.java.interfaces)
                advisedSupport.targetSource = targetSource

//                beanFac!!.beanDefMap[beanName]!!.bean = JdkDynamicAopProxy(advisedSupport).getProxy()!!
                beanFac!!.beanDefMap[beanName]!!.bean = CglibAopProxy(advisedSupport).getProxy()!!
                return beanFac!!.beanDefMap[beanName]!!.bean!!
            }
        }
        return bean
    }

    override fun setBeanFactory(beanFac: BeanFac) {
        this.beanFac = beanFac as AbsBeanFac
    }
}