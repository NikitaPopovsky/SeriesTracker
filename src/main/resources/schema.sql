CREATE TABLE IF NOT EXISTS serials (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_serial_tmdb INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    original_name VARCHAR(100),
    first_air_date DATE,
    overview VARCHAR(1000),
    origin_country VARCHAR(100),
    poster_path VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS episodes (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   id_serial_tmdb INT NOT NULL,
   season_number TINYINT NOT NULL,
   episode_number TINYINT NOT NULL,
   name VARCHAR(100) NOT NULL,
   air_date DATE
);

CREATE TABLE IF NOT EXISTS lastseasones (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    serial INT NOT NULL,
    season_number TINYINT NOT NULL,
    episode_count TINYINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    air_date DATE
);