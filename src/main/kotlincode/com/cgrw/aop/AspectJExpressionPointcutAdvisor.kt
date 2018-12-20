package kotlincode.com.cgrw.aop

import org.aopalliance.aop.Advice

/**
 * @author: cgrw
 **/

// AspectJ切面
class AspectJExpressionPointcutAdvisor : PointcutAdvisor {

    var pointcut: AspectJExpressionPointcut? = null
    var advice: Advice? = null

    override fun getMyPointcut(): Pointcut? {
        return pointcut
    }

    override fun getMyAdvice(): Advice? {
        return advice
    }

//    override fun getPointcut(): Pointcut? {
//        return pointcut
//    }
//
//    override fun getAdvice(): Advice? {
//        return advice
//    }
}