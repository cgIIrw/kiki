package kotlincode.com.cgrw.aop

import org.junit.Test

import org.junit.Assert.*
import testresources.beans.HelloJDKProxy

/**
 * @author: cgrw
 */
class TargetSourceTest {

    var helloJDKProxy = HelloJDKProxy()

    var targetSource = TargetSource(helloJDKProxy, *helloJDKProxy.javaClass.interfaces)
    @Test
    fun getTargetClass() {
        println(targetSource.targetClass[1].name)
    }

    @Test
    fun getTarget() {
    }
}