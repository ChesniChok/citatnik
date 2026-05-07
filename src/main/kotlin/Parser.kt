package main.kotlin

class Parser(
    private val input: String
) {

    private var curCharInd: Int = 0

    private val curChar: Char
        get() = input.getOrNull(curCharInd) ?: banChar//тащим символ если есть, иначе это знак того, что случился кердык (конец файла)

    private val banChar = '\\'//не встречается в стандартной структуре цитатника, пусть будет он


    private fun readChar() {
        curCharInd++//двигаем читающую каретку дальше
    }



    fun parse() {//конвертируем .txt в карту <автор, список цитат>

        while (findAuthor()) {//пока есть новый автор

            val author = getAuthor().trim()//читаем автора и обрезаем лишнее

            content[author] = mutableListOf()//создаём под автора список для заполнения

            while (findQuote()) content[author]?.add(getQuote().trim())//пока находятся цитаты: читаем, обрезаем лишнее и записываем их

        }

    }


    val content: MutableMap<String, MutableList<String>> = mutableMapOf()//сюда всё складывается


    private fun findAuthor(): Boolean {//поиск автора

        while (curChar != '-') {//пока не нашли метку начала имени
            if (curChar == banChar) return false//если кердык — заканчиваем

            readChar()//читаем далее
        }

        readChar()//переходим к имени (пропускаем '-')

        return true//нашли

    }

    private fun getAuthor(): String {//читаем автора

        val authName = StringBuilder()//сюда записываем

        while (curChar != ':') {//пока не дочитали до метки конца
            authName.append(curChar)//записываем

            readChar()//и читаем
        }

        readChar()//пропускаем ":"

        return authName.toString()//возвращаем чё начитали

    }

    private fun findQuote(): Boolean {//поиск цитаты
        while (curChar != '\t') {//пока не нашли табуляцию, которая означает начало строки в цитате
            if (curChar == ';') return false//если кердык — заканчиваем

            readChar()//пропускаем
        }

        return true//цитата ещё есть
    }

    private fun getQuote(): String {//читаем цитату

        val quote = StringBuilder()//сюда записываем

        while (curChar != banChar) {//если кердык

            //сейчас в curChar находится \t или \n

            //читаем построчно
            while (curChar == '\t') {//если на новой строке табуляция — это часть цитаты

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

