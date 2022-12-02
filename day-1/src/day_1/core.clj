(ns day-1.core
  (:gen-class)
  (:require [clojure.string :as st]))

(defn tarea []
   (let [datos-entrada (->> "./resources/input.lst"
                            (slurp)
                            (st/split-lines))]
     (loop [lista-entrada datos-entrada
            lista-intermedia '()
            lista-salida '()]
       (cond (= lista-entrada '()) (->> lista-intermedia
                                        (reduce + 0)
                                        (conj lista-salida))
             (= (first lista-entrada) "") (recur (rest lista-entrada) '() (conj lista-salida (reduce + 0 lista-intermedia)))
             :else (recur (rest lista-entrada)
                          (->> (first lista-entrada)
                               (Integer/parseInt)
                               (conj lista-intermedia))
                          lista-salida)))))

(defn -main []
 (let [salida-tarea (tarea)
       tarea-1 (->> salida-tarea
                  (reduce max 0))
       tarea-2 (->> salida-tarea
                    (sort)
                    (reverse)
                    (take 3)
                    (reduce + 0))]
   (println tarea-1)
   (println tarea-2)))
