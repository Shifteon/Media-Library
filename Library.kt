class Library {
    var books: MutableList<Book> = mutableListOf();
    var movies: MutableList<Movie> = mutableListOf();

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
}