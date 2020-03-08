package nicestring

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestDoubleLetter {

    private fun testNiceString(string: String, expected: Boolean) {
        Assert.assertEquals("Wrong result for \"$string\".isNice()", expected, containsDoubleLetter(string))
    }

    @Test
    fun testExample1() = testNiceString("abba", true)

    @Test
    fun testExample2() = testNiceString("aba", false)

    @Test
    fun testExample3() = testNiceString("a11a", false)

}