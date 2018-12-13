package kotlincode.com.cgrw.aop

import org.aspectj.weaver.tools.PointcutExpression
import org.aspectj.weaver.tools.PointcutParser
import org.aspectj.weaver.tools.PointcutPrimitive
import java.lang.reflect.Method

/**
 * @author: cgrw
 **/
class AspectJExpressionPointcut : Pointcut, ClassFilter, MethodMatcher {

    // 切点解析器
    var pointcutParser: PointcutParser? = null

    // 表达式字符串
    var expression: String? = null

    // 切点表达式
    var pointcutExpression: PointcutExpression? = null

    companion object {

        // 默认的primitives(原语)
        private val primitives = HashSet<PointcutPrimitive>()

        init {
            primitives.add(PointcutPrimitive.ADVICE_EXECUTION)
            primitives.add(PointcutPrimitive.ARGS)
            primitives.add(PointcutPrimitive.CALL)
            primitives.add(PointcutPrimitive.EXECUTION)
            primitives.add(PointcutPrimitive.GET)
            primitives.add(PointcutPrimitive.HANDLER)
            primitives.add(PointcutPrimitive.INITIALIZATION)
            primitives.add(PointcutPrimitive.PRE_INITIALIZATION)
            primitives.add(PointcutPrimitive.SET)
            primitives.add(PointcutPrimitive.STATIC_INITIALIZATION)
            primitives.add(PointcutPrimitive.TARGET)
            primitives.add(PointcutPrimitive.THIS)
            primitives.add(PointcutPrimitive.WITHIN)
            primitives.add(PointcutPrimitive.WITHIN_CODE)
            primitives.add(PointcutPrimitive.AT_ANNOTATION)
            primitives.add(PointcutPrimitive.AT_THIS)
            primitives.add(PointcutPrimitive.AT_TARGET)
            primitives.add(PointcutPrimitive.AT_ARGS)
            primitives.add(PointcutPrimitive.AT_WITHIN)
            primitives.add(PointcutPrimitive.AT_WITHINCODE)
            primitives.add(PointcutPrimitive.REFERENCE)
        }
    }

    constructor(supportedPrimitives: Set<PointcutPrimitive>) {
        pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives)
    }

    constructor() : this(primitives)

    // 避免重复解析
    fun checkReadyToMatch() {
        pointcutExpression = pointcutExpression ?: buildPointcutExpression()
    }

    // 切点解析器内部的解析操作
    private fun buildPointcutExpression(): PointcutExpression {
        return pointcutParser!!.parsePointcutExpression(expression)
    }

    // 类过滤方法
    override fun getClassFilter(): ClassFilter {
        return this
    }

    // 方法匹配方法
    override fun getMethodMatcher(): MethodMatcher {
        return this
    }

    // 已经通过了判空，如果还为空将抛出空指针异常，所以双感叹号；
    override fun matches(targetClass: Class<*>): Boolean {
        checkReadyToMatch()
        return pointcutExpression!!.couldMatchJoinPointsInType(targetClass)
    }

    // 已经通过了判空，如果还为空将抛出空指针异常，所以双感叹号；
    override fun matches(method: Method, targetClass: Class<*>): Boolean {
        checkReadyToMatch()
        var shadowMatch = pointcutExpression!!.matchesMethodExecution(method)
        if (shadowMatch.alwaysMatches())
            return true
        else if (shadowMatch.neverMatches())
            return false
        return false
    }
}