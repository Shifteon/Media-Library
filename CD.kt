data class CD(val title: String, val artist: String, val genre: MutableList<String>, val year: Int) {
//    val title: String = title;
//    val artist: String = artist;
//    val genre: MutableList<String> = genre;
//    val year: Int = year;

    override fun toString(): String {
        return "Title: $title\nArtist: $artist\nGenre: $genre\nYear: $year"
    }
}