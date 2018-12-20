package testresources.beans

/**
 * @author: cgrw
 **/

interface HelloJDKFather {
    fun helloMethod(s: String)
}

interface HelloJDKMother

open class HelloJDKProxy : HelloJDKFather, HelloJDKMother {

    override fun helloMethod(s: String) {
        println("I am: ${s}!")
    }
}