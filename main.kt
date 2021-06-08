fun main() {
    val book = Book("Skyward", "Brandon Sanderson", mutableListOf("Science Fiction"), "Skyward", 2014);
    val movie = Movie("Avengers", mutableListOf("Superhero"), "Marvel", 2011);
    val cd = CD("Windy Day", "Oh My Girl", mutableListOf("K-Pop"), 2016);
    val book2 = Book("Little Women", "Louisa May Alcott", mutableListOf("Novel", "Fiction", "Children's Literature"), "Little Women",1868);
    var library = Library();

    library.addBook(book);
    library.addBook(book);
    library.addBook(book2);
    library.addBook(book2);
    library.addBook(book2);
    library.addMovie(movie);
    library.addMovie(movie);
    library.addMovie(movie);
    library.addMovie(movie);
    library.addCD(cd);
    library.addCD(cd);
    library.addCD(cd);
    library.addCD(cd);

//    library.displayBooks();
//    library.displayMovies();
//    library.displayCDs();
    library.displayLibrary();
}