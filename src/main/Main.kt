import kotlincode.com.cgrw.ioc.context.ClassPathXmlApplicationContext
import testresources.beans.HelloJDKFather

/**
 * @author: cgrw
 **/

fun main(args: Array<String>) {
    var context = ClassPathXmlApplicationContext("bean-config.xml")
    (context.getBean("helloJDKProxy") as HelloJDKFather).helloMethod("cgrw")
}