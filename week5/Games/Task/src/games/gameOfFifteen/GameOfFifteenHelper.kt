package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    val smallestIntPresentInTheList = permutation.sorted().first()
    val mapOfUnpermutatedAndPermutatedInts: Map<Int, Int> = permutation
            .withIndex()
            .map { it.index + smallestIntPresentInTheList to it.value }
            .toMap()
    println(mapOfUnpermutatedAndPermutatedInts)

    var numberOfSwaps = 0

    while (hasSwapsLeftToUndoPermutation(mapOfUnpermutatedAndPermutatedInts)) {
        val indexToSwap: Int = findIndexOfItemToSwap(mapOfUnpermutatedAndPermutatedInts)!!
        val indexToSwapWith: Int = findIndexOfItemToSwapWith(mapOfUnpermutatedAndPermutatedInts, indexToSwap)
        doSwap(mapOfUnpermutatedAndPermutatedInts, indexToSwap, indexToSwapWith)
        numberOfSwaps++
    }

    return numberOfSwaps % 2 == 0
}

fun hasSwapsLeftToUndoPermutation(mapOfUnpermutatedAndPermutatedInts: Map<Int, Int>): Boolean {
    return findIndexOfItemToSwap(mapOfUnpermutatedAndPermutatedInts) != null
}

fun findIndexOfItemToSwap(mapOfUnpermutatedAndPermutatedInts: Map<Int, Int>): Int? {
    return mapOfUnpermutatedAndPermutatedInts.map { it.key to it.value }
            .firstOrNull { (index, value) -> value > index } // if the value is bigger than the index, it means the element is not in the right place
            ?.first
}

fun findIndexOfItemToSwapWith(mapOfUnpermutatedAndPermutatedInts: Map<Int, Int>, indexToSwap: Int): Int {
    return mapOfUnpermutatedAndPermutatedInts
            .entries
            .first { it.value == indexToSwap }
            .key
}

fun doSwap(mapOfUnpermutatedAndPermutatedInts: Map<Int, Int>, indexToSwap: Int, indexToSwapWith: Int) {
    val putThisAside = mapOfUnpermutatedAndPermutatedInts.getValue(indexToSwap)
    val mutable: MutableMap<Int, Int> = mapOfUnpermutatedAndPermutatedInts as MutableMap<Int, Int>
    mutable[indexToSwap] = mapOfUnpermutatedAndPermutatedInts.getValue(indexToSwapWith)
    mutable[indexToSwapWith] = putThisAside
}