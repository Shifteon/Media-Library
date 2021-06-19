data class Movie(val title: String, val genre: MutableList<String>, val series: String, val year: Int) {

    override fun toString(): String {
        return "Title: $title\nGenre: $genre\nSeries: $series\nYear: $year"
    }
}