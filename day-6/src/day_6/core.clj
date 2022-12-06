(ns day-6.core
  (:gen-class))

; obtener-subcadena :: String -> Long -> Long -> String
(defn obtener-subcadena [cadena indice-superior longitud]
  (let [indice-inferior (- indice-superior longitud)]
    (subs cadena indice-inferior indice-superior)))

; son-todas-distintas :: String -> Boolean
(defn son-todas-distintas [cadena]
  (let [num-carac (count cadena)]
    (->> cadena
         (set)
         (count)
         (= num-carac))))

; ultimo-caracter-marcador :: String -> Long
(defn ultimo-caracter-marcador [longitud cadena]
  (loop [indice longitud]
    (if (= indice (count cadena))
      '()
      (let [subcadena (obtener-subcadena cadena indice longitud)]
      (if (son-todas-distintas subcadena)
        indice
        (recur (+ indice 1)))))))

(defn -main []
 (let [lista-entrada (->> "./resources/input.lst"
                          (slurp))
       tarea-1 (->> lista-entrada
                    (ultimo-caracter-marcador 4))
       tarea-2 (->> lista-entrada
                    (ultimo-caracter-marcador 14))]
    (->> tarea-1
         (println))
    (->> tarea-2
         (println))))
