package kotlincode.com.cgrw.ioc.beans

/**
 * @author: cgrw
 **/

// bean后置处理器
interface BeanPostProcessor {

    // 调用bean初始化方法前的处理方法
    fun postProcessBeforeInitialization(bean: Any, beanName: String): Any
    // 调用bean初始化方法后的处理方法
    fun postProcessAfterInitialization(bean: Any, beanName: String): Any
}

