package nicestring

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestThreeVowels {

    private fun testNiceString(string: String, expected: Boolean) {
        Assert.assertEquals("Wrong result for \"$string\".isNice()", expected, containsThreeVowels(string))
    }

    @Test
    fun testExample1() = testNiceString("aei", true)

    @Test
    fun testExample2() = testNiceString("ai", false)

    @Test
    fun testExample3() = testNiceString("abcdieto", true)

    @Test
    fun testExample4() = testNiceString("fkdsflsdjkkl", false)



}