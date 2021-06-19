import java.util.Scanner

fun addBook(): Book {
    val scanner = Scanner(System. `in`)
    print("Please enter title: ")
    val title: String = readLine()!!
    print("Please enter author: ")
    var author: String = readLine()!!
    print("Please enter genres separated by a comma (no space): ")
    val genre: MutableList<String> = readLine()!!.split(',').toMutableList()
    print("Please enter series: ")
    val series: String = readLine()!!
    print("Please enter year: ")
    val year: Int = scanner.nextInt()

    val book: Book = Book(title, author, genre, series, year)
    return book
}

fun addMovie(): Movie {
    val scanner = Scanner(System. `in`)
    print("Please enter title: ")
    val title: String = readLine()!!
    print("Please enter genres separated by a comma (no space): ")
    val genre: MutableList<String> = readLine()!!.split(',').toMutableList()
    print("Please enter series: ")
    val series: String = readLine()!!
    print("Please enter year: ")
    val year: Int = scanner.nextInt()

    val movie: Movie = Movie(title, genre, series, year)
    return movie
}

fun addCD(): CD {
    val scanner = Scanner(System. `in`)
    print("Please enter title: ")
    val title: String = readLine()!!
    print("Please enter artist: ")
    var artist: String = readLine()!!
    print("Please enter genres separated by a comma (no space): ")
    val genre: MutableList<String> = readLine()!!.split(',').toMutableList()
    print("Please enter year: ")
    val year: Int = scanner.nextInt()

    val cd: CD = CD(title, artist, genre, year)
    return cd
}

fun menu() {
    val scanner = Scanner(System.`in`)
    var option: Int
    val library = Library()
    while (true) {
        println("MEDIA LIBRARY:")
        println("Add book:     1 | Add Movie:    2 | Add Cd:     3")
        println("Save Library: 4 | View Library: 5 | View Books: 6")
        println("View Movies:  7 | View Cds:     8 | Quit:       9")
        print("Choose an option: ")

        option = scanner.nextInt()
        while (option < 1 || option > 9) {
            print("Please enter a valid option: ")
            option = scanner.nextInt()
        }

        when (option) {
            1 -> library.addBook(addBook())
            2 -> library.addMovie(addMovie())
            3 -> library.addCD(addCD())
            4 -> library.saveLibrary()
            5 -> library.displayLibrary()
            6 -> library.displayBooks()
            7 -> library.displayMovies()
            8 -> library.displayCDs()
            9 -> return
        }
    }
}

fun main() {
    val library = Library()
    library.createDatabase()
    menu()
}