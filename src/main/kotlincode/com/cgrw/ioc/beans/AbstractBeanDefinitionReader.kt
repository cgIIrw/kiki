package kotlincode.com.cgrw.ioc.beans

import kotlincode.com.cgrw.ioc.beans.io.ResourceLoader

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