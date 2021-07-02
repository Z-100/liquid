SELECT game.title, os.type
FROM game
         JOIN tt_game_os ON game.id = tt_game_os.game_id
         JOIN os ON tt_game_os.os_id = os.id
WHERE os.type = MacOS;

SELECT game.id, game.title, game.description, game.price, category.category, game.thumbnail, os.type
FROM game
         JOIN tt_game_category ON game.id = tt_game_category.game_id
         JOIN category ON tt_game_category.category_id = category.id
         JOIN tt_game_os ON game.id = tt_game_os.game_id
         JOIN os ON tt_game_os.os_id = os.id
WHERE game.price < 24
  AND os.type = 'MacOS'
GROUP BY title


