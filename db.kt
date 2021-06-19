import java.sql.*

var globalFilename: String = ""

fun createNewDatabase(filename: String) {
    val url = "jdbc:sqlite:C:/sqlite3/db/$filename"
    globalFilename = filename
    try {
        val conn: Connection? = DriverManager.getConnection(url)
        if (conn != null) {
            val meta: DatabaseMetaData = conn.metaData
            println("The driver name is ${meta.driverName}")
            println("A new database has been created")
        }
    } catch (e: SQLException) {
        println(e.message)
    }
}

fun connect(filename: String): Connection? {
    val url = "jdbc:sqlite:C:/sqlite3/db/$filename"
    globalFilename = filename
    var conn: Connection? = null
    try {
        conn = DriverManager.getConnection(url)
    } catch (e: SQLException) {
        println(e.message)
    }

    return conn
}

fun createTable(sql: String) {
    val url = "jdbc:sqlite:C://sqlite3/db/media.db"
    try {
        val conn: Connection = DriverManager.getConnection(url)
        val stmt: Statement = conn.createStatement()
        stmt.execute(sql)
    } catch (e: SQLException) {
        println(e.message)
    }
}

fun createAuthorTable() {
    val sql = "CREATE TABLE IF NOT EXISTS author (\n" +
            "        author_id INTEGER PRIMARY KEY,\n" +
            "        first_name TEXT NOT NULL,\n" +
            "        last_name TEXT NOT NULL\n" +
            ");"
    createTable(sql)
}

fun createBookTable() {
    val sql = "CREATE TABLE IF NOT EXISTS book (\n" +
            "       book_id INTEGER PRIMARY KEY,\n" +
            "       title TEXT NOT NULL,\n" +
            "       year INTEGER(4),\n" +
            "       author_id INTEGER,\n" +
            "       series_id INTEGER,\n" +
            "       FOREIGN KEY(author_id) REFERENCES author(author_id),\n" +
            "       FOREIGN KEY(series_id) REFERENCES series(series_id)\n" +
            ");"

    createTable(sql)
}

fun createArtistTable() {
    val sql = "CREATE TABLE IF NOT EXISTS artist (\n" +
            "       artist_id INTEGER PRIMARY KEY,\n" +
            "       first_name TEXT NOT NULL,\n" +
            "       last_name TEXT NOT NULL\n" +
            ");"

    createTable(sql)
}

fun createCdTable() {
    val sql = "CREATE TABLE IF NOT EXISTS cd (\n" +
            "       cd_id INTEGER PRIMARY KEY,\n" +
            "       title TEXT NOT NULL,\n" +
            "       year INTEGER(4),\n" +
            "       artist_id INTEGER,\n" +
            "       FOREIGN KEY(artist_id) REFERENCES artist(artist_id)\n" +
            ");"

    createTable(sql)
}

fun createMovieTable() {
    val sql = "CREATE TABLE IF NOT EXISTS movie (\n" +
            "       movie_id INTEGER PRIMARY KEY,\n" +
            "       title TEXT NOT NULL,\n" +
            "       year INTEGER(4),\n" +
            "       series_id INTEGER,\n" +
            "       FOREIGN KEY(series_id) REFERENCES series(series_id)\n" +
            ");"

    createTable(sql)
}

fun createSeriesTable() {
    val sql = "CREATE TABLE IF NOT EXISTS series (\n" +
            "       series_id INTEGER PRIMARY KEY,\n" +
            "       series_name TEXT NOT NULL\n" +
            ");"

    createTable(sql)
}

fun createGenreTable() {
    val sql = "CREATE TABLE IF NOT EXISTS genre (\n" +
            "       genre_id   INTEGER PRIMARY KEY,\n" +
            "       genre_name TEXT NOT NULL\n" +
            ");"

    createTable(sql)
}

fun createBookGenreTable() {
    val sql = "CREATE TABLE IF NOT EXISTS book_genre (\n" +
            "       genre_id INTEGER NOT NULL,\n" +
            "       book_id  INTEGER NOT NULL,\n" +
            "       FOREIGN KEY(genre_id) REFERENCES genre(genre_id),\n" +
            "       FOREIGN KEY(book_id)  REFERENCES book(book_id)\n" +
            ");"

    createTable(sql)
}

fun createCdGenreTable() {
    val sql = "CREATE TABLE IF NOT EXISTS cd_genre (\n" +
            "       genre_id INTEGER NOT NULL,\n" +
            "       cd_id    INTEGER NOT NULL,\n" +
            "       FOREIGN KEY(genre_id) REFERENCES genre(genre_id),\n" +
            "       FOREIGN KEY(cd_id)    REFERENCES cd(cd_id)\n" +
            ");"

    createTable(sql)
}

fun createMovieGenreTable() {
    val sql = "CREATE TABLE IF NOT EXISTS movie_genre (\n" +
            "       genre_id INTEGER NOT NULL,\n" +
            "       movie_id INTEGER NOT NULL,\n" +
            "       FOREIGN KEY(genre_id) REFERENCES genre(genre_id),\n" +
            "       FOREIGN KEY(movie_id) REFERENCES movie(movie_id)\n" +
            ");"

    createTable(sql)
}

fun insertIntoAuthor(first_name: String, last_name: String, conn: Connection? = connect(globalFilename)) {
    val sql = "INSERT INTO author(first_name, last_name) VALUES(?,?);"
    try {
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        if (pstmt != null) {
            pstmt.setString(1, first_name)
            pstmt.setString(2, last_name)
            pstmt.executeUpdate()
        }
    } catch (e: SQLException) {
        println(e.message)
    }
}

fun insertIntoBook(title: String, year: Int, author_id: Int, series_id: Int, conn: Connection? = connect(globalFilename)) {
    val sql = "INSERT INTO book(title, year, author_id, series_id) VALUES(?, ?, ?, ?);"
    try {
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        if (pstmt != null) {
            pstmt.setString(1, title)
            pstmt.setInt(2, year)
            pstmt.setInt(3, author_id)
            pstmt.setInt(4, series_id)
            pstmt.executeUpdate()
        }
    } catch (e: SQLException) {
        println(e.message)
    }
}

fun insertIntoArtist(first_name: String, last_name: String, conn: Connection? = connect(globalFilename)) {
    val sql = "INSERT INTO artist(first_name, last_name) VALUES(?,?);"
    try {
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        if (pstmt != null) {
            pstmt.setString(1, first_name)
            pstmt.setString(2, last_name)
            pstmt.executeUpdate()
        }
    } catch (e: SQLException) {
        println(e.message)
    }
}

fun insertIntoCd(title: String, year: Int, artist_id: Int, conn: Connection? = connect(globalFilename)) {
    val sql = "INSERT INTO cd(title, year, artist_id) VALUES(?, ?, ?);"
    try {
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        if (pstmt != null) {
            pstmt.setString(1, title)
            pstmt.setInt(2, year)
            pstmt.setInt(3, artist_id)
            pstmt.executeUpdate()
        }
    } catch (e: SQLException) {
        println(e.message)
    }
}

fun insertIntoMovie(title: String, year: Int, series_id: Int, conn: Connection? = connect(globalFilename)) {
    val sql = "INSERT INTO movie(title, year, series_id) VALUES(?, ?, ?);"
    try {
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        if (pstmt != null) {
            pstmt.setString(1, title)
            pstmt.setInt(2, year)
            pstmt.setInt(3, series_id)
            pstmt.executeUpdate()
        }
    } catch (e: SQLException) {
        println(e.message)
    }
}

fun insertIntoGenre(genre_name: String, conn: Connection? = connect(globalFilename)) {
    val sql = "INSERT INTO genre(genre_name) VALUES(?);"
    try {
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        if (pstmt != null) {
            pstmt.setString(1, genre_name)
            pstmt.executeUpdate()
        }
    } catch (e: SQLException) {
        println(e.message)
    }
}

/**
 * Insert into *_genre. Provide the media type:
 *                      0 = book
 *                      1 = cd
 *                      2 = movie
 */
fun insertIntoMediaGenre(genre_id: Int, media_id: Int, mediaType: Int, conn: Connection? = connect(globalFilename)) {
    // Select which insert statement to use. Allows us to do this in one function
    val sql: String = when (mediaType) {
        0 -> "INSERT INTO book_genre(genre_id, book_id) VALUES(?, ?);"
        1 -> "INSERT INTO cd_genre(genre_id, cd_id) VALUES(?, ?);"
        2 -> "INSERT INTO movie_genre(genre_id, movie_id) VALUES(?, ?);"
        else -> return
    }
    try {
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        if (pstmt != null) {
            pstmt.setInt(1, genre_id)
            pstmt.setInt(2, media_id)
            pstmt.executeUpdate()
        }
    } catch (e: SQLException) {
        println(e.message)
    }
}

fun insertIntoSeries(series_name: String, conn: Connection? = connect(globalFilename)) {
    val sql = "INSERT INTO series(series_name) VALUES(?);"
    try {
        val pstmt: PreparedStatement? = conn?.prepareStatement(sql)
        if (pstmt != null) {
            pstmt.setString(1, series_name)
            pstmt.executeUpdate()
        }
    } catch (e: SQLException) {
        println(e.message)
    }
}