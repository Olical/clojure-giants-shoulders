(ns ^:typed.clojure example.core
  (:require [typed.clojure :as t]))

(t/ann add [t/Int t/Int :-> t/Int])
(defn add
  "If you have https://github.com/Olical/typedclojure-lsp configured correctly you should see a type error / warning if you try to type (add :foo 10) inside this buffer."
  [a b]
  (+ a b))

#_(defn does-not-typecheck []
    (add 10 :foo))
