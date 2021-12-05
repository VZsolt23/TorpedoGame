CREATE TABLE game_saves (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    playermap varchar(255) NOT NULL,
    playershootable varchar(255) NOT NULL,
    aimap varchar(255) NOT NULL,
    aishootable varchar(255) NOT NULL
);