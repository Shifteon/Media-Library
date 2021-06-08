class Movie(title: String, genre: MutableList<String>, series: String, year: Int) {
    val title: String = title;
    val year: Int = year;
    val genre: MutableList<String> = genre;
    val series: String = series;

    override fun toString(): String {
        return "Title: $title\nGenre: $genre\nSeries: $series\nYear: $year";
    }
}