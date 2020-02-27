package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    return evaluateGuess(secret.withIndex(), guess.withIndex())
}

private fun evaluateGuess(secret: Iterable<IndexedValue<Char>>, guess: Iterable<IndexedValue<Char>>): Evaluation {
    return Evaluation(rightPosition(secret, guess), wrongPosition(secret, guess))
}

private fun rightPosition(secret: Iterable<IndexedValue<Char>>, guess: Iterable<IndexedValue<Char>>): Int {
    return rightPositionIndexes(secret, guess).size
}

private fun wrongPosition(secret: Iterable<IndexedValue<Char>>, guess: Iterable<IndexedValue<Char>>): Int {
    var wrongPosition: Int = 0

    var secretColorsAvailableToMatchWithGuessedColors = secret
            .filter { !rightPositionIndexes(secret, guess).contains(it.index) }
            .toMutableList()

    guess.filter { !rightPositionIndexes(secret, guess).contains(it.index) }
            .forEach {
                val matchingSecretColor = getMatchingSecretColor(secretColorsAvailableToMatchWithGuessedColors, it)
                matchingSecretColor?.let {
                    secretColorsAvailableToMatchWithGuessedColors.remove(matchingSecretColor)
                    wrongPosition++
                }
            }

    return wrongPosition
}

private fun getMatchingSecretColor(secretColorsAvailableToMatchWithGuessedColors: List<IndexedValue<Char>>, guess: IndexedValue<Char>): IndexedValue<Char>? {
    return secretColorsAvailableToMatchWithGuessedColors.firstOrNull { it.value == guess.value }
}

private fun rightPositionIndexes(secret: Iterable<IndexedValue<Char>>, guess: Iterable<IndexedValue<Char>>): List<Int> {
    return secret.intersect(guess).map { it.index }
}