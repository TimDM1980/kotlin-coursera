package nicestring

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestBuBaBe {

    private fun testNiceString(string: String, expected: Boolean) {
        Assert.assertEquals("Wrong result for \"$string\".isNice()", expected, doesntContainBuBaBe(string))
    }

    @Test
    fun testExample1() = testNiceString("abac", false)

    @Test
    fun testExample2() = testNiceString("abic", true)

    @Test
    fun testExample3() = testNiceString("abuc", false)

    @Test
    fun testExample4() = testNiceString("abec", false)



}