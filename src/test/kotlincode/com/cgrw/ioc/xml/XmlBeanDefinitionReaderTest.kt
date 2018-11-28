package kotlincode.com.cgrw.ioc.xml

import kotlincode.com.cgrw.ioc.BeanReference
import kotlincode.com.cgrw.ioc.beans.io.ResourceLoader
import kotlincode.com.cgrw.ioc.beans.xml.XmlBeanDefinitionReader
import org.junit.Test

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
        println(s.id)
    }
}