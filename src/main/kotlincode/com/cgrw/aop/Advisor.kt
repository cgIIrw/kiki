package kotlincode.com.cgrw.aop

import org.aopalliance.aop.Advice

/**
 * @author: cgrw
 **/
// 接口，用于获得advice
interface Advisor {
    fun getAdvice(): Advice?
}