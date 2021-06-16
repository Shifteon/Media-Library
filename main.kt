import java.util.Scanner;

fun addBook(): Book {
    val scanner = Scanner(System. `in`);
    print("Please enter title: ");
    val title: String = readLine()!!;
    print("Please enter author: ");
    val author: String = readLine()!!;
    print("Please enter genres separated by a comma (no space): ");
    val genre: MutableList<String> = readLine()!!.split(',').toMutableList();
    print("Please enter series: ");
    val series: String = readLine()!!;
    print("Please enter year: ");
    val year: Int = scanner.nextInt();

    val book: Book = Book(title, author, genre, series, year);
    return book;
}

fun addMovie(): Movie {
    val scanner = Scanner(System. `in`);
    print("Please enter title: ");
    val title: String = readLine()!!;
    print("Please enter genres separated by a comma (no space): ");
    val genre: MutableList<String> = readLine()!!.split(',').toMutableList();
    print("Please enter series: ");
    val series: String = readLine()!!;
    print("Please enter year: ");
    val year: Int = scanner.nextInt();

    val movie: Movie = Movie(title, genre, series, year);
    return movie;
}

fun addCD(): CD {
    val scanner = Scanner(System. `in`);
    print("Please enter title: ");
    val title: String = readLine()!!;
    print("Please enter artist: ");
    val artist: String = readLine()!!;
    print("Please enter genres separated by a comma (no space): ");
    val genre: MutableList<String> = readLine()!!.split(',').toMutableList();
    print("Please enter year: ");
    val year: Int = scanner.nextInt();

    val cd: CD = CD(title, artist, genre, year);
    return cd;
}

fun main() {
    val book = Book("Skyward", "Brandon Sanderson", mutableListOf("Science Fiction"), "Skyward", 2014);
    val book2 = Book("Little Women", "Louisa May Alcott", mutableListOf("Novel", "Fiction", "Children's Literature"), "Little Women",1868);
//    val book = addBook();
    val movie = Movie("Avengers", mutableListOf("Superhero"), "Marvel", 2011);
    val movie2 = Movie("Whisper of the Heart", mutableListOf("Anime", "Romance"), "Whisper of the Heart", 1995);
    val cd = CD("Windy Day", "Oh My Girl", mutableListOf("K-Pop"), 2016);
    var library = Library();

    library.addBook(book, book2);
    library.addMovie(movie, movie2);
    library.addCD(cd);

//    library.displayBooks();
//    library.displayMovies();
//    library.displayCDs();
//    library.displayLibrary();
//    library.sortBy("year");
    library.saveLibrary();
//    library.loadLibrary();
}

