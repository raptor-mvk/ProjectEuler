(ns
  ^{:author raptor_MVK}
  euler.full-test
  (:use clojure.test)
  (:use tools.core))

(def solved-tasks-volume1 (rrange+ 34))

(def solved-tasks-volume2 [67])

(time (dorun (map #(require (read-string (str "euler.volume_1.task" %)))
  solved-tasks-volume1)))
