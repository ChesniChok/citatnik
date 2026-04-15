import java.io.File


fun main() {

    val readed = File("цитатник.txt").readText()

    val parser = Parser(readed)

    parser.parse()

    val citatnik = Citatnik(parser.content)

    //println(citatnik.getQuote(authorised = true))

    //println(citatnik.getQuotes(amount = 3, offset = 315))

    citatnik.getQuotes(amount = citatnik.quotes.size, authorised = true).forEach {

        println("$it\n===\n")

    }

}

