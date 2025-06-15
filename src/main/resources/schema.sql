CREATE TABLE IF NOT EXISTS serials (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_tmdb INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    original_name VARCHAR(100) NOT NULL,
    first_air_date DATE NOT NULL,
    overview VARCHAR(1000) NOT NULL,
    origin_country VARCHAR(100) NOT NULL,
    poster_path VARCHAR(1000) NOT NULL
);