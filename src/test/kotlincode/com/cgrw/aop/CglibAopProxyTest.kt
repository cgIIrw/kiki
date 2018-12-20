package kotlincode.com.cgrw.aop

import org.junit.Test

import org.junit.Assert.*
import testresources.beans.HelloJDKFather
import testresources.beans.HelloJDKProxy
import testresources.beans.SimpleInterceptor

/**
 * @author: cgrw
 */
class CglibAopProxyTest {

    @Test
    fun intercept() {

        var helloJDKProxy = HelloJDKProxy()
        var aspectJExpressionPointcut = AspectJExpressionPointcut()
        var expression = "execution(* testresources.beans.*.*(..))"
        var advisedSupport = AdvisedSupport()
        var simpleInterceptor = SimpleInterceptor()

        aspectJExpressionPointcut.expression = expression
        var targetSource = TargetSource(helloJDKProxy, *helloJDKProxy.javaClass.interfaces)
        var methodMatcher = aspectJExpressionPointcut.getMethodMatcher()

        advisedSupport.targetSource = targetSource
        advisedSupport.methodMatcher = methodMatcher
        advisedSupport.methodInterceptor = simpleInterceptor

        var CglibAopProxy = CglibAopProxy(advisedSupport).getProxy() as HelloJDKProxy
        CglibAopProxy.helloMethod("Test")
    }
}