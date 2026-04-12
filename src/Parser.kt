

class Parser(
    private val input: String
) {

    private var curCharInd: Int = 0

    public val curChar: Char
        get() = input.getOrNull(curCharInd) ?: banChar

    public val prevChar: Char
        get() = input.getOrNull(curCharInd - 1) ?: banChar

    private val nextChar: Char
        get() = input.getOrNull(curCharInd + 1) ?: banChar

    private val curToken: String
        get() = "$prevChar$curChar"

    private val banChar = '\\'


    private fun readChar() {
        curCharInd++
    }



    fun MAIN() {

        findAuthor()
        println(getAuthor())

        findQuote()
        println(getQuote())

        println("=== === === === ===")

        findQuote()
        println(getQuote())

    }


    val Content: MutableMap<String, String> = mutableMapOf()









    private fun findAuthor() {
        while (curChar != '-') readChar()//ищем начало имени

        readChar()//переходим к имени
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

    private fun findQuote() {
        while (curChar != '\t') readChar()//пропускаем всё до начала цитаты
    }

    private fun getQuote(): String {

        val quote = StringBuilder()

        //сейчас в curChar находится \t

        while (curChar != banChar) {//если кердык

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

