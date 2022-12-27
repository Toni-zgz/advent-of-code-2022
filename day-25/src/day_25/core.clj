(ns day-25.core
  (:gen-class)
  (:require [clojure.string :as st]))

(defn snafu->decimal [cadena]
  (loop [cad-bucle cadena
         salida 0]
    (if (= cad-bucle "")
      (/ salida 5) ; para compensar el hecho de que el numero mas a la derecha tiene que
      ; multiplicarse por 1 y no por 5.
      (let [elt (str (first cad-bucle))
            numero (cond (= elt "-") -1
                         (= elt "=") -2
                         :else (read-string elt))
            ; iterativamente multiplicamos por 5 en cada posicion
            nueva-salida (* (+ salida numero) 5)
            nueva-cadena (st/join (rest cad-bucle))]
        (recur nueva-cadena nueva-salida)))))

(defn -main []
  (let [lista-entrada (->> "./resources/input.lst"
                           (slurp)
                           (st/split-lines))
        tarea-1 (->> lista-entrada
                     (map #(snafu->decimal %))
                     (reduce + 0))]
    (->> tarea-1
         (println))))
