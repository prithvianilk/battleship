# battleship

## How to play?

```clojure
(ui.repl.main/create-game
  (game.init/create-config [4 4] 3))

; The game has been created!
; Game number: 2
; Board size: [4 4], Ships for each player: 3
```

```clojure
(ui.repl.main/add-player
  2
  game.init/PLAYER-1
  (game.init/create-player "Anisha" [[0 0] [1 1] [2 2]]))
; Added Anisha to the game.

(ui.repl.main/add-player
  2
  game.init/PLAYER-2
  (game.init/create-player "Prithvi" [...]))
; Added Anisha to the game.
```

```clojure
(ui.repl.main/play-move 4 game.init/PLAYER-2 [1 1])  
; It's a direct hit!
```

```clojure
(ui.repl.main/display-current-state 4) ; display's boards with ships hidden

; Prithvi's board:
; (🌊🌊🌊🌊
;   🌊🌊🌊🌊
;   🌊🌊🌊🌊
;   🌊🌊🌊🌊
;   )

; Anisha's board:
; (🌊🌊🌊🌊
;   🌊💀🌊🌊
;   🌊🌊🌊🌊
;   🌊🌊🌊🌊
;   )
```