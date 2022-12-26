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

; obtener-objetos :: [[A]] -> (Diccionario)
(defn obtener-objetos [tablero]
  (let [nlin (count tablero)
        ncol (count (first tablero))]
    (loop [indice-lin 0
           indice-col 0
           lista-salida '()]
      (if (= indice-lin nlin)
       (reverse lista-salida)
        (let [nueva-linea (if (= indice-col ncol)
                            (+ indice-lin 1)
                            indice-lin)
              nueva-col (if (= indice-col ncol)
                          0
                          (+ indice-col 1))
              elemento (leer-array2D tablero indice-lin indice-col)
              posicion-objeto (list indice-lin indice-col)
              direccion-objeto (cond (= elemento "^") (list -1 0)
                                     (= elemento "v") (list 1 0)
                                     (= elemento "<") (list 0 -1)
                                     (= elemento ">") (list 0 1)
                                     :else (list 0 0))
              objeto {:pos posicion-objeto, :dir direccion-objeto}
              nueva-salida (if (or (= elemento "^")
                                   (= elemento "v")
                                   (= elemento "<")
                                   (= elemento ">")
                                   (= elemento "#")) 
                             (conj lista-salida objeto)
                             lista-salida)]
          (recur nueva-linea nueva-col nueva-salida))))))

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
