; Topic: elementary

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task6
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math))

(defn sqr-diff
  "Given N, returns a difference between square of the sum of values from 1 to X and
  sum of the squares of values from 1 to N"
  [n]
  (let [coll (rrange (inc n))]
    (- (sqr (reduce + coll)) (reduce + (map sqr coll)))))

(deftest test1 (is (= (sqr-diff 10) 2640)))

(deftest test2 (is (= (sqr-diff 100) 25164150)))

(time (run-tests 'euler.volume_1.task6))