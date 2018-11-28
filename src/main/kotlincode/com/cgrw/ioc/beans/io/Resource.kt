package kotlincode.com.cgrw.ioc.beans.io

import java.io.InputStream

/**
 * @author: cgrw
 **/
interface Resource {
    fun getInputStream(): InputStream
}