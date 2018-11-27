package kotlincode.com.cgrw.ioc.factory

import kotlincode.com.cgrw.ioc.io.ResourceLoader
import kotlincode.com.cgrw.ioc.xml.XmlBeanDefinitionReader
import org.junit.Test

import org.junit.Assert.*
import testresources.beans.Student

/**
 * @author: cgrw
 */
class AutowireCapableBeanFacTest {
    var autowire = AutowireCapableBeanFac()
    var r = ResourceLoader()
    var xmlBeanDefinitionReader = XmlBeanDefinitionReader(r)

    @Test
    fun doCreateBean() {
        xmlBeanDefinitionReader.loadBeanDefinitions("bean-config.xml")
        var s = xmlBeanDefinitionReader.registry!!.get("student")!!
        autowire.registerBeanDef("student", s)
        println(autowire.doCreateBean(s) is Student)
    }
}