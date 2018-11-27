package kotlincode.com.cgrw.ioc

import kotlincode.com.cgrw.ioc.io.ResourceLoader

/**
 * @author: cgrw
 **/
abstract class AbstractBeanDefinitionReader(var resourceLoader: ResourceLoader)
    : BeanDefinitionReader {
    var registry: HashMap<String, BeanDef>? = null
    init {
        registry = HashMap()
    }
}