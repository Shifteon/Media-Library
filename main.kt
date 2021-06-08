fun main() {
    val book = Book("Skyward", "Brandon Sanderson", mutableListOf("Science Fiction"), "Skyward");
    val movie = Movie("Avengers", 2011, mutableListOf("Superhero"), "Marvel");
    var library = Library();

    library.addBook(book);
    library.addMovie(movie);

    library.displayBooks();
    library.displayMovies();
}