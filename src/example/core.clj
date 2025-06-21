(ns ^:typed.clojure example.core
  (:require [typed.clojure :as tc]))

(tc/ann add [tc/Int tc/Int :-> tc/Int])
(defn add
  "If you have https://github.com/Olical/typedclojure-lsp configured correctly you should see a type error / warning if you try to type (add :foo 10) inside this buffer."
  [a b]
  (+ a b))
