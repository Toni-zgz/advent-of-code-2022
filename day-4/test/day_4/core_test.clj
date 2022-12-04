(ns day-4.core-test
  (:require [clojure.test :refer :all]
            [day-4.core :refer :all]))

(deftest a-test
  (testing "Test day 4"
    (is (= (cadena->diccionario "2-4,6-8") {:elfo1-min 2, :elfo1-max 4, :elfo2-min 6 :elfo2-max 8}))
    (is (= (-> "2-4,6-8"
               (cadena->diccionario)
               (esta-dentro-de))
           false))
    (is (= (-> "2-3,4-5"
               (cadena->diccionario)
               (esta-dentro-de))
           false))
    (is (= (-> "5-7,7-9"
               (cadena->diccionario)
               (esta-dentro-de))
           false))
    (is (= (-> "2-8,3-7"
               (cadena->diccionario)
               (esta-dentro-de))
           true))
    (is (= (-> "6-6,4-6"
               (cadena->diccionario)
               (esta-dentro-de))
           true))
    (is (= (-> "2-6,4-8"
               (cadena->diccionario)
               (esta-dentro-de))
           false))
    (is (= (-> "2-4,6-8"
               (cadena->diccionario)
               (se-superponen))
           false))
    (is (= (-> "2-3,4-5"
               (cadena->diccionario)
               (se-superponen))
           false))
    (is (= (-> "5-7,7-9"
               (cadena->diccionario)
               (se-superponen))
           true))
    (is (= (-> "2-8,3-7"
               (cadena->diccionario)
               (se-superponen))
           true))
    (is (= (-> "6-6,4-6"
               (cadena->diccionario)
               (se-superponen))
           true))
    (is (= (-> "2-6,4-8"
               (cadena->diccionario)
               (se-superponen))
           true))))
