(ns day-21.core-test
  (:require [clojure.test :as test]
  (:require [clojure.test :as test]
            [day-21.core :refer :all]))

(test/deftest day21-test
  (test/testing "comprobando operar"
    (test/is (= (operar) '(152)))))
