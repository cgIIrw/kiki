package kotlincode.com.cgrw.aop

import org.aopalliance.aop.Advice

/**
 * @author: cgrw
 **/

// AspectJ切面
class AspectJExpressionPointcutAdvisor : PointcutAdvisor {
    private var pointcut = AspectJExpressionPointcut()

    // 这里为了兼容原本的get方法和重写的get方法，将属性设置为private
    private var advice: Advice? = null

    fun setAdvice(advice: Advice) {
        this.advice = advice
    }

    fun setExpression(expression: String) {
        this.pointcut.expression = expression
    }

    override fun getPointcut(): Pointcut {
        return pointcut
    }

    override fun getAdvice(): Advice? {
        return advice
    }
}