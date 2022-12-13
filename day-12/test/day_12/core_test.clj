(ns day-12.core-test
  (:require [clojure.test :as test]
            [day-12.core :refer :all]
            [clojure.string :as st]))

(test/deftest day-12
  (test/testing "crear, leer y escribir en arrays 2D "
    (let [ary1 [["." "."]
                ["." "."]
                ["." "."]]
          ary2 [["." "."]
                ["." "."]
                ["." "X"]]]
      (test/is (= (crear-array-2d 3 2 ".") ary1))
      (test/is (= (escribir-array-2d ary1 2 1 "X") ary2))
      (test/is (= (leer-array-2d ary2 2 1) "X"))))
  (test/testing "obteniendo posiciones candidatas y prometedoras"
    (let [cad-alturas "Sabqponm
                       abcryxxl
                       accszExk
                       acctuvwj
                       abdefghi"
          ary-alturas (->> cad-alturas
                           (st/split-lines)
                           (map #(st/trim %))
                           (map (fn [elt] (st/split elt #"")))
                           (map (fn [linea] (->> linea 
                                                 (map (fn [elt] (-> elt
                                                                    (.charAt 0)
                                                                    (int)
                                                                    (- 97))))
                                                 (vec))))
                           (vec))
          cad-visitado "v......
                        vv.....
                        .v.....
                        .......
                        ......."
          ary-visitado (->> cad-visitado
                            (st/split-lines)
                            (map #(st/trim %))
                            (vec)
                            (map (fn [elt] (st/split elt #""))) 
                            (vec))]
      (test/is (= (obtener-posiciones-candidatas ary-alturas 3 4) '((2 4) (4 4) (3 5) (3 3))))
      (test/is (= (obtener-posiciones-candidatas ary-alturas 0 4) '(() (1 4) (0 5) (0 3))))
      (test/is (= (obtener-posiciones-candidatas ary-alturas 4 3) '((3 3) () (4 4) (4 2))))
      (test/is (= (obtener-posiciones-candidatas ary-alturas 2 0) '((1 0) (3 0) (2 1) ())))
      (test/is (= (obtener-posiciones-candidatas ary-alturas 3 7) '((2 7) (4 7) () (3 6))))
      (test/is (= (obtener-posiciones-candidatas ary-alturas 0 0) '(() (1 0) (0 1) ())))
      (test/is (= (obtener-posiciones-candidatas ary-alturas 4 7) '((3 7) () () (4 6))))
      (test/is (= (obtener-posiciones-prometedoras ary-alturas ary-visitado 2 2 '((1 2) (3 2) (2 1) (2 3))) 
                  '((1 2) (3 2)))))))
