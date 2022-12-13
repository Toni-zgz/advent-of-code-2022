(ns day-12.core
  (:gen-class)
  (:require [clojure.string :as st]))

; crear-array-2d :: Int -> Int -> A -> [[A]]
(defn crear-array-2d [nlin ncol elt]
  (->> elt
       (repeat ncol)
       (vec)
       (repeat nlin)
       (vec)))

; leer-array-2d :: [[A]] -> Int -> Int -> A
(defn leer-array-2d [ary lin col]
  (-> ary
      (get lin)
      (get col)))

; escribir-array-2d :: [[A]] -> Int -> Int -> A -> [[A]]
(defn escribir-array-2d [ary lin col valor]
  (let [linea (get ary lin)
        nueva-linea (assoc linea col valor)]
    (assoc ary lin nueva-linea)))

; obtener-posiciones-candidatas [[A]] -> Int -> Int -> ((Int, Int))
(defn obtener-posiciones-candidatas [ary linea columna]
  (let [ultima-linea (-> ary
                         (count)
                         (- 1))
        ultima-columna (-> ary
                           (first)
                           (count)
                           (- 1))
        arriba (if (= linea 0)
                 '()
                 (list (- linea 1) columna))
        abajo (if (= linea ultima-linea)
                '()
                (list (+ linea 1) columna))
        derecha (if (= columna ultima-columna)
                  '()
                  (list linea (+ columna 1)))
        izquierda (if (= columna 0)
                    '()
                    (list linea (- columna 1)))]
    (list arriba abajo derecha izquierda)))

; obtener-posiciones-prometedoras :: [[A]] -> [[A]] -> Int -> Int -> ((Int, Int)) -> ((Int, Int))
(defn obtener-posiciones-prometedoras [ary-alturas ary-visitados linea columna coords]
  (let [valor (leer-array-2d ary-alturas linea columna) 
        valor+2 (-> valor
                    (+ 2))]
    (->> coords
       ; quitamos las coordenadas vacias '()
         (filter (fn [elt] (->> elt
                                (= '())
                                (not))))
       ; quitamos las coordenadas ya visitadas
         (filter (fn [elt] (let [linea (first elt)
                                 columna (second elt)]
                             (-> (leer-array-2d ary-visitados linea columna)
                                 (= ".")))))
       ; por ultimo, quitamos las coordenadas cuyo valor dista de la actual 2 letras
         (filter (fn [elt] (let [linea-elt (first elt)
                                 columna-elt (second elt)
                                 valor-elt (leer-array-2d ary-alturas linea-elt columna-elt)]
                             (< valor-elt valor+2)))))))

;
(defn procesar [])

(defn -main []
  (let [vector-entrada (->> "./resources/input.lst"
                           (slurp)
                           (st/split-lines)
                           (map (fn [elt] (->>
                                           (-> elt
                                               (st/split #""))
                                           (map (fn [elt]
                                                  (-> elt
                                                      (.charAt 0)
                                                      (int)
                                                      (- 97))))
                                           (vec))))
                           (vec))
        posicion-inicial '(0 0) 
        tarea-1 (->> vector-entrada)
        tarea-2 (->> vector-entrada)]
    (->> tarea-1
         (println))
    (->> tarea-2
         (println))))