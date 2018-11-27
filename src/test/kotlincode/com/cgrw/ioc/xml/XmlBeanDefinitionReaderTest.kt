package kotlincode.com.cgrw.ioc.xml

import kotlincode.com.cgrw.ioc.io.ResourceLoader
import org.junit.Test

import org.junit.Assert.*

/**
 * @author: cgrw
 */
class XmlBeanDefinitionReaderTest {
    var r = ResourceLoader()
    var xmlBeanDefinitionReader = XmlBeanDefinitionReader(r)


    @Test
    fun loadBeanDefinitions() {
        xmlBeanDefinitionReader.loadBeanDefinitions("bean-config.xml")
        println(xmlBeanDefinitionReader.registry!!.get("person")!!.beanClassName)
    }
}