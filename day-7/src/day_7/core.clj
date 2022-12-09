(ns day-7.core
  (:gen-class)
  (:require [clojure.string :as st]))

; crear-archivo :: String -> Long -> Diccionario
(defn crear-archivo [nombre longitud]
  {:tipo :archivo,
   :nombre nombre,
   :longitud longitud})

; crear-directorio :: String -> Diccionario
(defn crear-directorio [nombre]
  {:tipo :directorio,
   :nombre nombre,
   :contenido '()})

; cambiar-directorio :: (String) -> (String) -> (String)
(defn cambiar-directorio [navegacion argumentos]
  (let [argumento (first argumentos)]
    (cond (= argumento "..") (rest navegacion)
        :else (conj navegacion argumento))))

; anadir-estado :: Diccionario -> Diccionario -> (String) -> Diccionario
(defn anadir-estado [nuevo-estado estado navegacion]
  (let [directorio (first navegacion)
        ]))

; modificar-estado :: Diccionario -> (String) -> (String) -> Diccionario
(defn modificar-estado [estado navegacion lista-ordenes]
  (let [linea (first lista-ordenes)
        lista-linea (st/split linea #" ")
        long-o-dir (first lista-linea)
        nombre (second lista-linea)]
    (if (= long-o-dir "$") 
      estado
      (let [nueva-lista-ordenes (rest lista-ordenes)
            nuevo-estado (cond (number? long-o-dir) (crear-archivo nombre long-o-dir)
                               (= long-o-dir "dir") (crear-directorio nombre))
            estado-agregado ((anadir-estado nuevo-estado estado navegacion))]
        (recur estado-agregado navegacion nueva-lista-ordenes)))))

; procesar :: [String] -> Diccionario
(defn procesar [lista-ordenes]
  (let [estado-inicial (crear-directorio "/")
        navegacion-inicial '("/")]
    (loop [lista lista-ordenes
           navegacion navegacion-inicial
           estado estado-inicial]
      (if (= lista '())
        estado
        (let [nueva-lista (rest lista)
              primera-orden (-> lista
                                (first)
                                (st/split #" "))
              comando (first primera-orden)
              argumentos (rest primera-orden)
              nueva-navegacion (cond (= comando "cd") (cambiar-directorio navegacion argumentos)
                                     :else navegacion)
              nuevo-estado (cond (= comando "ls") (modificar-estado estado navegacion nueva-lista)
                                 :else estado)]
          (recur nueva-lista nueva-navegacion nuevo-estado))))))

(defn -main []
  (let [lista-entrada (->> "./resources/input.lst"
                          (slurp)
                           (st/split-lines))
       tarea-1 (->> lista-entrada
                    (procesar))
       tarea-2 (->> lista-entrada
                    (count))]
    (->> tarea-1
         (println))
    (->> tarea-2
         (println))))
