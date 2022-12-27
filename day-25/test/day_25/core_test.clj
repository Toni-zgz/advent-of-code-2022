(ns day-25.core-test
  (:require [clojure.test :as test]
            [day-25.core :refer :all]))

(test/deftest day25-test
  (test/testing "Conversion SNAFU a decimal"
    (test/is (= (snafu->decimal "1=-0-2") 1747))
    (test/is (= (snafu->decimal "12111") 906))
    (test/is (= (snafu->decimal "2=0=") 198))
    (test/is (= (snafu->decimal "21") 11))
    (test/is (= (snafu->decimal "2=01") 201))
    (test/is (= (snafu->decimal "111") 31))
    (test/is (= (snafu->decimal "20012") 1257))
    (test/is (= (snafu->decimal "112") 32))
    (test/is (= (snafu->decimal "1=-1=") 353))
    (test/is (= (snafu->decimal "1-12") 107))
    (test/is (= (snafu->decimal "12") 7))
    (test/is (= (snafu->decimal "1=") 3))
    (test/is (= (snafu->decimal "122") 37))))

 
