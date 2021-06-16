data class Book(val title:String, val author:String, val genre:MutableList<String>, val series:String, val year: Int) {
//    title = title;
//    val author: String = author;
//    val genre: MutableList<String> = genre;
//    val series: String = series;
//    val year: Int = year;

    override fun toString(): String {
        return "Title: $title\nAuthor: $author\nGenre: $genre\nSeries: $series\nYear: $year"
    }
}