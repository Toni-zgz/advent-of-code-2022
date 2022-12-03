(ns day-3.core-test
  (:require [clojure.test :refer :all]
            [day-3.core :refer :all]))

(defn prueba-tarea-1 [cadena]
  (->> cadena
       (convertir-a-enteros)
       (dividir-cadenas)
       (obtener-duplicados-tarea-1)
       (first)
       (char)))

(defn prueba-tarea-2 [lista]
  (->> lista
       (map convertir-a-enteros) 
       (obtener-duplicados-tarea-2)
       (first)
       (char)))

(deftest a-test
  (testing "Tarea 1"
    (is (= (->> "vJrwpWtwJgWrhcsFMMfFFhFp"
                (prueba-tarea-1))  \p))
    (is (= (->> "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                (prueba-tarea-1)) \L))
    (is (= (->> "PmmdzqPrVvPwwTWBwg"
                (prueba-tarea-1)) \P))
    (is (= (->> "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                (prueba-tarea-1)) \v))
    (is (= (->> "ttgJtRGJQctTZtZT"
                (prueba-tarea-1)) \t))
    (is (= (->> "CrZsJsPPZsGzwwsLwLmpwMDw"
                (prueba-tarea-1)) \s)))
  (testing "Tarea 2"
    (is (= (->> '("vJrwpWtwJgWrhcsFMMfFFhFp" "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL" "PmmdzqPrVvPwwTWBwg")
                (prueba-tarea-2)) \r))
    (is (= (->> '("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn" "ttgJtRGJQctTZtZT" "CrZsJsPPZsGzwwsLwLmpwMDw")
                (prueba-tarea-2)) \Z))))