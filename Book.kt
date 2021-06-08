class Book(title:String, author:String, genre:MutableList<String>, series:String, year: Int) {
    val title: String = title;
    val author: String = author;
    val genre: MutableList<String> = genre;
    val series: String = series;
    val year: Int = year;

    override fun toString(): String {
        return "Title: $title\nAuthor: $author\nGenre: $genre\nSeries: $series\nYear: $year";
    }
}