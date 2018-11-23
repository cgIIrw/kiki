package kotlincode.com.cgrw.ioc

/**
 * @author: cgrw
 **/
interface BeanDefinitionReader {
    fun loadBeanDefinitions(location: String)
}