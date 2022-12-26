(ns day-24.core
  (:gen-class)
  (:require [clojure.string :as st]))

; crear-array2D :: Int -> Int -> A -> [[A]]
(defn crear-array2D [nlin ncol elt]
  (->> elt
       (repeat ncol)
       (repeat nlin)))

; escribir-array2D :: [[A]] -> Int -> Int -> A -> [[A]]
(defn escribir-array2D [ary linea col valor]
  (let [fila (get ary linea)
        nueva-fila (assoc fila col valor)]
    (assoc ary linea nueva-fila)))

; leer-array2D :: [[A]] -> Int -> Int -> A
(defn leer-array2D [ary linea col]
  (-> ary
      (get linea)
      (get col)))

(defn -main []
  (let [lista-datos (->> "./resources/input.lst"
                         (slurp)
                         (st/split-lines)
                         (map #(st/split % #""))
                         (vec))
        tarea-1 (->> lista-datos)
        ;tarea-2 (->> lista-datos)
        ]
    (->> tarea-1
         (println))
    ;(->> tarea-2
    ;     (println))
    ))
