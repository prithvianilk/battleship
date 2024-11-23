(ns game.play
  (:use game.init))

(defn play-move
      [game player-id move]
      (let [
            attacked-player's-id (if (= player-id "player-1") "player-2" "player-1")
            attacked-player's-board (get-player's-board game attacked-player's-id)
            attacked-entity (nth (nth attacked-player's-board (get move 0)) (get move 1))
            ]
        (cond
          (= attacked-entity SEA)
          (println "Oh no :( you missed")

          (= attacked-entity SHIP)
          (println "Direct Hit!")

          (= attacked-entity KILLED_SHIP) (println "Bro, you already killed this")

          (= attacked-entity MISSED_SHIP) (println "You already missed here :/")

          :else (println attacked-player's-id attacked-player's-board attacked-entity)
          )
        )
      )
