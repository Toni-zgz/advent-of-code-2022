(ns day-8.core
  (:gen-class)
  (:require [clojure.string :as st]))

;;; Conversion de cadena a vector 2d.
; cadena->vector2d-int :: String -> [[Int]]
(defn cadena->vector2d-int [cadena]
  (->> cadena
       (st/split-lines)
       (map (fn [linea] (st/split linea #"")))
       (map (fn [fila] (->> fila
                            (map #(Integer/parseInt %))
                            (vec))))
       (vec)))

;;; Manipulación de vectores 2d.
; crear-vector2d :: Long -> Long -> [[Boolean]]
(defn crear-vector2d [lin col]
  (let [vector-lin (vec (repeat col false))]
    (vec (repeat lin vector-lin))))

; obtener-vector2d :: [[A]] -> Long -> Long -> A
(defn obtener-vector2d [vect lin col]
   (-> vect
       (get lin)
       (get col)))

; escribir-vector2d :: [[A]] -> Long -> Long -> A -> [[A]]
(defn escribir-vector2d [vect lin col valor]
  (let [linea (get vect lin)
        nueva-linea (assoc linea col valor)]
    (assoc vect lin nueva-linea)))

;;; Predicados utiles 
; lateral? :: Long -> Long -> Long -> Long -> Boolean          
(defn lateral? [lin col alto ancho]
   (cond (= lin 0) true
         (= col 0) true
         (= lin (- alto 1)) true
         (= col (- ancho 1)) true))

;;;;;;; Tarea 1

; visible? :: [[A]] -> Long -> Long -> Long -> Long -> Long -> Long -> Boolean 
(defn visible? [vect lin col dlin dcol alto ancho]
  (if (lateral? lin col alto ancho)
    true
    (let [valor (obtener-vector2d vect lin col)]
      (loop [indice-lin lin
             indice-col col
             result true] 
        (cond (= indice-lin 0) result
              (= indice-lin (- alto 1)) result
              (= indice-col 0) result
              (= indice-col (- ancho 1)) result
              (not result) result
              :else (let [nueva-lin (+ indice-lin dlin)
                          nueva-col (+ indice-col dcol)
                          valor-nuevo (obtener-vector2d vect nueva-lin nueva-col)
                          nuevo-result (< valor-nuevo valor)]
                      (recur nueva-lin nueva-col nuevo-result)))))))

; visible-arriba? :: [[A]] -> Long -> Long -> Long -> Long -> Boolean
(defn visible-arriba? [vect lin col alto ancho]
  (visible? vect lin col -1 0 alto ancho))

; visible-abajo? :: [[A]] -> Long -> Long -> Long -> Long -> Boolean
(defn visible-abajo? [vect lin col alto ancho]
  (visible? vect lin col 1 0 alto ancho))

; visible-derecha? :: [[A]] -> Long -> Long -> Long -> Long -> Boolean
(defn visible-derecha? [vect lin col alto ancho]
  (visible? vect lin col 0 1 alto ancho))

; visible-izquierda? :: [[A]] -> Long -> Long -> Long -> Long -> Boolean
(defn visible-izquierda? [vect lin col alto ancho]
  (visible? vect lin col 0 -1 alto ancho))

; arboles-visibles? :: [[Int]] -> [[Boolean]]
(defn arboles-visibles? [vect]
  (let [alto (count vect)
        ancho (count (first vect))]
    (loop [lin 0
           col 0
           salida (crear-vector2d alto ancho)]
      (if (= lin alto)
        salida
        (let [nueva-lin (if (= col (- ancho 1))
                          (+ lin 1)
                          lin)
              nueva-col (if (= col (- ancho 1))
                          0
                          (+ col 1))
              arbol-visible? (or (visible-arriba? vect lin col alto ancho)
                                 (visible-abajo? vect lin col alto ancho)
                                 (visible-derecha? vect lin col alto ancho)
                                 (visible-izquierda? vect lin col alto ancho))
              nueva-salida (escribir-vector2d salida lin col arbol-visible?)]
          (recur nueva-lin nueva-col nueva-salida))))))

; vector-2d-bool->int :: [[Boolean]] -> [[Int]]
(defn vector2d-bool->int [vect]
  (->> vect
       (map (fn [lin]
              (map (fn [elt] (if elt
                               1
                               0))
                   lin)))))

; contar-arboles-visibles :: [[Int]] -> Int
(defn contar-arboles-visibles [vect]
  (->> vect
       (map (fn [lin]
              (reduce + 0 lin)))
       (reduce + 0)))

;;;;;;; Tarea 2

; numero-arboles :: [[A]] -> Long -> Long -> Long -> Long -> Long -> Long -> Int 
(defn numero-arboles [vect lin col dlin dcol alto ancho]
  (if (lateral? lin col alto ancho)
    0
    (let [valor (obtener-vector2d vect lin col)]
      (loop [indice-lin lin
             indice-col col
             result 0
             bloqueado false]
        (cond (= indice-lin 0) result
              (= indice-lin (- alto 1)) result
              (= indice-col 0) result
              (= indice-col (- ancho 1)) result
              (= bloqueado true) result
              :else (let [nueva-lin (+ indice-lin dlin)
                          nueva-col (+ indice-col dcol)
                          valor-nuevo (obtener-vector2d vect nueva-lin nueva-col)
                          nuevo-result (+ result 1)
                          nuevo-bloqueado (if (< valor-nuevo valor)
                                            false
                                            true)]
                      (recur nueva-lin nueva-col nuevo-result nuevo-bloqueado)))))))

; arboles-arriba :: [[A]] -> Long -> Long -> Long -> Long -> Int
(defn arboles-arriba [vect lin col alto ancho]
  (numero-arboles vect lin col -1 0 alto ancho))

; arboles-abajo :: [[A]] -> Long -> Long -> Long -> Long -> Int
(defn arboles-abajo [vect lin col alto ancho]
  (numero-arboles vect lin col 1 0 alto ancho))

; arboles-derecha :: [[A]] -> Long -> Long -> Long -> Long -> Int
(defn arboles-derecha [vect lin col alto ancho]
  (numero-arboles vect lin col 0 1 alto ancho))

; arboles-izquierda :: [[A]] -> Long -> Long -> Long -> Long -> Int
(defn arboles-izquierda [vect lin col alto ancho]
  (numero-arboles vect lin col 0 -1 alto ancho))

; puntuaciones-escenicas :: [[Int]] -> [[Int]]
(defn puntuaciones-escenicas [vect]
  (let [alto (count vect)
        ancho (count (first vect))]
    (loop [lin 0
           col 0
           salida (crear-vector2d alto ancho)]
      (if (= lin alto)
        salida
        (let [nueva-lin (if (= col (- ancho 1))
                          (+ lin 1)
                          lin)
              nueva-col (if (= col (- ancho 1))
                          0
                          (+ col 1))
              puntuacion (* (arboles-arriba vect lin col alto ancho)
                            (arboles-abajo vect lin col alto ancho)
                            (arboles-derecha vect lin col alto ancho)
                            (arboles-izquierda vect lin col alto ancho))
              nueva-salida (escribir-vector2d salida lin col puntuacion)]
          (recur nueva-lin nueva-col nueva-salida))))))

; puntuacion-maxima :: [[Int]] -> Int
(defn puntuacion-maxima [vect]
  (->> vect
       (map (fn [linea] (reduce max 0 linea)))
       (reduce max 0)))

;;;;; Funcion principal
(defn -main []
  (let [lista-entrada (->> "./resources/input.lst"
                           (slurp)
                           (cadena->vector2d-int))
        tarea-1 (->> lista-entrada
                     (arboles-visibles?)
                     (vector2d-bool->int)
                     (contar-arboles-visibles))
        tarea-2 (->> lista-entrada
                     (puntuaciones-escenicas)
                     (puntuacion-maxima))]
    (->> tarea-1
         (println))
    (->> tarea-2
         (println))))
