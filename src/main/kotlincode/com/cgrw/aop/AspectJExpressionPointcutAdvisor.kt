package kotlincode.com.cgrw.aop

import org.aopalliance.aop.Advice

/**
 * @author: cgrw
 **/

class AspectJExpressionPointcutAdvisor : PointcutAdvisor {
    private var pointcut = AspectJExpressionPointcut()
    private var advice: Advice? = null

    fun setAdvice(advice: Advice) {
        this.advice = advice
    }

    override fun getPointcut(): Pointcut {
        return pointcut
    }

    override fun getAdvice(): Advice? {
        return advice
    }
}