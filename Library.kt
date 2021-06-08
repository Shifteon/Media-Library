class Library {
    var books: MutableList<Book> = mutableListOf();
    var movies: MutableList<Movie> = mutableListOf();
    var cds: MutableList<CD> = mutableListOf();

    /**
     * Add a book
     */
    fun addBook(book: Book) {
        books.add(book);
    }

    /**
     * Add a movie
     */
    fun addMovie(movie: Movie) {
        movies.add(movie);
    }

    /**
     * Add a cd
     */
    fun addCD(cd: CD) {
        cds.add(cd);
    }

    /**
     * Displays all the books
     */
    fun displayBooks() {
        println("========Books=========");
        // Check if they have any books
        if (books.isEmpty()) {
            println("You have no books!");
        } else {
            for (book in books) {
                println(book);
                println("---------------------");
            }
        }
    }

    /**
     * Displays all the movies
     */
    fun displayMovies() {
        println("========Movies========");
        // Check if they have any movies
        if (movies.isEmpty()) {
            println("You have no movies!")
        } else {
            for (movie in movies) {
                println(movie);
                println("---------------------");
            }
        }
    }

    /**
     * Displays all the cds
     */
    fun displayCDs() {
        println("==========CDs=========");
        // Check if they have any cds
        if (cds.isEmpty()) {
            println("You have no CDs!");
        } else {
            for (cd in cds) {
                println(cd);
                println("---------------------");
            }
        }
    }

    /**
     * Displays the title of everything in the library
     */
    fun displayLibrary() {
        // What's the greatest number of things we'll have to loop through?
        val num: Int = when {
            books.size > movies.size && books.size > cds.size -> books.size;
            movies.size > books.size && movies.size > cds.size -> movies.size;
            else -> cds.size;
        }

        // Print
        println("========Books=========\t========Movies========\t==========CDs=========");
        for (i in 0 until num) {
            // Are we outside of the lists range?
            var bookTitle: String =  if (books.size - 1 >= i) books[i].title else " ";
            var movieTitle: String = if (movies.size - 1 >= i) movies[i].title else " ";
            var cdTitle: String =    if (cds.size - 1 >= i) cds[i].title else " ";

            // Create spaces so things look nice
            if (bookTitle.length < 21) {
                while (bookTitle.length < 21) {
                    bookTitle += " ";
                }
            }
            if (movieTitle.length < 21) {
                while (movieTitle.length < 21) {
                    movieTitle += " ";
                }
            }

            // Print everything out
            println("$bookTitle\t$movieTitle\t$cdTitle");
            println("---------------------\t---------------------\t---------------------");
        }
    }
}