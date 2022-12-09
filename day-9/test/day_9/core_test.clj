(ns day-9.core-test
  (:require [clojure.test :as test]
            [day-9.core :refer :all]))

(test/deftest tests-day-9
  (test/testing "convert-data"
    (test/is (= (convert-data "D 3") '("D" "D" "D"))))
  (test/testing "adyacente?"
    (test/is (= (adyacente? 0 0 0 0) true))
    (test/is (= (adyacente? 0 1 0 0) true))
    (test/is (= (adyacente? 0 2 0 0) false))
    (test/is (= (adyacente? 1 4 0 3) true))
    (test/is (= (adyacente? 2 4 0 3) false)))
  (test/testing "mover-cola"
    (test/is (= (mover-cola 1 3 1 1) '(1 2)))
    (test/is (= (mover-cola 3 1 1 1) '(2 1)))
    (test/is (= (mover-cola 1 2 3 1) '(2 2)))
    (test/is (= (mover-cola 2 3 3 1) '(2 2)))) 
  (test/testing "bucle"
    (test/is (= (->> '("R 4" "U 4" "L 3" "D 1" "R 4" "D 1" "L 5" "R 2")
                     (map convert-data)
                     (flatten)
                     (procesar-datos 2)
                     (count)) 13))
    (test/is (= (->> '("R 4" "U 4" "L 3" "D 1" "R 4" "D 1" "L 5" "R 2")
                     (map convert-data)
                     (flatten)
                     (procesar-datos 10)
                     (count)) 1))
    (test/is (= (->> '("R 5" "U 8" "L 8" "D 3" "R 17" "D 10" "L 25" "U 20")
                     (map convert-data)
                     (flatten)
                     (procesar-datos 2)
                     (count)) 88))))
