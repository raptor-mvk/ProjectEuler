(ns
  ^{:author raptor_MVK}
  euler.full-test
  (:use clojure.test)
  (:use tools.core))

(def solved-tasks-volume1 (rrange+ 50))

(def solved-tasks-volume2 (concat (range+ 51 61) [67]))

(def solved-tasks-volume8 [387])

(def solved-tasks-volume10 [493])

(dorun (map #(require (read-string (str "euler.volume_1.task" %)))
  solved-tasks-volume1))

(dorun (map #(require (read-string (str "euler.volume_2.task" %)))
         solved-tasks-volume2))
