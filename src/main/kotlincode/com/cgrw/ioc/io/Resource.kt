package kotlincode.com.cgrw.ioc.io

import java.io.InputStream

/**
 * @author: cgrw
 **/
interface Resource {
    fun getInputStream(): InputStream
}