import com.beust.klaxon.Klaxon
import java.io.File
import java.io.PrintWriter
import java.sql.*

class Library {
    var books: MutableList<Book> = mutableListOf()
    var movies: MutableList<Movie> = mutableListOf()
    var cds: MutableList<CD> = mutableListOf()

    val databaseName = "media.db"
    // The database connection
    val conn = connect(databaseName)

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
        val sql = "select b.title, a.first_name || \" \" || a.last_name as name,\n" +
                "s.series_name, g.genre_name, b.year\n" +
                "from book b inner join author a\n" +
                "on b.author_id = a.author_id\n" +
                "inner join series s\n" +
                "on b.series_id = s.series_id\n" +
                "inner join book_genre bg\n" +
                "on bg.book_id = b.book_id\n" +
                "inner join genre g \n" +
                "on bg.genre_id = g.genre_id;"
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        val result = pstmt?.executeQuery()


        println("========Books=========")
        // Check if they have any books
        while(result!!.next()) {
            println("Title: ${result.getString("title")}")
            println("Author: ${result.getString("name")}")
            println("Genre: ${result.getString("genre_name")}")
            println("Series: ${result.getString("series_name")}")
            println("Year: ${result.getString("year")}")
            println("---------------------")
        }
    }

    /**
     * Displays all the movies
     */
    fun displayMovies() {
        val sql = "select m.title, g.genre_name, s.series_name, m.year\n" +
                "from movie m inner join movie_genre mg\n" +
                "on m.movie_id = mg.movie_id\n" +
                "inner join genre g\n" +
                "on mg.genre_id = g.genre_id\n" +
                "inner join series s\n" +
                "on m.series_id = s.series_id;"

        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        val result = pstmt?.executeQuery()


        println("=======Movies=========")
        // Check if they have any books
        while(result!!.next()) {
            println("Title: ${result.getString("title")}")
            println("Genre: ${result.getString("genre_name")}")
            println("Series: ${result.getString("series_name")}")
            println("Year: ${result.getString("year")}")
            println("---------------------")
        }

    }

    /**
     * Create a list of the media
     */
    private fun retrieveMedia(sql: String): MutableList<String> {
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        val result = pstmt?.executeQuery()
        var list: MutableList<String> = mutableListOf()

        while (result!!.next()) {
            list.add(result.getString("title"))
        }

        return list
    }

    /**
     * Displays all the cds
     */
    fun displayCDs() {
        val sql = "SELECT c.title, a.first_name || \" \" || a.last_name as name, g.genre_name, c.year\n" +
                "FROM cd c INNER JOIN cd_genre cg\n" +
                "ON c.cd_id = cg.cd_id\n" +
                "INNER JOIN genre g\n" +
                "ON g.genre_id = cg.genre_id\n" +
                "INNER JOIN artist a\n" +
                "ON c.artist_id = a.artist_id;"
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        val result = pstmt?.executeQuery()

        println("==========CDs=========")
        // Check if they have any cds
        while(result!!.next()) {
            println("Title: ${result.getString("title")}")
            println("Artist: ${result.getString("name")}")
            println("Genre: ${result.getString("genre_name")}")
            println("Year: ${result.getString("year")}")
            println("---------------------")
        }
    }

    /**
     * Displays the title of everything in the library
     */
    fun displayLibrary(books: MutableList<Book> = this.books, movies: MutableList<Movie> = this.movies, cds: MutableList<CD> = this.cds) {
        val bookSql = "SELECT title FROM book;"
        val movieSql = "SELECT title FROM movie;"
        val cdSql = "SELECT title FROM cd;"

        // Retrieve data from database and create temporary lists
        val tempBooks = retrieveMedia(bookSql)
        val tempMovies = retrieveMedia(movieSql)
        val tempCds = retrieveMedia(cdSql)

        for (book in books) {
            tempBooks.add(book.title)
        }
        for (movie in movies) {
            tempMovies.add(movie.title)
        }
        for (cd in cds) {
            tempCds.add(cd.title)
        }

        // What's the greatest number of things we'll have to loop through?
        val num: Int = when {
            tempBooks.size >= tempMovies.size && tempBooks.size >= tempCds.size -> tempBooks.size
            tempMovies.size >= tempBooks.size && tempMovies.size >= tempCds.size -> tempMovies.size
            else -> tempCds.size
        }

        // Print
        println("========Books=========\t========Movies========\t==========Cds=========")
        for (i in 0 until num) {
            // Are we outside of the lists range?
            var bookTitle: String =  if (tempBooks.size - 1 >= i) tempBooks[i] else " "
            var movieTitle: String = if (tempMovies.size - 1 >= i) tempMovies[i] else " "
            var cdTitle: String =    if (tempCds.size - 1 >= i) tempCds[i] else " "

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

    /**
     * Create the database if it doesn't exist
     */
    fun createDatabase() {
        val file: File = File("C:/sqlite3/db/$databaseName")
        // Check if the database already exists
        if (!file.exists()) {
            // Create the database
            createNewDatabase(databaseName)
            // Create all the tables
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
        }
    }

    /**
     * Returns the id from the sql statement provided
     * Provide statements that return only one id
     */
    private fun getId(sql: String, items: List<String>, id: String): Int {
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)

        for (i in 0 until items.size) {
            pstmt?.setString(i + 1, items[i])
        }

        val curr_id = pstmt?.executeQuery()
        if (curr_id!!.next()) {
            return curr_id.getInt(id)
        } else {
            return -1
        }
    }

    /**
     * Saves the library to the database
     */
    fun saveLibrary() {
        try {
            for (book in books) {
                println(book.title)
                var author_name = book.author.split(' ').toMutableList()
                // Make sure author_name isn't too big
                while (author_name.size > 2) {
                    author_name[0] = author_name[0] + "_" + author_name[1]
                    author_name.removeAt(1)
                }

                // All of the SQL queries we need
                val authorSql: String = "SELECT author_id FROM author WHERE first_name = ? AND last_name = ?;"
                val seriesSql: String = "SELECT series_id FROM series WHERE series_name = ?;"
                val genreSql: String = "SELECT genre_id FROM genre WHERE genre_name = ?;"
                val bookSql: String = "SELECT book_id FROM book ORDER BY book_id DESC LIMIT 1;"

                // Get the author_id
                var author_id = getId(authorSql, author_name, "author_id")
                // Get the series_id
                var series_id = getId(seriesSql, listOf(book.series), "series_id")
                // Get the genre_id
                var genre_id: MutableList<Int> = mutableListOf()
                var count = 0
                for (genre in book.genre) {
                    genre_id.add(getId(genreSql, listOf(genre), "genre_id"))
                    // Insert the genre if it doesn't exist
                    if (genre_id[count] == -1) {
                        insertIntoGenre(genre, conn)
                        genre_id[count] = getId(genreSql, listOf(genre), "genre_id")
                        count++
                    }
                }

                // If either of the ids are null, we need to add them to the database
                if (author_id == -1) {
                    insertIntoAuthor(author_name[0], author_name[1], conn)
                    println("We added the author!")
                    // Set the author_id to the author we just added
                    author_id = getId(authorSql, author_name, "author_id")
                }
                if (series_id == -1) {
                    insertIntoSeries(book.series, conn)
                    println("We added the series!")
                    // Set series_id to the series we just added
                    series_id = getId(seriesSql, listOf(book.series), "series_id")
                }

                // Add the book to the database
                insertIntoBook(book.title, book.year, author_id, series_id, conn)
                println("Book added!")
                // Add the genres to book_genre
                val book_id = getId(bookSql, listOf(), "book_id")
                for (genre in genre_id) {
                    insertIntoMediaGenre(genre, book_id, 0, conn)
                    println("Book genre added!")
                }
            }

            for (cd in cds) {
                var artist_name = cd.artist.split(" ").toMutableList()
                // Make sure artist_name isn't too big
                while (artist_name.size > 2) {
                    artist_name[0] = artist_name[0] + "_" + artist_name[1]
                    artist_name.removeAt(1)
                }

                // All of the SQL queries we need
                val sql: String = "SELECT artist_id FROM artist WHERE first_name = ? AND last_name = ?;"
                val genreSql: String = "SELECT genre_id FROM genre WHERE genre_name = ?;"
                val cdSql: String = "SELECT cd_id FROM cd ORDER BY cd_id DESC LIMIT 1;"

                // Get the artist_id
                var artist_id = getId(sql, artist_name, "artist_id")
                // Get the genre_id
                var genre_id: MutableList<Int> = mutableListOf()
                var count = 0
                for (genre in cd.genre) {
                    genre_id.add(getId(genreSql, listOf(genre), "genre_id"))
                    // Insert the genre if it doesn't exist
                    if (genre_id[count] == -1) {
                        insertIntoGenre(genre, conn)
                        genre_id[count] = getId(genreSql, listOf(genre), "genre_id")
                        count++
                    }
                }

                if (artist_id == -1) {
                    insertIntoArtist(artist_name[0], artist_name[1], conn)
                    println("Artist added!")
                    artist_id = getId(sql, artist_name, "artist_id")
                }

                insertIntoCd(cd.title, cd.year, artist_id, conn)
                println("cd added!")
                val cd_id = getId(cdSql, listOf(), "cd_id")
                for (genre in genre_id) {
                    insertIntoMediaGenre(genre, cd_id, 1, conn)
                    println("Cd genre added!")
                }
            }

            for (movie in movies) {
                // All of the SQL queries we need
                val sql = "SELECT series_id FROM series WHERE series_name = ?;"
                val genreSql = "SELECT genre_id FROM genre WHERE genre_name = ?;"
                val movieSql = "SELECT movie_id FROM movie ORDER BY movie_id DESC LIMIT 1;"

                var series_id = getId(sql, listOf(movie.series), "series_id")
                // Get the genre_id
                var genre_id: MutableList<Int> = mutableListOf()
                var count = 0
                for (genre in movie.genre) {
                    genre_id.add(getId(genreSql, listOf(genre), "genre_id"))
                    // Insert the genre if it doesn't exist
                    if (genre_id[count] == -1) {
                        insertIntoGenre(genre, conn)
                        genre_id[count] = getId(genreSql, listOf(genre), "genre_id")
                        count++
                    }
                }

                if (series_id == -1) {
                    insertIntoSeries(movie.series, conn)
                    series_id = getId(sql, listOf(movie.series), "series_id")
                }

                insertIntoMovie(movie.title, movie.year, series_id, conn)
                println("cd added!")
                val movie_id = getId(movieSql, listOf(), "movie_id")
                for (genre in genre_id) {
                    insertIntoMediaGenre(genre, movie_id, 2, conn)
                    println("Movie genre added!")
                }
            }
            // Clear the lists. Everything is in the database
            books.clear()
            cds.clear()
            movies.clear()

        } catch (e: SQLException) {
            println("ERROR: ${e.message}")
        }
    }
}