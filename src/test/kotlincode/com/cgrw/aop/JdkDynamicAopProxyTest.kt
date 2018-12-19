package kotlincode.com.cgrw.aop

import org.junit.Test

import testresources.beans.HelloJDKFather
import testresources.beans.HelloJDKProxy
import testresources.beans.SimpleInterceptor

/**
 * @author: cgrw
 */
class JdkDynamicAopProxyTest {

    @Test
    fun getProxy() {
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

        var jdkDynamicAopProxy = JdkDynamicAopProxy(advisedSupport).getProxy() as HelloJDKFather
        jdkDynamicAopProxy.helloMethod(5)
    }
}