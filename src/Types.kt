

class Author(
    val name: String
) {

    val quotes: MutableList<String> = mutableListOf()

    fun addQuote(q: String) {
        quotes.add(q)
    }

}

