package mastermind

import mastermind.HintEnum.RIGHT_POSITION
import mastermind.HintEnum.WRONG_POSITION

enum class HintEnum {
    RIGHT_POSITION, WRONG_POSITION
}

data class Hint(val hintEnum: HintEnum, val guessIndex: Int, val correspondingSecretIndex: Int = guessIndex)

fun evaluateGuessWithStreams(secret: String, guess: String): Evaluation {

    val hints = mutableListOf<Hint>()

    for ((guessIndex, guessColor) in guess.withIndex()) {
        if (secret[guessIndex] == guessColor) {
            hints.add(Hint(RIGHT_POSITION, guessIndex))
        }
    }

    for ((guessIndex, guessColor) in guess.withIndex()) {
        if (!guessIsAlreadyHinted(hints, guessIndex)) {
            secretColorsNotYetHinted(hints, secret)
                    .filter { it.value == guessColor }
                    .map { it.index }
                    .firstOrNull()
                    ?.let { hints.add(Hint(WRONG_POSITION, guessIndex, it)) }
        }
    }

    return Evaluation(
            hints.filter { hint -> hint.hintEnum == RIGHT_POSITION }.size,
            hints.filter { hint -> hint.hintEnum == WRONG_POSITION }.size
    )
}

private fun guessIsAlreadyHinted(hints: MutableList<Hint>, guessIndex: Int): Boolean =
        hints.stream()
            .anyMatch { it.guessIndex == guessIndex }

private fun secretColorsNotYetHinted(hints: MutableList<Hint>, secret: String): List<IndexedValue<Char>> {
    val secretIndexesAlreadyHinted = hints.map {hint -> hint.correspondingSecretIndex }.toList()
    return secret.withIndex().filter { (index, value) -> !secretIndexesAlreadyHinted.contains(index) }.toList()
}