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
        objetos '({:pos (2 1), :dir (0 1)}
                  {:pos (4 4), :dir (1 0)})]
    (test/testing "arrays 2D"
      (test/is (= (crear-array2D 3 2 false) ary-1))
      (test/is (= (escribir-array2D ary-1 2 1 true) ary-2))
      (test/is (= (leer-array2D ary-2 2 1) true)))
    (test/testing "Obtener objetos desde el tablero"
      (test/is (= (obtener-objetos ary-3) objetos)))))
