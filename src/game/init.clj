(ns game.init)

(def PLAYER-1 "player-1")
(def PLAYER-2 "player-2")

(def PIECE-SHIP "⛴")
(def PIECE-SEA "\uD83C\uDF0A")
(def PIECE-KILLED-SHIP "\uD83D\uDC80")
(def PIECE-MISSED-SHIP "❌")

(defn get-board-size [game] ((game :config) :board-size))
(defn get-player [game player-id] (game (keyword player-id)))
(defn get-player's-name [game player-id] ((game (keyword player-id)) :name))
(defn get-player's-ships [game player-id] ((game (keyword player-id)) :ships))
(defn get-player's-board [game player-id] ((game (keyword player-id)) :board))

(defn create-board-row
      [row-index board-column-count ships]
      (map
        #(if (contains? ships [row-index %]) PIECE-SHIP PIECE-SEA)
        (range board-column-count)
        )
      )

(defn create-board
      [board-size ships]
      (let [ships (set ships)]
        (map #(create-board-row % (board-size 1) ships) (range (board-size 0)))
        )
      )

(defn create-config
      [board-size ships-per-player]
      {:board-size board-size :ships-per-player ships-per-player}
      )

(defn create-player [name ships] {:name name :ships ships})

(defn create-game
      [config player-1 player-2]
      {
       :id                (rand-int 10)
       :config            config
       (keyword PLAYER-1) (assoc player-1 :board (create-board (config :board-size) (player-1 :ships)))
       (keyword PLAYER-2) (assoc player-2 :board (create-board (config :board-size) (player-2 :ships)))
       }
      )

(defn pretty-print-player-board
      [game player]
      (let [board (create-board (get-board-size game) (get-player's-ships game player))]
        (for [i board] (println i))
        )
      )
