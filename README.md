# battleship

## Example game init

```clojure
(game.init/create-game
  (game.init/create-config [4 4] 3)
  (game.init/create-player "Prithvi" [[0 0] [1 1] [2 2]])
  (game.init/create-player "Anisha" [[0 0] [2 2] [3 2]])
  )
```

## Example game move play

```clojure
(game.play/play-move game "player-1" [1 1])
```