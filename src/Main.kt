import java.io.File


fun main() {

    val readed = File("цитатник.txt").readText()

    val parser = Parser(readed)

    parser.parse()

    parser.content.forEach { author ->

        println("\t\t${author.key}")

        author.value.forEach { quote ->
            println(quote)
            println()
        }

        println("=== === === === ===")

    }

}

