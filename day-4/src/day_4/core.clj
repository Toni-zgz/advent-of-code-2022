(ns day-4.core
  (:gen-class)
  (:require [clojure.string :as st]))

; cadena->diccionario :: String -> Dictionary
(defn cadena->diccionario [cadena]
  (let [elfos-lista (st/split cadena #",")
        elfo1-cadena (first elfos-lista)
        elfo2-cadena (second elfos-lista)
        elfo1-lista (st/split elfo1-cadena #"-")
        elfo2-lista (st/split elfo2-cadena #"-")
        elfo1-min (Integer/parseInt (first elfo1-lista))
        elfo1-max (Integer/parseInt(second elfo1-lista))
        elfo2-min (Integer/parseInt(first elfo2-lista))
        elfo2-max (Integer/parseInt(second elfo2-lista))]
    {:elfo1-min elfo1-min,
     :elfo1-max elfo1-max,
     :elfo2-min elfo2-min,
     :elfo2-max elfo2-max}))

; esta-dentro-de :: Dictionary -> Boolean
(defn esta-dentro-de [diccionario]
  (let [elfo1-min (diccionario :elfo1-min)
        elfo1-max (diccionario :elfo1-max)
        elfo2-min (diccionario :elfo2-min)
        elfo2-max (diccionario :elfo2-max)]
    (cond (and (<= elfo1-min elfo2-min)
               (>= elfo1-max elfo2-max)) true
          (and (<= elfo2-min elfo1-min)
               (>= elfo2-max elfo1-max)) true
          :else false)))

; se-superponen :: Dictionary -> Boolean 
(defn se-superponen [diccionario]
  (let [elfo1-min (diccionario :elfo1-min)
        elfo1-max (diccionario :elfo1-max)
        elfo2-min (diccionario :elfo2-min)
        elfo2-max (diccionario :elfo2-max)]
    (cond (esta-dentro-de diccionario) true
          (and (<= elfo1-min elfo2-max)
               (>= elfo1-max elfo2-max)) true
          (and (<= elfo2-min elfo1-max)
               (>= elfo2-max elfo1-max)) true
          :else false)))

; main :: () -> ()
(defn -main []
  (let [lista-entrada (->> "./resources/input.lst"
                           (slurp)
                           (st/split-lines))
        tarea-1 (->> lista-entrada
                     (map cadena->diccionario)
                     (filter esta-dentro-de)
                     (count))
        tarea-2 (->> lista-entrada
                     (map cadena->diccionario)
                     (filter se-superponen)
                     (count))]
    (->> tarea-1
         (println))
    (->> tarea-2
         (println))))