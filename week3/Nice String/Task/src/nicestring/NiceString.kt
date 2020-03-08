package nicestring

val doesntContainBuBaBe: (String) -> Boolean = { !it.contains( Regex("b[uae]")) }

val containsThreeVowels: (String) -> Boolean = { it.contains( Regex("(.*[aeiou].*){3,}")) }

val containsDoubleLetter: (String) -> Boolean = { it.contains( Regex("""([a-z])\1""")) }

fun String.isNice(): Boolean {

    return listOf(doesntContainBuBaBe, containsThreeVowels, containsDoubleLetter)
            .filter { it(this) }
            .size >= 2

}