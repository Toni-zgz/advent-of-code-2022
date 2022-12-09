(ns day-7.core-test
  (:require [clojure.test :as test]
            [day-7.core :refer :all]))

  (test/deftest dia-7
    (test/testing "creación de archivo y directorio"
      (test/is (= (crear-archivo "D" 14457) {:tipo :archivo, :nombre "D", :longitud 14457}))
      (test/is (= (crear-directorio "C") {:tipo :directorio, :nombre "C", :contenido '()})))
    (test/testing "tests de navegacion"
      (test/is (= (cambiar-directorio '("/") '("d")) '("d" "/")))
      (test/is (= (cambiar-directorio '("d" "/") '("..")) '("/")))))