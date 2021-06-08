class Movie(title: String, year: Int, genre: MutableList<String>, series: String) {
    val title: String = title;
    val year: Int = year;
    val genre: MutableList<String> = genre;
    val series: String = series;

    override fun toString(): String {
        return "Title: $title\nYear: $year\nGenre: $genre\nSeries: $series";
    }
}