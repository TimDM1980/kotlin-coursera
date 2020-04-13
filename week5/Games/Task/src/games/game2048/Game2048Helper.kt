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
    val zippedPairs = this // b, null, a, a
            .filterNotNull() // b, a, a
            .windowed(2, 1, true,
                    { window -> Pair(window[0], window.getOrNull(1)) }) //b-a, a-a, a-null
    val zippedEqualPairs = moveAndMergeEqualRecursive(zippedPairs) // b-null, a-a
    return zippedEqualPairs.map { //b, aa
        when {
            it.first == it.second -> merge(it.first)
            else -> it.first
        }
    }
}

private fun <T : Any> moveAndMergeEqualRecursive(zippedPairs: List<Pair<T, T?>>): List<Pair<T, T?>> {
    val firstPair: Pair<T, T?> = zippedPairs[0]
    return when {
        zippedPairs.isEmpty() -> emptyList()
        zippedPairs.size == 1 -> {
            if (firstPair.first == firstPair.second) listOf(firstPair) else listOf(Pair(firstPair.first, null))
        }
        (zippedPairs.size == 2 && firstPair.first == firstPair.second) -> listOf(firstPair)
        firstPair.first == firstPair.second -> listOf(firstPair) + moveAndMergeEqualRecursive(zippedPairs.subList(2, zippedPairs.size))
        else -> listOf(Pair(firstPair.first, null)) + moveAndMergeEqualRecursive(zippedPairs.subList(1, zippedPairs.size))
    }
}

