(ns day-24.core-test
  (:require [clojure.test :as test]
            [day-24.core :refer :all]))

(test/deftest day-24-test
  (let [ary-1 [[false false]
               [false false]
               [false false]]
        ary-2 [[false false]
               [false false]
               [false true]]
        ary-3 [["#" "." "#" "#" "#" "#" "#"]
               ["#" "." "." "." "." "." "#"]
               ["#" ">" "." "." "." "." "#"]
               ["#" "." "." "." "." "." "#"]
               ["#" "." "." "." "v" "." "#"]
               ["#" "." "." "." "." "." "#"]
               ["#" "#" "#" "#" "#" "." "#"]]
        ary-4 [["#" "." "#" "#" "#" "#" "#"]
               ["#" "." "." "." "." "." "#"]
               ["#" "." ">" "." "." "." "#"]
               ["#" "." "." "." "." "." "#"]
               ["#" "." "." "." "." "." "#"]
               ["#" "." "." "." "v" "." "#"]
               ["#" "#" "#" "#" "#" "." "#"]]
        inicial '({:pos (0 0), :dir (0 0)}
                  {:pos (0 2), :dir (0 0)}
                  {:pos (0 3), :dir (0 0)}
                  {:pos (0 4), :dir (0 0)}
                  {:pos (0 5), :dir (0 0)}
                  {:pos (0 6), :dir (0 0)}
                  {:pos (1 0), :dir (0 0)}
                  {:pos (1 6), :dir (0 0)}
                  {:pos (2 0), :dir (0 0)}
                  {:pos (2 1), :dir (0 1)}
                  {:pos (2 6), :dir (0 0)}
                  {:pos (3 0), :dir (0 0)}
                  {:pos (3 6), :dir (0 0)}
                  {:pos (4 0), :dir (0 0)}
                  {:pos (4 4), :dir (1 0)}
                  {:pos (4 6), :dir (0 0)}
                  {:pos (5 0), :dir (0 0)}
                  {:pos (5 6), :dir (0 0)}
                  {:pos (6 0), :dir (0 0)}
                  {:pos (6 1), :dir (0 0)}
                  {:pos (6 2), :dir (0 0)}
                  {:pos (6 3), :dir (0 0)}
                  {:pos (6 4), :dir (0 0)}
                  {:pos (6 6), :dir (0 0)})
        posterior '({:pos (0 0), :dir (0 0)}
                    {:pos (0 2), :dir (0 0)}
                    {:pos (0 3), :dir (0 0)}
                    {:pos (0 4), :dir (0 0)}
                    {:pos (0 5), :dir (0 0)}
                    {:pos (0 6), :dir (0 0)}
                    {:pos (1 0), :dir (0 0)}
                    {:pos (1 6), :dir (0 0)}
                    {:pos (2 0), :dir (0 0)}
                    {:pos (2 2), :dir (0 1)}
                    {:pos (2 6), :dir (0 0)}
                    {:pos (3 0), :dir (0 0)}
                    {:pos (3 6), :dir (0 0)}
                    {:pos (4 0), :dir (0 0)}
                    {:pos (5 4), :dir (1 0)}
                    {:pos (4 6), :dir (0 0)}
                    {:pos (5 0), :dir (0 0)}
                    {:pos (5 6), :dir (0 0)}
                    {:pos (6 0), :dir (0 0)}
                    {:pos (6 1), :dir (0 0)}
                    {:pos (6 2), :dir (0 0)}
                    {:pos (6 3), :dir (0 0)}
                    {:pos (6 4), :dir (0 0)}
                    {:pos (6 6), :dir (0 0)})]
    (test/testing "arrays 2D"
      (test/is (= (crear-array2D 3 2 false) ary-1))
      (test/is (= (escribir-array2D ary-1 2 1 true) ary-2))
      (test/is (= (leer-array2D ary-2 2 1) true)))
    (test/testing "Obtener objetos desde el tablero"
      (test/is (= (obtener-objetos ary-3) inicial)))
    (test/testing "Mover objetos por el tablero"
      (test/is (= (actualizar-objetos inicial 7 7) posterior)))
    (test/testing "Imprimimos tableros"
      (test/is (= (imprimir-tablero 7 7 inicial) ary-3))
      (test/is (= (imprimir-tablero 7 7 posterior) ary-4)))
    (test/testing "Obtener posiciones validas"
      (test/is (= (obtener-posiciones-validas 5 4 ary-3) '((5 4) (5 5) (5 3)))))))
