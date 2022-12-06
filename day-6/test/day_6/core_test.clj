(ns day-6.core-test
  (:require [clojure.test :as test]
            [day-6.core :refer :all]))

(test/deftest test-day-6
  (test/testing "función obtener-subcadena-de-4"
    (test/is (= (obtener-subcadena "bvwbjplbgvbhsrlpgdmjqwftvncz" 4 4) "bvwb")))
  (test/testing "función son-todas-distintas"
    (test/is (= (son-todas-distintas "abcd") true))
    (test/is (= (son-todas-distintas "abca") false)))
  (test/testing "función ultimo-caracter-marcador-4"
    (test/is (= (ultimo-caracter-marcador 4 "bvwbjplbgvbhsrlpgdmjqwftvncz") 5))
    (test/is (= (ultimo-caracter-marcador 4 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 7))
    (test/is (= (ultimo-caracter-marcador 4 "nppdvjthqldpwncqszvftbrmjlhg") 6))
    (test/is (= (ultimo-caracter-marcador 4 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 10))
    (test/is (= (ultimo-caracter-marcador 4 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 11)))
  (test/testing "función ultimo-caracter-marcador-14"
    (test/is (= (ultimo-caracter-marcador 14 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 19))
    (test/is (= (ultimo-caracter-marcador 14 "bvwbjplbgvbhsrlpgdmjqwftvncz") 23))
    (test/is (= (ultimo-caracter-marcador 14 "nppdvjthqldpwncqszvftbrmjlhg") 23))
    (test/is (= (ultimo-caracter-marcador 14 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 29))
    (test/is (= (ultimo-caracter-marcador 14 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 26))))
