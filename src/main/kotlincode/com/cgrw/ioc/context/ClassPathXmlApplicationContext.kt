package kotlincode.com.cgrw.ioc.context

import kotlincode.com.cgrw.ioc.beans.factory.AbsBeanFac
import kotlincode.com.cgrw.ioc.beans.factory.AutowireCapableBeanFac
import kotlincode.com.cgrw.ioc.beans.io.ResourceLoader
import kotlincode.com.cgrw.ioc.beans.xml.XmlBeanDefinitionReader

/**
 * @author: cgrw
 **/
class ClassPathXmlApplicationContext(var configLocation: String, var beanFactory: AbsBeanFac)
    : AbstractApplicationContext(beanFactory) {

    constructor(configLocation: String) : this(configLocation, AutowireCapableBeanFac())

    init {
        refresh()
    }

    // 在这里的作用是将对xml解析的(name, BeanDef)键值对装入工厂的容器中
    override fun loadBeanDefinitions(beanFac: AbsBeanFac) {
        var r = ResourceLoader()
        var xmlBeanDefinitionReader = XmlBeanDefinitionReader(r)
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation)
        xmlBeanDefinitionReader.registry?.let {
            it.entries.forEach { insideit ->
                beanFactory.registerBeanDef(insideit.key, insideit.value)
            }
        }
    }
}