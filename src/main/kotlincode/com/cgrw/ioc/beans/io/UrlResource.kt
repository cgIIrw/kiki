package kotlincode.com.cgrw.ioc.beans.io

import java.io.InputStream
import java.net.URL

/**
 * @author: cgrw
 **/
class UrlResource(val url: URL): Resource {
    override fun getInputStream(): InputStream {
        var urlConnection = url.openConnection()
        urlConnection.connect()
        return urlConnection.getInputStream()
    }
}