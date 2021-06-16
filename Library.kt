import com.beust.klaxon.Klaxon
import java.io.File
import java.io.PrintWriter
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.DatabaseMetaData

class Library {
    var books: MutableList<Book> = mutableListOf()
    var movies: MutableList<Movie> = mutableListOf()
    var cds: MutableList<CD> = mutableListOf()

    /**
     * Add a book
     */
    fun addBook(vararg books: Book) {
        for (book in books) {
            this.books.add(book)
        }
    }

    /**
     * Add a movie
     */
    fun addMovie(vararg movies: Movie) {
        for (movie in movies) {
            this.movies.add(movie)
        }
    }

    /**
     * Add a cd
     */
    fun addCD(vararg cds: CD) {
        for (cd in cds) {
            this.cds.add(cd)
        }
    }

    /**
     * Displays all the books
     */
    fun displayBooks() {
        println("========Books=========")
        // Check if they have any books
        if (books.isEmpty()) {
            println("You have no books!")
        } else {
            for (book in books) {
                println(book)
                println("---------------------")
            }
        }
    }

    /**
     * Displays all the movies
     */
    fun displayMovies() {
        println("========Movies========")
        // Check if they have any movies
        if (movies.isEmpty()) {
            println("You have no movies!")
        } else {
            for (movie in movies) {
                println(movie)
                println("---------------------")
            }
        }
    }

    /**
     * Displays all the cds
     */
    fun displayCDs() {
        println("==========CDs=========")
        // Check if they have any cds
        if (cds.isEmpty()) {
            println("You have no CDs!")
        } else {
            for (cd in cds) {
                println(cd)
                println("---------------------")
            }
        }
    }

    /**
     * Displays the title of everything in the library
     */
    fun displayLibrary(books: MutableList<Book> = this.books, movies: MutableList<Movie> = this.movies, cds: MutableList<CD> = this.cds) {
        // What's the greatest number of things we'll have to loop through?
        val num: Int = when {
            books.size >= movies.size && books.size >= cds.size -> books.size
            movies.size >= books.size && movies.size >= cds.size -> movies.size
            else -> cds.size
        }

        // Print
        println("========Books=========\t========Movies========\t==========CDs=========")
        for (i in 0 until num) {
            // Are we outside of the lists range?
            var bookTitle: String =  if (books.size - 1 >= i) books[i].title else " "
            var movieTitle: String = if (movies.size - 1 >= i) movies[i].title else " "
            var cdTitle: String =    if (cds.size - 1 >= i) cds[i].title else " "

            // Create spaces so things look nice
            if (bookTitle.length < 21) {
                while (bookTitle.length < 21) {
                    bookTitle += " "
                }
            }
            if (movieTitle.length < 21) {
                while (movieTitle.length < 21) {
                    movieTitle += " "
                }
            }

            // Print everything out
            println("$bookTitle\t$movieTitle\t$cdTitle")
            println("---------------------\t---------------------\t---------------------")
        }
    }

    fun sortBy(sort: String) {
        val sortedBooks: MutableList<Book>
        val sortedMovies: MutableList<Movie>
        val sortedCds: MutableList<CD>

        // Create sorted lists according to the sort parameter
        if (sort == "title") {
            sortedBooks = books.sortedBy { it.title }.toMutableList()
            sortedMovies = movies.sortedBy { it.title }.toMutableList()
            sortedCds = cds.sortedBy { it.title }.toMutableList()
            displayLibrary(sortedBooks, sortedMovies, sortedCds)
            return
        } else if (sort == "year") {
            sortedBooks = books.sortedBy { it.year }.toMutableList()
            sortedMovies = movies.sortedBy { it.year }.toMutableList()
            sortedCds = cds.sortedBy { it.year }.toMutableList()
            displayLibrary(sortedBooks, sortedMovies, sortedCds)
            return
        } else if (sort == "genre") {
            sortedBooks = books.sortedBy { it.genre[0] }.toMutableList()
            sortedMovies = movies.sortedBy { it.genre[0] }.toMutableList()
            sortedCds = cds.sortedBy { it.genre[0] }.toMutableList()
            displayLibrary(sortedBooks, sortedMovies, sortedCds)
            return
        }
    }

    fun filterBy(field: String, filter: String) {
        var filteredBooks: MutableList<Book> = mutableListOf()
        var filteredMovies: MutableList<Movie> = mutableListOf()
        var filteredCds: MutableList<CD> = mutableListOf()

        for (book in books) {
            if (field == "title") {
                if (book.title == filter) {
                    filteredBooks.add(book)
                }
            }
        }
    }

    fun saveLibrary() {
//        println(books.toString());

//        File("save.txt").writeText(books.toString() + "," + movies.toString() + "," + cds.toString());
        createNewDatabase("test.db")
        createAuthorTable()
        createBookTable()
        createArtistTable()
        createCdTable()
        createMovieTable()
        createGenreTable()
        createBookGenreTable()
        createCdGenreTable()
        createMovieGenreTable()
        createSeriesTable()

//        insertIntoAuthor("Brandon", "Sanderson");
//        insertIntoBook("Skyward", "Skyward", 2014, 2);
    }

    fun loadLibrary() {

    }
}