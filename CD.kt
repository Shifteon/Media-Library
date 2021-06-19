data class CD(val title: String, val artist: String, val genre: MutableList<String>, val year: Int) {

    override fun toString(): String {
        return "Title: $title\nArtist: $artist\nGenre: $genre\nYear: $year"
    }
}