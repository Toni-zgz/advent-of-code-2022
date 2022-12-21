(ns day-14.core-test
  (:require [clojure.test :as test]
            [day-14.core :refer :all]))

(test/deftest a-test
  (let [cad1 "498,4 -> 498,6 -> 496,6"
        cad2 "503,4 -> 502,4 -> 502,9 -> 494,9"
        ary1 ['(498 4) '(498 6) '(496 6)]
        ary2 ['(503 4) '(502 4) '(502 9) '(494 9)]
        ary-g (vector ary1 ary2)
        lineas1 (list '(498 4) '(498 5) '(498 6) '(496 6) '(497 6))
        lineas2 (list '(502 4) '(503 4) '(502 5) '(502 6) '(502 7) '(502 8) '(502 9)
                      '(494 9) '(495 9) '(496 9) '(497 9) '(498 9) '(499 9) '(500 9) 
                      '(501 9))]

    (test/testing "Comprobando función cad->ary"
      (test/is (= (cad->ary cad1) ary1))
      (test/is (= (cad->ary cad2) ary2)))
    
    (test/testing "Comprobando valores maximos y minimos"
      (test/is (= (obtener-maximo-x ary-g) 503))
      (test/is (= (obtener-maximo-y ary-g) 9))
      (test/is (= (obtener-minimo-x ary-g) 494))
      (test/is (= (obtener-minimo-y ary-g) 4)))
    
    (test/testing "lineas"
      (test/is (= (obtener-puntos ary1) lineas1))
      (test/is (= (obtener-puntos ary2) lineas2)))))
