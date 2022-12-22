(ns day-21.core
  (:gen-class)
  (:require [clojure.core.logic :as logic])
  (:require [clojure.core.logic.fd :as fd]))

(defn operar []
  (logic/run 1 [q]
             (logic/fresh [root dbpl cczh zczc ptdq dvpt lfqf humn ljgn sjmn sllz pppw lgvd drzm hmdt]
                          (logic/== q root)
                          (fd/+ pppw sjmn root)
                          (logic/== dbpl 5)
                          (fd/+ sllz lgvd cczh)
                          (logic/== zczc 2)
                          (fd/- humn dvpt ptdq)
                          (logic/== dvpt 3)
                          (logic/== lfqf 4)
                          (logic/== humn 5)
                          (logic/== ljgn 2)
                          (fd/* drzm dbpl sjmn)
                          (logic/== sllz 4)
                          (fd/quot cczh lfqf pppw)
                          (fd/* ljgn ptdq lgvd)
                          (fd/- hmdt zczc drzm)
                          (logic/== hmdt 32))))