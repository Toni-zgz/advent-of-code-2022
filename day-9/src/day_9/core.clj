(ns day-9.core
  (:gen-class)
  (:require [clojure.string :as st]))

; convert-data :: String -> [String]
(defn convert-data [cadena]
  (let [lista (st/split cadena #" ")
        multiplicador (Integer/parseInt (second lista))
        valor (first lista)]
    (repeat multiplicador valor)))

; adyacente? :: Long -> Long -> Long -> Long -> Bool
(defn adyacente? [lin1 col1 lin2 col2]
  (let [dlin (abs (- lin1 lin2))
        dcol (abs (- col1 col2))]
    (and (< dlin 2)
         (< dcol 2))))

; mover-cola :: Long -> Long -> Long -> Long -> (Long, Long)
(defn mover-cola [lin-cabeza col-cabeza lin-cola col-cola]
  (let [dlin (abs (- lin-cabeza lin-cola))
        dcol (abs (- col-cabeza col-cola))
        direcc-lin (if (= dlin 0)
                     0
                     (/ (- lin-cabeza lin-cola) dlin)) 
        direcc-col (if (= dcol 0)
                     0
                     (/ (- col-cabeza col-cola) dcol))  
        lin-salida (+ lin-cola direcc-lin)
        col-salida (+ col-cola direcc-col)]
    (list lin-salida col-salida)))

; calcular-nudos :: [(Long, Long)] -> [(Long, Long)]
(defn calcular-nudos [pos-nudos]
  (loop [indice 1
         nudos-bucle pos-nudos]
    (if (= indice (count pos-nudos))
      nudos-bucle
      (let [indice-anterior (- indice 1)
            pos-anterior (get nudos-bucle indice-anterior)
            lin-anterior (first pos-anterior)
            col-anterior (second pos-anterior)
            pos-nudo (get nudos-bucle indice)
            lin-nudo (first pos-nudo)
            col-nudo (second pos-nudo)
            nuevo-nudo (if (adyacente? lin-anterior col-anterior lin-nudo col-nudo)
                         (list lin-nudo col-nudo)
                         (mover-cola lin-anterior col-anterior lin-nudo col-nudo))
            nuevos-nudos (assoc nudos-bucle indice nuevo-nudo)
            nuevo-indice (+ indice 1)]
        (recur nuevo-indice nuevos-nudos)))))

; procesar-datos :: [String] -> [Int]
(defn procesar-datos [num-nudos lista]
  (loop [posiciones-nudos (vec (repeat num-nudos '(0 0)))
         lista-visitados '()
         lista-ordenes lista]
    (if (= lista-ordenes '())
      lista-visitados
      (let [orden (first lista-ordenes)
            despl-linea (cond (= orden "U") 1
                              (= orden "D") -1
                              :else 0)
            despl-columna (cond (= orden "R") 1
                                (= orden "L") -1
                                :else 0)
            posicion-cabeza (get posiciones-nudos 0)
            lin-cabeza (first posicion-cabeza)
            col-cabeza (second posicion-cabeza)
            nueva-lin-cabeza (+ lin-cabeza despl-linea)
            nueva-col-cabeza (+ col-cabeza despl-columna)
            nueva-posicion-cabeza (list nueva-lin-cabeza nueva-col-cabeza) 
            nueva-posicion-nudos (-> posiciones-nudos
                                     (assoc 0 nueva-posicion-cabeza)
                                     (calcular-nudos))
            nueva-posicion-cola (get nueva-posicion-nudos (- num-nudos 1))
            pos-visitada? (->
                           (->> lista-visitados
                                (filter #(= nueva-posicion-cola %))
                                (count))
                           (> 0))
            nuevos-visitados (if pos-visitada?
                               lista-visitados
                               (conj lista-visitados nueva-posicion-cola))
            nuevas-ordenes (rest lista-ordenes)]
        (recur nueva-posicion-nudos nuevos-visitados nuevas-ordenes)))))

(defn -main []
  (let [lista-entrada (->> "./resources/input.lst"
                           (slurp)
                           (st/split-lines)
                           (map convert-data)
                           (flatten))
        tarea-1 (->> lista-entrada
                     (procesar-datos 2)
                     (count))
        tarea-2 (->> lista-entrada
                     (procesar-datos 10)
                     (count))]
    (->> tarea-1
         (println))
    (->> tarea-2
         (println))))
