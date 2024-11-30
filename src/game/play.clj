(ns game.play
  (:use game.init))

(def OUTCOME-HIT "HIT")
(def OUTCOME-MISS "MISS")

(defn nth-and-mth
      ([s n m]
       (nth (nth n s) m))

      ([s pos]
       (nth (nth s (pos 0)) (pos 1)))
      )

(defn get-attacked-entities [game player-id move]
      (let [
            attacked-player's-id (if (= player-id PLAYER-1) PLAYER-2 PLAYER-1)
            attacked-player (get-player game attacked-player's-id)
            attacked-player's-board (get-player's-board game attacked-player's-id)
            attacked-piece (nth-and-mth attacked-player's-board move)]
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
            updated-board (assoc (vec attacked-player's-board) (move 0) updated-row)]
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

(defn hide-ships-in-row
      [row]
      (map #(if (= % PIECE-SHIP) PIECE-SEA %) row))

(defn hide-ships-in-board [board] (map hide-ships-in-row board))

(defn hide-ships-in-game
      [game]
      (let [player-1's-board (get-player's-board game PLAYER-1)
            player-2's-board (get-player's-board game PLAYER-2)]
        [(hide-ships-in-board player-1's-board) (hide-ships-in-board player-2's-board)])
      )