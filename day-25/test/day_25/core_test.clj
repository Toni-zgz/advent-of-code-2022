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
    (test/is (= (snafu->decimal "122") 37)))
  (test/testing "Conversion decimal a SNAFU"
    (test/is (= (decimal->snafu 1747) "1=-0-2"))
    (test/is (= (decimal->snafu 906) "12111"))
    (test/is (= (decimal->snafu 198) "2=0="))
    (test/is (= (decimal->snafu 11) "21"))
    (test/is (= (decimal->snafu 201) "2=01"))
    (test/is (= (decimal->snafu 31) "111"))
    (test/is (= (decimal->snafu 1257) "20012"))
    (test/is (= (decimal->snafu 32) "112"))
    (test/is (= (decimal->snafu 353) "1=-1="))
    (test/is (= (decimal->snafu 107) "1-12"))
    (test/is (= (decimal->snafu 7) "12"))
    (test/is (= (decimal->snafu 3) "1="))
    (test/is (= (decimal->snafu 37) "122"))))

 
