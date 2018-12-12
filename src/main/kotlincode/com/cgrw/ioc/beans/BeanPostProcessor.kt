package kotlincode.com.cgrw.ioc.beans

/**
 * @author: cgrw
 **/
interface BeanPostProcessor {

    fun postProcessBeforeInitialization(bean: Any, beanName: String): Any

    fun postProcessAfterInitialization(bean: Any, beanName: String): Any
}

