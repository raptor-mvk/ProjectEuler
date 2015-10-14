(ns
  ^{:author raptor_MVK}
  euler.task6
  (:use clojure.test)
  (:use tools.math))

(defn sqr-diff
  "Given N, returns a difference between square of the sum of values from 1 to X and
  sum of the squares of values from 1 to N"
  [n] (let [coll (range 1 (inc n))]
        (- (sqr (reduce + coll)) (reduce + (map sqr coll)))))

(deftest test1 (is (= (sqr-diff 10) 2640)))

(deftest test2 (is (= (sqr-diff 100) 25164150)))

(time (run-tests 'euler.task6))