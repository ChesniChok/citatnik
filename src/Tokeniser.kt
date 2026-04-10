

class Tokeniser(
    private val input: String
) {

    private var curCharInd: Int = 0

    private val curChar: Char
        get() = input.getOrNull(curCharInd) ?: '\\'

    private val prevChar: Char
        get() = input.getOrNull(curCharInd - 1) ?: '\\'

    private val banChar = '\\'


    fun readChar() {
        curCharInd++
    }

    

    private fun findAuthor(): String {
        while (curChar != '-') readChar()

        return getAuthor()
    }

    private fun getAuthor(): String {

        var toRet = ""

        while (curChar != ':') toRet += curChar

        return toRet

    }











}

