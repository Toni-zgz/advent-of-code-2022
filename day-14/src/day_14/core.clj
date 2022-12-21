(ns day-14.core
  (:gen-class)
  (:require [clojure.string :as st])
  (:require [clojure.zip :as zip]))

; crear-array2d :: Int -> Int -> [[A]]
(defn crear-array2d [nfil ncol]
  (let [fila (-> nfil
                 (repeat ".")
                 (vec))
        ary2d (-> ncol
                  (repeat fila)
                  (vec))]
    ary2d))

; leer-array2d :: [[A]] -> Int -> Int -> A
(defn leer-array2d [ary nfil ncol]
  (-> ary
      (get nfil)
      (get ncol)))

; escribir-array2d :: [[A]] -> Int -> Int -> A -> [[A]]
(defn escribir-array2d [ary nfil ncol valor]
  (let [fila (get ary nfil)
        nueva-fila (assoc fila ncol valor)]
    (assoc ary nfil nueva-fila)))

; cad->ary :: String -> [(Int Int)]
(defn cad->ary [cadena]
  (->> (st/split cadena #"->")
       (map (fn [elt] (st/split elt #",")))
       (map (fn [elt1] (map (fn [elt2] (read-string elt2)) elt1)))
       (vec)))

; obtener-datos :: [[(Int Int)]] -> (Int -> Int -> Int) -> (Seq -> Int)
(defn obtener-datos [vect func pos valor]
  (->> vect
       (map (fn [elt] (->> elt
                           (reduce (fn [acc elt]
                                     (func (pos elt) acc)) valor))))
       (reduce func valor)))

; obtener-maximo-x :: [(Int Int)] -> Int
(defn obtener-maximo-x [vect]
  (obtener-datos vect max first 0))

; obtener-maximo-y :: [(Int Int)] -> Int
(defn obtener-maximo-y [vect]
  (obtener-datos vect max second 0))

; obtener-maximo-x :: [(Int Int)] -> Int
(defn obtener-minimo-x [vect]
  (obtener-datos vect min first 1000000))

; obtener-maximo-x :: [(Int Int)] -> Int
(defn obtener-minimo-y [vect]
  (obtener-datos vect min second 1000000))

; linea-vertical :: Int -> Int -> Int -> [(Int Int)]
(defn linea-vertical [xmin ymin ymax]
  (let [ys (range ymin (+ ymax 1))
        longitud-ys (count ys)
        xs (repeat longitud-ys xmin)]
    (-> (map #(list %1 %2) xs ys))))

; linea-horizontal :: Int -> Int -> Int -> [(Int Int)]
(defn linea-horizontal [xmin xmax ymin]
  (let [xs (range xmin (+ xmax 1))
        longitud-xs (count xs)
        ys (repeat longitud-xs ymin)]
    (-> (map #(list %1 %2) xs ys))))

; obtener-puntos :: [(Int Int)] -> [(Int Int)]
; esta funcion convierte un vector de vertices en un vector 
; de puntos para poder imprimir
(defn obtener-puntos [ary-entrada]
  (let [longitud (count ary-entrada)]
    (loop [indice 0
           salida '()]
      (if (= indice (- longitud 1))
        (-> salida
            (distinct))
        (let [coord1 (get ary-entrada indice)
              coord2 (get ary-entrada (+ indice 1))
              x1 (first coord1)
              y1 (second coord1)
              x2 (first coord2)
              y2 (second coord2)
              xmin (min x1 x2)
              ymin (min y1 y2)
              xmax (max x1 x2)
              ymax (max y1 y2)
              dx (- xmax xmin)
              dy (- ymax ymin)
              nuevo-indice (+ indice 1)
              puntos (cond (= dx 0) (linea-vertical xmin ymin ymax)
                           (= dy 0) (linea-horizontal xmin xmax ymin))
              nueva-salida (concat salida puntos)]
          (recur nuevo-indice nueva-salida))))))

(defn -main []
  (let [lista-datos (-> "./resources/input.lst"
                        (slurp)
                        (st/split-lines))
        tarea-1 (->> lista-datos)
        tarea-2 (->> lista-datos)]
    (-> tarea-1
        (println))
    (-> tarea-2
        (println))))
