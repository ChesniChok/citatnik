import java.io.File


fun main() {

    val readed = File("цитатник.txt").readText()

    val parser = Parser(readed)

    parser.MAIN()

}

