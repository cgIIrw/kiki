package kotlincode.com.cgrw.ioc.beans

/**
 * @author: cgrw
 **/
interface BeanDefinitionReader {
    fun loadBeanDefinitions(location: String)
}