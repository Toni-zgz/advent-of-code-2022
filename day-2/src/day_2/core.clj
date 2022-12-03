(ns day-2.core
  (:gen-class)
  (:require [clojure.string :as st]))

(defn -main []
  (let [lista-entrada (->> "./resources/input.lst"
                           (slurp)
                           (st/split-lines))
        tarea-1 (->> lista-entrada
                     (map (fn [elt]
                            (cond (= elt "A X") 4 ; Piedra - Piedra (1 + 3)
                                  (= elt "A Y") 8 ; Piedra - Papel (2 + 6)
                                  (= elt "A Z") 3 ; Piedra - Tijera (3 + 0)
                                  (= elt "B X") 1 ; Papel - Piedra (1 + 0)
                                  (= elt "B Y") 5 ; Papel - Papel (2 + 3)
                                  (= elt "B Z") 9 ; Papel - Tijera (3 + 6)
                                  (= elt "C X") 7 ; Tijera - Piedra (1 + 6)
                                  (= elt "C Y") 2 ; Tijera - Papel (2 + 0)
                                  (= elt "C Z") 6 ; Tijera - Tijera (3 + 3)
                                  )))
                     (reduce + 0))
        tarea-2 (->> lista-entrada
                     (map (fn [elt]
                            (cond (= elt "A X") 3 ; Piedra - Perder (Tijera) (3 + 0)
                                  (= elt "A Y") 4 ; Piedra - Empatar (Piedra) (1 + 3)
                                  (= elt "A Z") 8 ; Piedra - Ganar (Papel) (2 + 6)
                                  (= elt "B X") 1 ; Papel - Perder (Piedra) (1 + 0)
                                  (= elt "B Y") 5 ; Papel - Empatar (Papel) (2 + 3)
                                  (= elt "B Z") 9 ; Papel - Ganar (Tijera) (3 + 6)
                                  (= elt "C X") 2 ; Tijera - Perder (Papel) (2 + 0)
                                  (= elt "C Y") 6 ; Tijera - Empatar (Tijera) (3 + 3)
                                  (= elt "C Z") 7 ; Tijera - Ganar (Piedra) (1 + 6)
                                  )))
                     (reduce + 0))]
    (->> tarea-1 
         (println))
    (->> tarea-2
         (println))))