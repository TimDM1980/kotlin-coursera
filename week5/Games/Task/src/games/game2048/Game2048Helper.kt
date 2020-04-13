package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    return this // b, null, a, a
            .filterNotNull() // b, a, a
            .windowed(2, 1, true,
                    { window -> Pair(window[0], window.getOrNull(1)) }) // b-a, a-a, a-null
            .let { zipRecursive_AsIdenticalPairs_OrAsPairsWithNullAsSecond(it) } // b-null, a-a
            .map { if (it.first == it.second) merge(it.first) else it.first} // b, aa
}

private fun <T : Any> zipRecursive_AsIdenticalPairs_OrAsPairsWithNullAsSecond(zippedWithNext: List<Pair<T, T?>>): List<Pair<T, T?>> {
    val firstPair: Pair<T, T?> = zippedWithNext[0]
    return when {
        zippedWithNext.isEmpty() -> emptyList()

        firstPair.first == firstPair.second -> listOf(firstPair) +
                when {
                    zippedWithNext.size <= 2 -> emptyList()
                    else -> zipRecursive_AsIdenticalPairs_OrAsPairsWithNullAsSecond(zippedWithNext.subList(2, zippedWithNext.size))
                }

        else -> listOf(Pair(firstPair.first, null)) +
                when {
                    zippedWithNext.size == 1 -> emptyList()
                    else -> zipRecursive_AsIdenticalPairs_OrAsPairsWithNullAsSecond(zippedWithNext.subList(1, zippedWithNext.size))
                }
    }
}
