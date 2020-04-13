package games.gameOfFifteen

import org.junit.Assert
import org.junit.Test

class TestGameOfFifteenInitializer {
    @Test
    fun testInitialPermutationIsNotTrivial() {
        val initializer = RandomGameInitializer()
        Assert.assertNotEquals("The initial permutation must not be trivial",
            (1..15).toList(), initializer.initialPermutation)
    }

    // https://www.coursera.org/learn/kotlin-for-java-developers/discussions/weeks/5/threads/vfkQ29ZYTMe5ENvWWKzHtw
    @Test
    fun testInitialPermutationIsEven() {
        val initializer = RandomGameInitializer()
        Assert.assertTrue("The initial permutation must be even",
            isEven(initializer.initialPermutation))
    }
}