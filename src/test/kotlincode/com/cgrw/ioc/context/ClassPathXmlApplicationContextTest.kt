package kotlincode.com.cgrw.ioc.context

import kotlincode.com.cgrw.ioc.beans.factory.AutowireCapableBeanFac
import org.junit.Test

import org.junit.Assert.*
import testresources.beans.Person
import testresources.beans.Student

/**
 * @author: cgrw
 */
class ClassPathXmlApplicationContextTest {
    var autowire = AutowireCapableBeanFac()
    var classPathXmlApp = ClassPathXmlApplicationContext("bean-config.xml", autowire)

    @Test
    fun refresh() {
        classPathXmlApp.refresh()
        println((autowire.getBean("student01") as Student).age)
    }
}