(ns day-8.core-test
  (:require [clojure.test :as test]
            [day-8.core :refer :all]))

((test/deftest test-day8
   (let [vector1 [[false false false]
                  [false false false]
                  [false false false]]
         vector2 [[3 0 3 7 3]
                  [2 5 5 1 2]
                  [6 5 3 3 2]
                  [3 3 5 4 9]
                  [3 5 3 9 0]]
         vector3 [[3 0 3 7 3]
                  [2 5 5 1 2]
                  [6 5 3 3 2]
                  [3 3 5 0 9]
                  [3 5 3 9 0]]
         vector4 [[true true true true true]
                  [true true true false true]
                  [true true false true true]
                  [true false true false true]
                  [true true true true true]]
         vector5 [[1 1 1 1 1]
                  [1 1 1 0 1]
                  [1 1 0 1 1]
                  [1 0 1 0 1]
                  [1 1 1 1 1]]
          vector6 [[0 0 0 0 0]
                   [0 1 4 1 0]
                   [0 6 1 2 0]
                   [0 1 8 3 0]
                   [0 0 0 0 0]]]
     (test/testing "vector 2d"
       (test/is (= (crear-vector2d 3 3) vector1))
       (test/is (= (obtener-vector2d vector2 1 1) 5))
       (test/is (= (escribir-vector2d vector2 3 3 0) vector3)))
     (test/testing "predicados"
       (test/is (= (visible-arriba? vector2 2 2 5 5) false))
       (test/is (= (visible-abajo? vector2 3 2 5 5) true))
       (test/is (= (visible-derecha? vector2 2 1 5 5) true))
       (test/is (= (visible-izquierda? vector2 1 2 5 5) false)))
     (test/testing "arboles visibles"
       (test/is (= (arboles-visibles? vector2) vector4)))
     (test/testing "vector2d-bool->int"
       (test/is (= (vector2d-bool->int vector4) vector5)))
     (test/testing "contar-arboles-visibles"
       (test/is (= (contar-arboles-visibles vector5) 21)))
     (test/testing "numero-arboles"
       (test/is (= (arboles-arriba vector2 1 2 5 5) 1))
       (test/is (= (arboles-abajo vector2 1 2 5 5) 2))
       (test/is (= (arboles-derecha vector2 1 2 5 5) 2))
       (test/is (= (arboles-izquierda vector2 1 2 5 5) 1))
       (test/is (= (arboles-arriba vector2 3 2 5 5) 2))
       (test/is (= (arboles-abajo vector2 3 2 5 5) 1))
       (test/is (= (arboles-derecha vector2 3 2 5 5) 2))
       (test/is (= (arboles-izquierda vector2 3 2 5 5) 2)))
     (test/testing "puntuaciones-escenicas"
       (test/is (= (puntuaciones-escenicas vector2) vector6)))
     (test/testing "puntuacion-maxima"
       (test/is (= (puntuacion-maxima vector2) 9))
       (test/is (= (puntuacion-maxima vector6) 8))))))

