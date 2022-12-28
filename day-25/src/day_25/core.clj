(ns day-25.core
  (:gen-class)
  (:require [clojure.string :as st]))

; snafu->decimal :: String -> Int
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

; decimal->snafu :: Int -> String
(defn decimal->snafu [numero]
  (loop [numero-bucle numero
         acarreo 0
         lista-salida '()]
    (cond (and (= numero-bucle 0)
               (= acarreo 0)) (st/join lista-salida)
          (and (= numero-bucle 0)
               (= acarreo 1)) (st/join (conj lista-salida "1"))
          :else (let [nuevo-numero (quot numero-bucle 5)
                      elt (+ (rem numero-bucle 5) acarreo)
                      nuevo-acarreo (if (> elt 2)
                                      1
                                      0)
                      nueva-cad (cond (= elt 0) "0"
                                      (= elt 1) "1"
                                      (= elt 2) "2"
                                      (= elt 3) "="
                                      (= elt 4) "-"
                                      (= elt 5) "0")
                      nueva-salida (conj lista-salida nueva-cad)]
                  (recur nuevo-numero nuevo-acarreo nueva-salida)))))

(defn -main []
  (let [lista-entrada (->> "./resources/input.lst"
                           (slurp)
                           (st/split-lines))
        tarea-1 (->> lista-entrada
                     (map #(snafu->decimal %))
                     (reduce + 0)
                     (decimal->snafu))]
    (->> tarea-1
         (println))))
