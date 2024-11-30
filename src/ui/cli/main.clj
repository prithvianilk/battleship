(ns ui.cli.main
  (:require
    [game.init :as init]
    [game.repository.memory :as repository]
    [game.play :as play]))

(defn create-game [config]
      (let [game (init/create-game config)]
        (repository/add game)
        (println
          "The game has been created!\n"
          (format "Game number: %d\n" (game :id))
          (format "Board size: %s, Ships for each player: %d\n"
                  (init/get-board-size game)
                  (init/get-ships-per-player game))
          )
        ))

(defn add-player [game-id player-id player]
      (let [game (repository/get-by-id game-id)]
        (if (init/player-exists? game player-id)
          (println (format "Player %s already exists" player-id))

          ((repository/add (game.init/add-player game player-id player))
           (println (format "Added %s to the game." (player :name))))
          )
        ))

(defn play-move [game-id player-id move]
      (let [game (repository/get-by-id game-id)
            {game-after-move :game outcome :outcome} (play/play-move game player-id move)]
        (repository/add game-after-move)
        (if (= outcome play/OUTCOME-HIT)
          (println "It's a direct hit!")
          (println "It's a miss :("))
        ))