package mastermind

fun evaluateGuessProcedural(secret: String, guess: String): Evaluation {

    val secretColorsToCheckForWrongPosition = mutableListOf<Char>()
    val guessedColorsToCheckForWrongPosition = mutableListOf<Char>()

    for ((guessedIndex, guessedColor) in guess.withIndex()) {
        if (secret[guessedIndex] != guessedColor) {
            secretColorsToCheckForWrongPosition.add(secret[guessedIndex])
            guessedColorsToCheckForWrongPosition.add(guessedColor)
        }
    }
    val rightPosition = secret.length - secretColorsToCheckForWrongPosition.size

    var wrongPosition = 0
    for (guessedColor in guessedColorsToCheckForWrongPosition) {
        if (secretColorsToCheckForWrongPosition.contains(guessedColor)) {
            wrongPosition++
            secretColorsToCheckForWrongPosition.remove(guessedColor)
        }
    }

    return Evaluation(rightPosition, wrongPosition)
}