(ns
  ^{:author raptor_MVK}
  euler.full-test
  (:use clojure.test)
  (:use tools.core))

(def solved-tasks-volume1 (rrange+ 49))

(def solved-tasks-volume2 [61 67])

(dorun (map #(require (read-string (str "euler.volume_1.task" %)))
  solved-tasks-volume1))

(dorun (map #(require (read-string (str "euler.volume_2.task" %)))
         solved-tasks-volume2))
