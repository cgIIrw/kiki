package kotlincode.com.cgrw.ioc.xml

import kotlincode.com.cgrw.ioc.BeanReference
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
        var s = xmlBeanDefinitionReader.registry!!.get("person")!!
                .propertyValues.propertyValueList.get(2).value as BeanReference
        println(s.name)
    }
}