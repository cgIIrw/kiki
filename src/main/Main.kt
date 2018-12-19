import kotlincode.com.cgrw.ioc.context.ClassPathXmlApplicationContext
import testresources.beans.HelloJDKFather
import testresources.beans.HelloJDKProxy

/**
 * @author: cgrw
 **/

fun main(args: Array<String>) {
    var context = ClassPathXmlApplicationContext("bean-config.xml")
    (context.getBean("helloJDKProxy") as HelloJDKProxy).helloMethod(3)
}