package kotlincode.com.cgrw.ioc

import kotlincode.com.cgrw.ioc.factory.BeanFac
import org.junit.Test

/**
 * @author: cgrw
 */
class BeanFacTest {
    var fac = BeanFac()

    @Test
    fun getBean() {

    }

    @Test
    fun setBean() {
        fac.setBean("chenchen", BeanDef(Any()))
    }
}