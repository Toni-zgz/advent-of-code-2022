(ns day-5.core
  (:gen-class)
   (:require [clojure.string :as st]))

; cad->dict :: String -> Dictionary
(defn cad->dict [cadena]
  (let [ary-cad (st/split cadena #" ")
        cantidad (Integer/parseInt (get ary-cad 1))
        origen (Integer/parseInt (get ary-cad 3))
        destino (Integer/parseInt (get ary-cad 5))]
    {:cantidad cantidad :origen origen :destino destino}))

; lista->cad :: ((Char)) -> String
(defn lista->cad [lista]
  (loop [entrada lista
         salida []]
    (if (= entrada '())
      (st/join salida)
      (let [lista-primera (first entrada)
            elemento (first lista-primera)
            elemento-final (if (nil? elemento)
                             " "
                             elemento)
            nueva-entrada (rest entrada)
            nueva-salida (conj salida elemento-final)]
        (recur nueva-entrada nueva-salida)))))

; procesar-orden :: ((Char)) -> Dictionary -> ((Char))
(defn procesar-orden [estado orden]
  (let [origen (- (orden :origen) 1) ; para pasarlo de base 1 a base 0 hay que restar 1 al indice.
        destino (- (orden :destino) 1)] ; para pasarlo de base 1 a base 0 hay que restar 1 al indice.
    (loop [ary-estado (into [] estado)
           cantidad (orden :cantidad)]
      (if (= cantidad 0)
        (reverse (into '() ary-estado)) ; el paso de array a lista lo hace invirtiendo el array, por lo que hay que volver a invertirlo para ponerlo bien.
        (let [nueva-cantidad (- cantidad 1)
              loc-origen (get ary-estado origen)
              loc-destino (get ary-estado destino)
              nuevo-origen (pop loc-origen)
              nuevo-destino (->> (peek loc-origen)
                                 (conj loc-destino))
              nuevo-estado (-> ary-estado
                               (assoc origen nuevo-origen)
                               (assoc destino nuevo-destino))]
          (recur nuevo-estado nueva-cantidad))))))

; procesar-orden-multiple :: ((Char)) -> Dictionary -> ((Char))
(defn procesar-orden-multiple [estado orden]
  (let [origen (- (orden :origen) 1) ; para pasarlo de base 1 a base 0 hay que restar 1 al indice.
        destino (- (orden :destino) 1) ; para pasarlo de base 1 a base 0 hay que restar 1 al indice.
        cantidad (orden :cantidad)
        ary-estado (into [] estado)
        loc-origen (get ary-estado origen)
        loc-destino (get ary-estado destino)
        temp (loop [cant-bucle cantidad
                    temp-bucle '()
                    origen-bucle loc-origen]
               (if (= cant-bucle 0)
                 temp-bucle
                 (let [nuevo-temp-bucle (->> (peek origen-bucle)
                                             (conj temp-bucle))
                       nueva-cant-bucle (- cant-bucle 1)
                       nuevo-origen-bucle (rest origen-bucle)]
                   (recur nueva-cant-bucle nuevo-temp-bucle nuevo-origen-bucle))))
        nuevo-destino (loop [cant-bucle cantidad
                             dest-bucle loc-destino
                             temp-bucle temp]
                        (if (= cant-bucle 0)
                          dest-bucle
                          (let [nuevo-dest-bucle (->> (peek temp-bucle)
                                                      (conj dest-bucle))
                                nueva-cant-bucle (- cant-bucle 1)
                                nuevo-temp-bucle (rest temp-bucle)]
                            (recur nueva-cant-bucle nuevo-dest-bucle nuevo-temp-bucle))))
        nuevo-origen (loop [cant-bucle cantidad
                            origen-bucle loc-origen]
                       (if (= cant-bucle 0)
                         origen-bucle
                         (let [nuevo-origen-bucle (pop origen-bucle)
                               nueva-cant-bucle (- cant-bucle 1)]
                           (recur nueva-cant-bucle nuevo-origen-bucle))))
        estado-salida (-> ary-estado
                          (assoc origen nuevo-origen)
                          (assoc destino nuevo-destino))]
    (reverse (into '() estado-salida))))

; procesar-ordenes :: (((Char)) -> Dictionary -> ((Char))) ->((Char)) -> (Dictionary) -> ((Char))
(defn procesar-ordenes [funcion estado lista-ordenes]
  (if (= lista-ordenes '())
    estado
    (let [orden (first lista-ordenes)
          nuevo-estado (funcion estado orden)
          nueva-lista-ordenes (rest lista-ordenes)]
      (recur funcion nuevo-estado nueva-lista-ordenes))))


(defn -main []
  (let [estado-inicial '((\W \R \T \G) (\W \V \S \M \P \H \C \G) (\M \G \S \T \L \C) (\F \R \W \M \D \H \J)
                                       (\J \F \W \S \H \L \Q \P) (\S \M \F \N \D \J \P) (\J \S \C \G \F \D \B \Z)
                                       (\B \T \R) (\C \L \W \N \H))
        lista-entrada (->> "./resources/input.lst"
                           (slurp)
                           (st/split-lines))
        tarea-1 (->> lista-entrada
                     (map cad->dict)
                     (procesar-ordenes procesar-orden estado-inicial)
                     (lista->cad))
        tarea-2 (->> lista-entrada
                     (map cad->dict)
                     (procesar-ordenes procesar-orden-multiple estado-inicial)
                     (lista->cad))]
    (->> tarea-1
         (println))
    (->> tarea-2
         (println))))
