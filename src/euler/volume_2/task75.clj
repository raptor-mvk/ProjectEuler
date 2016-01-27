; Topic: number theory
; Idea: Euclid's formula

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task75
  (:use clojure.test)
  (:use tools.math))

(defn only-pythagorean-triplet-count
  "Given N, returns the number of p <= N such, that if p is the perimeter of an only right
  angle triangle with integral length sides with this perimeter"
  [n]
  (count (filter #(= (val %) 1)
           (frequencies (map #(reduce + %) (get-pythagorean-triplets n))))))

(deftest test1 (is (= (only-pythagorean-triplet-count 1500000) 161667)))

(time (run-tests 'euler.volume_2.task75))
