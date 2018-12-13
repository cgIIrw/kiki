package kotlincode.com.cgrw.aop

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * @author: cgrw
 */
class AspectJExpressionPointcutTest {

    var aspectJExpressionPointcut = AspectJExpressionPointcut()
    var expression = "execution(* kotlincode.com.cgrw.aop.*.*(..))"

    init {
        aspectJExpressionPointcut.expression = expression
    }


    @Test
    fun matches() {
        Assert.assertTrue(aspectJExpressionPointcut.getClassFilter().matches(Advisor::class.java))
    }

    @Test
    fun matches02() {
        Assert.assertTrue(aspectJExpressionPointcut.getMethodMatcher()
                .matches(Advisor::class.java.getDeclaredMethod("getAdvice"), Advisor::class.java))
    }
}