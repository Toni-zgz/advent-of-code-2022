(ns day-10.core-test
  (:require [clojure.test :as test]
            [clojure.string :as st]
            [day-10.core :refer :all]))

(test/deftest day-10
  (test/testing "noop"
    (test/is (= (noop '([1 2 3] 3)) '([1 2 3 3] 3))))
  (test/testing "addx"
    (test/is (= (addx '([1 2 3 4 5] 5) "7") '([1 2 3 4 5 5 5] 12))))
  (test/testing "procesar"
    (let [codigo "noop
                   addx 3
                   addx -5"
          lista (->> codigo
                     (st/split-lines)
                     (map st/trim))]
      (test/is (= (procesar lista) [1 1 1 1 4 4]))))
  (let [codigo "addx 15
                  addx -11
                  addx 6
                  addx -3
                  addx 5
                  addx -1
                  addx -8
                  addx 13
                  addx 4
                  noop
                  addx -1
                  addx 5
                  addx -1
                  addx 5
                  addx -1
                  addx 5
                  addx -1
                  addx 5
                  addx -1
                  addx -35
                  addx 1
                  addx 24
                  addx -19
                  addx 1
                  addx 16
                  addx -11
                  noop
                  noop
                  addx 21
                  addx -15
                  noop
                  noop
                  addx -3
                  addx 9
                  addx 1
                  addx -3
                  addx 8
                  addx 1
                  addx 5
                  noop
                  noop
                  noop
                  noop
                  noop
                  addx -36
                  noop
                  addx 1
                  addx 7
                  noop
                  noop
                  noop
                  addx 2
                  addx 6
                  noop
                  noop
                  noop
                  noop
                  noop
                  addx 1
                  noop
                  noop
                  addx 7
                  addx 1
                  noop
                  addx -13
                  addx 13
                  addx 7
                  noop
                  addx 1
                  addx -33
                  noop
                  noop
                  noop
                  addx 2
                  noop
                  noop
                  noop
                  addx 8
                  noop
                  addx -1
                  addx 2
                  addx 1
                  noop
                  addx 17
                  addx -9
                  addx 1
                  addx 1
                  addx -3
                  addx 11
                  noop
                  noop
                  addx 1
                  noop
                  addx 1
                  noop
                  noop
                  addx -13
                  addx -19
                  addx 1
                  addx 3
                  addx 26
                  addx -30
                  addx 12
                  addx -1
                  addx 3
                  addx 1
                  noop
                  noop
                  noop
                  addx -9
                  addx 18
                  addx 1
                  addx 2
                  noop
                  noop
                  addx 9
                  noop
                  noop
                  noop
                  addx -1
                  addx 2
                  addx -37
                  addx 1
                  addx 3
                  noop
                  addx 15
                  addx -21
                  addx 22
                  addx -6
                  addx 1
                  noop
                  addx 2
                  addx 1
                  noop
                  addx -10
                  noop
                  noop
                  addx 20
                  addx 1
                  addx 2
                  addx 2
                  addx -6
                  addx -11
                  noop
                  noop
                  noop"
        lista (->> codigo
                   (st/split-lines)
                   (map st/trim))
        estado (procesar lista)
        pantalla ["##..##..##..##..##..##..##..##..##..##.."
                  "###...###...###...###...###...###...###."
                  "####....####....####....####....####...."
                  "#####.....#####.....#####.....#####....."
                  "######......######......######......####"
                  "#######.......#######.......#######....."]]
    (test/testing "fuerza-senal"
      (test/is (= (get estado 20) 21))
      (test/is (= (get estado 60) 19))
      (test/is (= (get estado 100) 18))
      (test/is (= (get estado 140) 21))
      (test/is (= (get estado 180) 16))
      (test/is (= (get estado 220) 18))
      (test/is (= (fuerza-senal estado) 13140)))
    (test/testing "dibujar-pixels"
      (test/is (= (->> estado
                       (dibujar-pixels)
                       (map st/join)
                       (vec))
                  pantalla)))))
