; Topic: number theory
; Idea: Euclid's formula

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task39
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math)
  (:use tools.conversions))

(defn max-rigth-triangle-perimeters-count
  "Given N, returns p <= N such, that if p is the perimeter of a right angle triangle
  with integral length sides, the number of solutions is maximized"
  [n]
  (max-map-key (frequencies (map #(reduce + %) (get-pythagorean-triplets n)))))

(deftest test1 (is (= (max-rigth-triangle-perimeters-count 1000) 840)))

(time (run-tests 'euler.volume_1.task39))
