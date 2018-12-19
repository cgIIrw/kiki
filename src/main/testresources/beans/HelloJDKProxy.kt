package testresources.beans

/**
 * @author: cgrw
 **/

interface HelloJDKFather {
    fun helloMethod(x: Int)
}

interface HelloJDKMother

class HelloJDKProxy : HelloJDKFather, HelloJDKMother {

    override fun helloMethod(x: Int) {
        println("I am Hello! $x")
    }
}