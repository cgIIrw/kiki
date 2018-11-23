package kotlincode.com.cgrw.ioc

import kotlincode.com.cgrw.ioc.io.ResourceLoader

/**
 * @author: cgrw
 **/
abstract class AbstractBeanDefinitionReader(var resourceLoader: ResourceLoader)
    : BeanDefinitionReader {
    init {
        var registry = HashMap<String, BeanDef>()
    }
}