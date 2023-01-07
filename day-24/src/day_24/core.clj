(ns day-24.core
  (:gen-class)
  (:require [clojure.string :as st])
  (:require [bogus.core :as bg]))

; crear-array2D :: Int -> Int -> A -> [[A]]
(defn crear-array2D [nlin ncol elt]
  (->> elt
       (repeat ncol)
       (vec)
       (repeat nlin)
       (vec)))

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

; actualizar-objetos :: (Diccionario) -> Int -> Int -> (Diccionario)
(defn actualizar-objetos [lista ancho alto]
  (->> lista 
       (map (fn [elt]
              (let [direccion (get elt :dir)
                    posicion (get elt :pos)
                    x (first posicion)
                    y (second posicion)
                    dx (first direccion)
                    dy (second direccion)
                    nuevo-x (cond (and (= x 1)
                                       (= dx -1)) (- ancho 1)
                                  (and (= x (- ancho 1))
                                       (= dx 1)) 1
                                  :else (+ x dx))
                    nuevo-y (cond (and (= y 1)
                                       (= dy -1)) (- alto 1)
                                  (and (= y (- alto 1))
                                       (= dy 1)) 1
                                  :else (+ y dy))
                    nueva-posicion (list nuevo-x nuevo-y)]
                {:pos nueva-posicion, :dir direccion})))))

; imprimir-tablero :: Int -> Int -> (Diccionario) -> [[A]]
(defn imprimir-tablero [nfil ncol objetos]
  (let [tablero (crear-array2D nfil ncol ".")]
    (loop [lista-objetos objetos
           tablero-bucle tablero]
      (if (= lista-objetos '())
        tablero-bucle
        (let [nueva-lista-objetos (rest lista-objetos)
              elt (first lista-objetos)
              elt-pos (get elt :pos)
              elt-lin (first elt-pos)
              elt-col (second elt-pos)
              elt-dir (get elt :dir)
              elt-dlin (first elt-dir)
              elt-dcol (second elt-dir)
              elt-val (cond (= elt-dlin 1) "v"
                            (= elt-dlin -1) "^"
                            (= elt-dcol 1) ">"
                            (= elt-dcol -1) "<"
                            :else "#")
              nuevo-tablero (escribir-array2D tablero-bucle elt-lin elt-col elt-val)]
          (recur nueva-lista-objetos nuevo-tablero))))))

; obtener-posiciones-validas :: Int -> Int -> [[A]] -> [(Int Int)]
(defn obtener-posiciones-validas [lin col tablero]
  (let [posicion-valida (fn [lin col]
                          (if (= (leer-array2D tablero lin col) ".")
                            (list lin col)
                            nil))
        actual (posicion-valida lin col)
        arriba (posicion-valida (- lin 1) col)
        abajo (posicion-valida (+ lin 1) col)
        derecha (posicion-valida lin (+ col 1))
        izquierda (posicion-valida lin (- col 1))
        posiciones (list actual arriba abajo derecha izquierda)]
    (->> posiciones
         (filter (fn [elt] (not (nil? elt))))
         (vec))))

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
