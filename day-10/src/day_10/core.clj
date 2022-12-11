(ns day-10.core
  (:gen-class)
  (:require [clojure.string :as st]))

; noop :: ([Int], Int) -> ([Int], Int)
(defn noop [estado-entrada]
  (let [estados (first estado-entrada)
        ultimo-valor (second estado-entrada)
        nuevos-estados (conj estados ultimo-valor)]
    (list nuevos-estados ultimo-valor)))

; addx :: ([Int], Int) -> String -> ([Int], Int)
(defn addx [estado-entrada argumento]
  (let [numero (->> argumento
                       (Integer/parseInt)) 
        ultimo-valor (second estado-entrada)
        nuevo-valor (+ ultimo-valor numero)
        estado-ultimo (first estado-entrada)
        nuevos-estados (-> estado-ultimo
                           (conj ultimo-valor)
                           (conj ultimo-valor))]
    (list nuevos-estados nuevo-valor)))

; procesar :: (String) -> [Int]
(defn procesar [codigo]
  (loop [codigo-bucle codigo
         estado (list [1] 1)]
    (if (= codigo-bucle '())
      (first estado)
      (let [nuevo-codigo-bucle (rest codigo-bucle)
            instruccion (first codigo-bucle)
            nuevo-estado (cond (st/starts-with? instruccion "noop") (noop estado)
                               (st/starts-with? instruccion "addx") (->>
                                                                     (-> instruccion
                                                                         (st/split #" ")
                                                                         (second))
                                                                     (addx estado))
                               :else estado)]
        (recur nuevo-codigo-bucle nuevo-estado)))))

; fuerza-senal :: [Int] -> Int
(defn fuerza-senal [estado]
  (let [estado-20 (get estado 20)
        estado-60 (get estado 60)
        estado-100 (get estado 100)
        estado-140 (get estado 140)
        estado-180 (get estado 180)
        estado-220 (get estado 220)]
    (+ (* estado-20 20) 
       (* estado-60 60) 
       (* estado-100 100) 
       (* estado-140 140) 
       (* estado-180 180) 
       (* estado-220 220))))

; dibujar-pixels :: [Int] -> [[Int]]
(defn dibujar-pixels [estado]
  (let [numero-total-pixels 240
        pixels-por-linea 40
        numero-lineas 6
        linea (vec (repeat pixels-por-linea "."))
        pantalla (vec (repeat numero-lineas linea))]
   (loop [indice 0 
          pantalla-bucle pantalla]
     (if (= indice numero-total-pixels)
       pantalla-bucle
      (let [numero-linea (int (/ indice pixels-por-linea))
            numero-pixel (mod indice pixels-por-linea)
            valor-x (get estado (+ indice 1))
            posicion-central valor-x
            posicion-izquierda (- posicion-central 1)
            posicion-derecha (+ posicion-central 1)
            linea-actual (get pantalla-bucle numero-linea)
            nueva-linea (if (or (= numero-pixel posicion-central)
                                (= numero-pixel posicion-derecha)
                                (= numero-pixel posicion-izquierda))
                          (-> linea-actual
                              (assoc numero-pixel "#"))
                          linea-actual)
            nueva-pantalla-bucle (assoc pantalla-bucle numero-linea nueva-linea)]
        (recur (+ indice 1) nueva-pantalla-bucle))))))

(defn -main []
  (let [lista-entrada (->> "./resources/input.lst"
                           (slurp)
                           (st/split-lines)
                           (map st/trim))
        tarea-1 (->> lista-entrada
                     (procesar)
                     (fuerza-senal))
        tarea-2 (->> lista-entrada
                     (procesar)
                     (dibujar-pixels))]
    (->> tarea-1
         (println))
    (->> tarea-2
         (map st/join)
         (run! prn))))
