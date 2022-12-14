(ns day-13.core
  (:gen-class)
  (:require [clojure.string :as st]))

; cargar-datos :: String -> ([A] [A])
(defn cargar-datos [cad]
  (let [vect (->> cad
                  (st/split-lines))] 
    (loop [ary-i []
           ary-d []
           indice 0]
      (if (> indice (count vect))
        (list ary-i ary-d)
        (let [indice-i indice
              indice-d (+ indice 1)
              elemento-i (-> vect
                             (get indice-i)
                             (read-string))
              elemento-d (-> vect 
                             (get indice-d)
                             (read-string))
              nuevo-ary-i (conj ary-i elemento-i)
              nuevo-ary-d (conj ary-d elemento-d) 
              nuevo-indice (+ indice 3)] 
          (recur nuevo-ary-i nuevo-ary-d nuevo-indice))))))

; orden-correcto? :: [A] -> [A] -> Boolean
(defn orden-correcto? [izq der]
  (let [elt-i (first izq)
        elt-d (first der)]
    (cond (nil? elt-i) true
          (nil? elt-d) false
          (= elt-i []) true
          (= elt-d []) false
          (and (vector? elt-i)
               (vector? elt-d)) (orden-correcto? elt-i elt-d)
          (vector? elt-i) (orden-correcto? elt-i (vector elt-d))
          (vector? elt-d) (orden-correcto? (vector elt-i) elt-d)
          :else (cond (< elt-i elt-d) true
                      (> elt-i elt-d) false
                      :else (recur (rest izq) (rest der))))))

; procesar-lista :: ([A] [A]) -> [Boolean]
(defn procesar-lista [lista]
  (let [vector-i (first lista)
        vector-d (second lista)
        num-elems (count vector-i)]
    (loop [indice 0
           salida []]
      (if (= indice num-elems)
        salida
        (let [elemento-i (get vector-i indice)
              elemento-d (get vector-d indice)
              correcto? (orden-correcto? elemento-i elemento-d)
              nueva-salida (conj salida correcto?)
              nuevo-indice (+ indice 1)]
          (recur nuevo-indice nueva-salida))))))

; obtener-indices :: [Bool] -> [Int]
(defn obtener-indices [vect]
  (loop [lista-salida []
         indice 1]
    (if (= indice (count vect))
      lista-salida
      (let [elemento (get vect (- indice 1))
            nueva-salida (if elemento
                           (conj lista-salida indice)
                           lista-salida)
            nuevo-indice (+ indice 1)]
        (recur nueva-salida nuevo-indice)))))

(defn -main []
  (let [lista-entrada (->> "resources/input.lst"
                           (slurp))
        tarea-1 (->> lista-entrada
                     (cargar-datos)
                     (procesar-lista)
                     (obtener-indices)
                     (reduce + 0))
        tarea-2 (->> lista-entrada
                     (cargar-datos))]
    (->> tarea-1
         (println))
    ;(->> tarea-2
    ;     (println))
    ))
