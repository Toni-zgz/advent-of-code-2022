(ns day-21.core-test
  (:require [clojure.test :as test]
            [day-21.core :refer :all]))

(test/deftest a-test
  (test/testing "comprobando operar"
    (test/is (= (operar) '(152)))))
