(ns game.repository.memory)

(def store (atom {}))

(defn add [game] (swap! store assoc (game :id) game))

(defn get-all [] @store)

(defn get-by-id [id] (@store id))
