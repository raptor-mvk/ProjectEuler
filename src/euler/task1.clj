(ns
  ^{:author raptor_MVK}
  euler.task1
  (:use clojure.test)
  (:use tools.math))

(defn sum-mul
  "Given N, coll, returns the sum of all the multiples of any element of coll below N"
  [n coll]
  (reduce + (filter #(or-divides? % coll) (range 1 n))))

(deftest test1 (is (= (sum-mul 10 [3 5]) 23)))

(deftest test2 (is (= (sum-mul 1000 [3 5]) 233168)))

(time (run-tests 'euler.task1))