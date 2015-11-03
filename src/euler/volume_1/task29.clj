; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task29
  (:use clojure.test)
  (:use tools.math))

(defn distinct-powers-count
  "Given M, N, returns number of distinct values of a ^ b, where 2 <= a <= M, 2 <= b <= N"
  [m n]
  (count (distinct (for [x (range 2 (inc m))
                         y (range 2 (inc n))]
                     (nat-pow x y)))))

(deftest test1 (is (= (distinct-powers-count 5 5) 15)))

(deftest test2 (is (= (distinct-powers-count 100 100) 9183)))

(time (run-tests 'euler.volume_1.task29))
