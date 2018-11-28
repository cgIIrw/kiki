package kotlincode.com.cgrw.ioc.factory

import kotlincode.com.cgrw.ioc.beans.factory.AutowireCapableBeanFac
import kotlincode.com.cgrw.ioc.beans.io.ResourceLoader
import kotlincode.com.cgrw.ioc.beans.xml.XmlBeanDefinitionReader
import org.junit.Test

import testresources.beans.Person
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
        var s = xmlBeanDefinitionReader.registry!!.get("person")!!
        var s1 = xmlBeanDefinitionReader.registry!!.get("student")!!
        autowire.registerBeanDef("person", s)
        autowire.registerBeanDef("student", s1)
        println((autowire.doCreateBean(s) as Person).refer is Student)
    }
}