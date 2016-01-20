; Topic: dynamic programming

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task76
  (:use clojure.test)
  (:use tools.core)
  (:use tools.dynprog))

(defn dif-sums-count
  "Given N, returns number of different ways to represent N as sum of at least two
  positive integers"
  [n]
  (sums-count n (rrange n)))

(deftest test1 (is (= (dif-sums-count 5) 6)))

(deftest test2 (is (= (dif-sums-count 100) 190569291)))

(time (run-tests 'euler.volume_2.task76))
