(ns game.play
  (:use game.init))

(def OUTCOME-HIT "HIT")
(def OUTCOME-MISS "MISS")

(defn get-attacked-entities [game player-id move]
      (let [
            attacked-player's-id (if (= player-id PLAYER-1) PLAYER-2 PLAYER-1)
            attacked-player (get-player game attacked-player's-id)
            attacked-player's-board (get-player's-board game attacked-player's-id)
            attacked-piece (nth (nth attacked-player's-board (get move 0)) (get move 1))
            ]
        [attacked-player's-id attacked-player attacked-player's-board attacked-piece]
        )
      )

(defn get-attacked-piece [game player-id move]
      (nth (get-attacked-entities game player-id move) 3))

(defn update-game
      [game player-id move new-piece]
      (let [
            [attacked-player's-id attacked-player attacked-player's-board _]
            (get-attacked-entities game player-id move)

            updated-row (assoc (vec (nth attacked-player's-board (move 0))) (move 1) new-piece)
            updated-board (assoc (vec attacked-player's-board) (move 0) updated-row)
            ]
        (assoc game (keyword attacked-player's-id) (assoc attacked-player :board updated-board))
        )
      )

(defn play-move
      [game player-id move]
      (let [attacked-piece (get-attacked-piece game player-id move)]
        (cond
          (= attacked-piece PIECE-SEA)
          {:game (update-game game player-id move PIECE-MISSED-SHIP) :outcome OUTCOME-MISS}

          (= attacked-piece PIECE-SHIP)
          {:game (update-game game player-id move PIECE-KILLED-SHIP) :outcome OUTCOME-HIT}

          (= attacked-piece PIECE-KILLED-SHIP)
          {:game game :outcome OUTCOME-MISS}

          (= attacked-piece PIECE-MISSED-SHIP)
          {:game game :outcome OUTCOME-MISS}
          )
        )
      )
