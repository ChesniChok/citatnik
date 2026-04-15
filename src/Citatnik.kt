import kotlin.random.Random


class Citatnik(
    private val parsed: Map<String, List<String>>
) {

    public val authors: List<String> = parsed.keys.toList()

    public val quotes: List<String> = parsed.values.flatten()

    private val authorsOfQuotes: Map<String, String> = parsed.flatMap { (author, quotes) ->

        quotes.map { it to author }

    }.toMap()


    fun quotesOf(author: String): List<String> {

        return parsed[author] ?: listOf()

    }

    fun authorOF(quote: String): String {

        return authorsOfQuotes[quote] ?: "не найден"

    }


    fun getQuote(ind: Int? = null, authorised: Boolean = false): String {

        val newInd = ind?: Random.nextInt(quotes.size)

        val quote = quotes.getOrNull(newInd) ?: "не найдена"

        val author = if (authorised) " © ${authorOF(quote)}" else ""

        return "$quote$author"

    }

    fun getQuotes(amount: Int = 1, offset: Int = 0, authorised: Boolean = false, randomised: Boolean = false): List<String> {

        val quotes = mutableListOf<String>()

        for (i in offset until amount+offset) {

            quotes += if (randomised) getQuote(authorised = authorised)
            else getQuote(authorised = authorised, ind = i)

        }

        return quotes

    }

}

