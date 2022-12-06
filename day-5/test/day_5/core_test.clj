(ns day-5.core-test
  (:require [clojure.test :as test]
            [day-5.core :refer :all]))

(let [;inicial-cad '("NZ" "DCM" "P")
      inicial-lst '((\N \Z) (\D \C \M) (\P))
      orden1-cad "move 1 from 2 to 1"
      orden1-dic {:cantidad 1 :origen 2 :destino 1}
      despues-orden1 '((\D \N \Z) (\C \M) (\P))
      despues-orden1-multiple '((\D \N \Z) (\C \M) (\P))
      despues-orden1-cad "DCP"
      orden2-cad "move 3 from 1 to 3"
      orden2-dic {:cantidad 3 :origen 1 :destino 3}
      despues-orden2 '(() (\C \M) (\Z \N \D \P))
      despues-orden2-multiple '(() (\C \M) (\D \N \Z \P))
      despues-orden2-cad " CZ"
      orden3-cad "move 2 from 2 to 1"
      orden3-dic {:cantidad 2 :origen 2 :destino 1}
      despues-orden3 '((\M \C) () (\Z \N \D \P))
      despues-orden3-multiple '((\C \M) () (\D \N \Z \P))
      despues-orden3-cad "M Z"
      orden4-cad "move 1 from 1 to 2"
      orden4-dic {:cantidad 1 :origen 1 :destino 2}
      despues-orden4  '((\C) (\M) (\Z \N \D \P))
      despues-orden4-multiple  '((\M) (\C) (\D \N \Z \P))
      despues-orden4-cad "CMZ"]
  ;(test/testing "Comprobando función cad->lista"
    ;(test/is (= (map cad->lista inicial-cad) inicial-lst)))
  (test/testing "Comprobando función cad->dict"
    ; conversión de la cadena de las ordenes en un diccionario
    (test/is (= (cad->dict orden1-cad) orden1-dic))
    (test/is (= (cad->dict orden2-cad) orden2-dic))
    (test/is (= (cad->dict orden3-cad) orden3-dic))
    (test/is (= (cad->dict orden4-cad) orden4-dic)))
  (test/testing "Comprobando función lista->cad"
    ; conversión de la lista de estado en una cadena
    (test/is (= (lista->cad despues-orden1) despues-orden1-cad))
    (test/is (= (lista->cad despues-orden2) despues-orden2-cad))
    (test/is (= (lista->cad despues-orden3) despues-orden3-cad))
    (test/is (= (lista->cad despues-orden4) despues-orden4-cad)))
  (test/testing "Comprobando función procesar-orden"
    ; actualización del estado despues de una orden
    (test/is (= (procesar-orden inicial-lst orden1-dic) despues-orden1))
    (test/is (= (procesar-orden despues-orden1 orden2-dic) despues-orden2))
    (test/is (= (procesar-orden despues-orden2 orden3-dic) despues-orden3))
    (test/is (= (procesar-orden despues-orden3 orden4-dic) despues-orden4)))
  (test/testing "Comprobando función procesar-orden"
; actualización del estado despues de una orden
    (test/is (= (procesar-orden-multiple inicial-lst orden1-dic) despues-orden1-multiple))
    (test/is (= (procesar-orden-multiple despues-orden1-multiple orden2-dic) despues-orden2-multiple))
    (test/is (= (procesar-orden-multiple despues-orden2-multiple orden3-dic) despues-orden3-multiple))
    (test/is (= (procesar-orden-multiple despues-orden3-multiple orden4-dic) despues-orden4-multiple))))
    