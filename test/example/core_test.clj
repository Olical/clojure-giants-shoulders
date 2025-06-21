(ns ^:typed.clojure example.core-test
  (:require [clojure.test :as t]
            [example.core :as ex]))

(t/deftest add
  (t/testing "it adds stuff"
    (t/is (= 2 (ex/add 1 1)))))
