package kotlincode.com.cgrw.aop

import org.aspectj.weaver.tools.PointcutExpression
import org.aspectj.weaver.tools.PointcutParser
import org.aspectj.weaver.tools.PointcutPrimitive
import java.lang.reflect.Method

/**
 * @author: cgrw
 **/
class AspectJExpressionPointcut : Pointcut, ClassFilter, MethodMatcher {
    var pointcutParser: PointcutParser? = null

    var expression: String? = null

    var pointcutExpression: PointcutExpression? = null

    companion object {
        private val DEFAULT_SUPPORTED_PRIMITIVES = HashSet<PointcutPrimitive>()

        init {
            DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION)
            DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS)
            DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE)
            DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS)
            DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET)
            DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN)
            DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION)
            DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN)
            DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS)
            DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET)
        }
    }

    constructor(supportedPrimitives: Set<PointcutPrimitive>) {
        var pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives)
    }

    constructor() : this(DEFAULT_SUPPORTED_PRIMITIVES)

    fun checkReadyToMatch() {
        pointcutExpression = pointcutExpression ?: buildPointcutExpression()
    }

    private fun buildPointcutExpression(): PointcutExpression {
        return pointcutParser!!.parsePointcutExpression(expression)
    }

    override fun getClassFilter(): ClassFilter {
        return this
    }

    override fun getMethodMatcher(): MethodMatcher {
        return this
    }

    override fun matches(targetClass: Class<*>): Boolean {
        checkReadyToMatch()
        return pointcutExpression!!.couldMatchJoinPointsInType(targetClass)
    }

    override fun matches(method: Method, targetClass: Class<*>): Boolean {
        checkReadyToMatch()
        var shadowMatch = pointcutExpression!!.matchesAdviceExecution(method)
        if (shadowMatch.alwaysMatches())
            return true
        else if (shadowMatch.neverMatches())
            return false
        return false
    }
}