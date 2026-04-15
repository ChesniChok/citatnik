

class Parser(
    private val input: String
) {

    private var curCharInd: Int = 0

    private val curChar: Char
        get() = input.getOrNull(curCharInd) ?: banChar

    private val banChar = '\\'


    private fun readChar() {
        curCharInd++
    }



    fun parse() {

        while (findAuthor()) {

            val author = getAuthor().trim()

            content[author] = mutableListOf()

            while (findQuote()) content[author]?.add(getQuote().trim())

        }

    }


    val content: MutableMap<String, MutableList<String>> = mutableMapOf()


    private fun findAuthor(): Boolean {

        while (curChar != '-') {
            if (curChar == banChar) return false

            readChar()
        }

        readChar()//переходим к имени

        return true

    }

    private fun getAuthor(): String {

        var authName = ""

        while (curChar != ':') {//пока не дочитали до конца
            authName += curChar//записываем

            readChar()//и читаем
        }

        readChar()//пропускаем ":"

        return authName

    }

    private fun findQuote(): Boolean {
        while (curChar != '\t') {
            if (curChar == ';') return false//если конец списка цитат у автора, то цитат больше нет

            readChar()//пропускаем всё до начала цитаты
        }

        return true//цитата ещё есть
    }

    private fun getQuote(): String {

        val quote = StringBuilder()

        while (curChar != banChar) {//если кердык

            //сейчас в curChar находится \t

            //читаем построчно
            while (curChar == '\t') {//если на новой строке табуляция — это продолжение цитаты

                readChar()//пропускаем табуляцию

                while (curChar != '\n') {//пока не дошли до конца строки
                    quote.append(curChar)//добавляем

                    readChar()//читаем далее
                }

                readChar()//пропускаем перевод на новую строку

                if (curChar == '\t') quote.append('\n')//если снова табуляция, переводим на новую строку

            }

            return quote.toString()//возвращаем что начитали

        }

        return "ban"//кердык

    }

}

