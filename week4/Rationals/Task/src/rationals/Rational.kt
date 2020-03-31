package rationals

import java.math.BigInteger

@Suppress("DataClassPrivateConstructor")
data class Rational private constructor(val numerator: BigInteger, val denominator: BigInteger) : Comparable<Rational> {

    // it was a good choice to normalize during initialization,
        // because we need the normalized form when comparing, printing, ...
        // but how do we initialize???
    // my solution: data class + var
        // when using data class, we can't use VAL in the primary constructor because the properties must be recalculated (normalized)
        // drawback: we must use var and we now have a mutable dataclass, never a good idea
    // better solution: private primary constructor + companion object
    // other solution: non-data class + val
        // you can do the initialization in the init block
        // drawback: roll your own equals, hashcode, toString, ...

    companion object {
        fun create(numerator: BigInteger, denominator: BigInteger): Rational =
            normalize(numerator, denominator)

        private fun normalize(numerator: BigInteger, denominator: BigInteger): Rational {
            val gcd = numerator.gcd(denominator)

            var num = numerator / gcd
            var denom = denominator / gcd

            if (denominator < BigInteger.ZERO) {
                num = -num
                denom = -denom
            }

            return Rational(num, denom)
        }
    }

    operator fun plus(other: Rational): Rational {
        return create(
                (this.numerator * other.denominator) + (other.numerator * this.denominator),
                this.denominator * other.denominator
        )
    }

    operator fun minus(other: Rational): Rational {
        return create(
                (this.numerator * other.denominator) - (other.numerator * this.denominator),
                this.denominator * other.denominator
        )
    }

    operator fun times(other: Rational): Rational {
        return create(
                this.numerator * other.numerator,
                this.denominator * other.denominator
        )
    }

    operator fun div(other: Rational): Rational {
        return this * create(other.denominator, other.numerator)
    }

    override operator fun compareTo(other: Rational): Int {
        return (this.numerator * other.denominator).compareTo(other.numerator * this.denominator)
    }

    operator fun unaryMinus(): Rational {
        return create(-this.numerator, this.denominator)
    }

    override fun toString(): String {
        return if (BigInteger.ONE == denominator) numerator.toString() else ("$numerator/$denominator")
    }
}

infix fun Int.divBy(other: Int): Rational =
    Rational.create(this.toBigInteger(), other.toBigInteger())

infix fun Long.divBy(other: Long): Rational {
    return Rational.create(BigInteger.valueOf(this), BigInteger.valueOf(other))
}

infix fun BigInteger.divBy(other: BigInteger): Rational {
    return Rational.create(this, other)
}

fun String.toRational(): Rational =
    Rational.create(
        this.split("/").first().toBigInteger(),
        this.split("/").getOrElse(1) { _ -> "1" }.toBigInteger()
    )

fun main() {
    val half: Rational = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}