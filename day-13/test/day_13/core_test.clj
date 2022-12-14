(ns day-13.core-test
  (:require [clojure.test :as test]
            [day-13.core :refer :all]))

(test/deftest day-13
  (let [cad "[1,1,3,1,1]
             [1,1,5,1,1]
               
             [[1],[2,3,4]]
             [[1],4]
           
             [9]
             [[8,7,6]]
           
             [[4,4],4,4]
             [[4,4],4,4,4]
          
             [7,7,7,7]
             [7,7,7]
           
             []
             [3]
           
             [[[]]]
             [[]]
           
             [1,[2,[3,[4,[5,6,7]]]],8,9]
             [1,[2,[3,[4,[5,6,0]]]],8,9]"
        lista1 '([[1 1 3 1 1]
                  [[1] [2 3 4]]
                  [9]
                  [[4 4] 4 4]
                  [7 7 7 7]
                  []
                  [[[]]]
                  [1 [2 [3 [4 [5 6 7]]]] 8 9]]
                 [[1 1 5 1 1]
                  [[1] 4]
                  [[8 7 6]]
                  [[4 4] 4 4 4]
                  [7 7 7]
                  [3]
                  [[]]
                  [1 [2 [3 [4 [5 6 0]]]] 8 9]])
        ary1-i [1 1 3 1 1]
        ary1-d [1 1 5 1 1]
        ary2-i [[1] [2 3 4]]
        ary2-d [[1] 4]
        ary3-i [9]
        ary3-d [[8 7 6]]
        ary4-i [[4 4] 4 4]
        ary4-d [[4 4] 4 4 4]
        ary5-i [7 7 7 7]
        ary5-d [7 7 7]
        ary6-i []
        ary6-d [3]
        ary7-i [[[]]]
        ary7-d [[]]
        ary8-i [1 [2 [3 [4 [5 6 7]]]] 8 9]
        ary8-d [1 [2 [3 [4 [5 6 0]]]] 8 9]
        lista2 '([[1 1 3 1 1]
                  [[1] [2 3 4]]
                  [9]
                  [[4 4] 4 4]
                  [7 7 7 7]
                  []
                  [[[]]]
                  [1 [2 [3 [4 [5 6 7]]]] 8 9]]
                 [[1 1 5 1 1]
                  [[1] 4]
                  [[8 7 6]]
                  [[4 4] 4 4 4]
                  [7 7 7]
                  [3]
                  [[]]
                  [1 [2 [3 [4 [5 6 0]]]] 8 9]])
        ary9 [true true false true false true false false]
        ary10 [1 2 4 6]]
    (test/testing "Probando la carga de funciones"
      (test/is (= (cargar-datos cad) lista1)))
    (test/testing "Probando la función orden-correcto?"
      (test/is (= (orden-correcto? ary1-i ary1-d) true))
      (test/is (= (orden-correcto? ary2-i ary2-d) true))
      (test/is (= (orden-correcto? ary3-i ary3-d) false))
      (test/is (= (orden-correcto? ary4-i ary4-d) true))
      (test/is (= (orden-correcto? ary5-i ary5-d) false))
      (test/is (= (orden-correcto? ary6-i ary6-d) true))
      (test/is (= (orden-correcto? ary7-i ary7-d) false))
      (test/is (= (orden-correcto? ary8-i ary8-d) false)))
    (test/testing "Probando la funcion procesar-lista"
      (test/is (= (procesar-lista lista2) ary9)))
    (test/testing "Probando la función obtener-indices"
      (test/is (= (obtener-indices ary9) ary10)))))






