(ns day-3.core
  (:gen-class)
  (:require [clojure.string :as st]))

; convertir-a-enteros :: String -> (Long)
(defn convertir-a-enteros [cadena]
  (map int cadena))

; dividir-cadenas-en-2 :: (Long) -> (Seq(Long), Seq(Long))
(defn dividir-cadenas [lista]
  (let [num-elems (count lista)
        mitad (/ num-elems 2)
        seq-1 (take mitad lista)
        seq-2 (drop mitad lista)]
    (list seq-1 seq-2)))

; obtener-duplicados-tarea-1 :: (Seq(Long), Seq(Long)) -> (Long)
(defn obtener-duplicados-tarea-1 [tupla]
  (let [seq1-ord (sort (first tupla))
        seq2-ord (sort (second tupla))]
    (loop [seq1 seq1-ord
           seq2 seq2-ord
           salida '()]
      (let [elt1 (first seq1)
            elt2 (first seq2)]
        (cond (empty? seq1) (distinct salida)
              (empty? seq2) (distinct salida)
              (< elt1 elt2) (recur (rest seq1) seq2 salida)
              (> elt1 elt2) (recur seq1 (rest seq2) salida)
              (= elt1 elt2) (recur (rest seq1) (rest seq2) (conj salida elt1)))))))

; obtener-duplicados-tarea-2 :: ((Long), (Long), (Long)) -> (Long)
(defn obtener-duplicados-tarea-2 [tupla]
  (let [list1-ord (sort (nth tupla 0))
        list2-ord (sort (nth tupla 1))
        list3-ord (sort (nth tupla 2))]
    (loop [lista-1 list1-ord
           lista-2 list2-ord
           lista-3 list3-ord
           salida '()]
      (let [elt1 (first lista-1)
            elt2 (first lista-2)
            elt3 (first lista-3)]
        (cond (empty? lista-1) (distinct salida)
              (empty? lista-2) (distinct salida)
              (empty? lista-3) (distinct salida)
              (= elt1 elt2 elt3) (recur (rest lista-1) (rest lista-2) (rest lista-3) (conj salida elt1))
              (= (min elt1 elt2 elt3) elt1) (recur (rest lista-1) lista-2 lista-3 salida)
              (= (min elt1 elt2 elt3) elt2) (recur lista-1 (rest lista-2) lista-3 salida)
              (= (min elt1 elt2 elt3) elt3) (recur lista-1 lista-2 (rest lista-3) salida))))))

; obtener-prioridades :: Long -> Long
(defn obtener-prioridades [numero]
  (cond (>= numero 97) (+ (- numero 97) 1)
        :else (+ (- numero 65) 27)))

; agrupar-3-cadenas :: ((Long)) -> (((Long), (Long), (Long)))
(defn agrupar-3-cadenas [lista-de-listas]
  (loop [entrada lista-de-listas
         salida '()]
    (if (= entrada '())
      salida
      (let [primera (nth entrada 0)
            segunda (nth entrada 1)
            tercera (nth entrada 2)
            nueva-lista  (list primera segunda tercera)
            nueva-salida  (cons nueva-lista salida)
            nueva-entrada (->> entrada
                           (rest)
                           (rest)
                           (rest))]
        (recur nueva-entrada nueva-salida)))))

(defn -main []
  (let [lista-entrada (->> "./resources/input.lst"
                           (slurp)
                           (st/split-lines))
        tarea-1 (->> lista-entrada
                     (map convertir-a-enteros)
                     (map dividir-cadenas)
                     (map obtener-duplicados-tarea-1)
                     (flatten)
                     (map obtener-prioridades)
                     (reduce + 0))
         tarea-2 (->> lista-entrada
                      (map convertir-a-enteros)
                      (agrupar-3-cadenas)
                      (map obtener-duplicados-tarea-2)
                      (flatten)
                      (map obtener-prioridades)
                      (reduce + 0))]
    (->> tarea-1
         (println))
    (->> tarea-2
         (println))))