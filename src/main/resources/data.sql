---------------------------------------
-- MEDIA
---------------------------------------

INSERT INTO media (id, title, album_id, type, url)
VALUES
    (1, 'Everything Black', NULL, 'SONG', 'https://open.spotify.com/track/3UEnF6y5tyHVtMzldS3svp?nd=1&dlsi=c051a513ae45432c'),
    (2, 'Vois sur ton chemin - DnB Remix', NULL, 'SONG', 'https://open.spotify.com/track/7cmJwhf4ERDBJPTe2ab8eW?nd=1&dlsi=0799729594c74904'),
    (3, 'Tous les mêmes', NULL, 'SONG', 'https://open.spotify.com/track/6M4nkEPZMj58acftDRTuKL?nd=1&dlsi=0ee6827613fa47a9'),
    (4, 'Ode to My Next Life', NULL, 'SONG', 'https://open.spotify.com/track/6GyhcNTsESxJf96toAGrKC?nd=1&dlsi=ddb4e0ad36e04a55'),
    (5, 'Burn - Remastered 2004', NULL, 'SONG', 'https://open.spotify.com/track/1iW2ktyrQHNKZwFTvgP0Ta?nd=1&dlsi=eab88542b9414849'),
    (6, 'Money for Nothing - Remastered 1996', NULL, 'SONG', 'https://open.spotify.com/track/4nFNJmjfgBF7jwv2oBC45b?nd=1&dlsi=9c8f02861719479f'),
/* Song 7 skipped: Need Sleep Token ID */
    (8, 'Deutschland', NULL, 'VIDEO', 'https://www.youtube.com/watch?v=NeQM1c-XCDc');

---------------------------------------
-- MEDIA_ARTIST
---------------------------------------

INSERT INTO media_artist (media_id, artist_id)
VALUES
    (1, 1),  -- Everything Black → Unlike Pluto
    (2, 2),  -- Vois sur ton chemin → Bennet
    (3, 3),  -- Tous les mêmes → Stromae
    (4, 4),  -- Ode to My Next Life → Kishi Bashi
    (5, 5),  -- Burn → Deep Purple
    (6, 6),  -- Money for Nothing → Dire Straits
-- (7, ?)  -- The Summoning → Sleep Token (ID missing)
    (8, 7);  -- Deutschland → Rammstein

---------------------------------------
-- MEDIA_GENRE
---------------------------------------

INSERT INTO media_genre (media_id, genre_id)
VALUES
-- Everything Black
(1, 1),
(1, 2),

-- Vois sur ton chemin
(2, 1),
(2, 2),
(2, 3),

-- Tous les mêmes
(3, 2),
(3, 4),

-- Ode to My Next Life
(4, 5),
(4, 6),

-- Burn
(5, 7),

-- Money for Nothing
(6, 7),

-- Deutschland
(8, 9);
