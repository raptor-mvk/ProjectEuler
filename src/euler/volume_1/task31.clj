; Topic: combinatorics

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task31
  (:use clojure.test)
  (:use tools.comb))

(defn coin-sums-count
  "Given N, returns the number of different ways can N pences be made using coins 1p, 2p,
  5p, 10p, 20p, 50p, 100p and 200p"
  [n]
  (parts-by-coll-count n [1 2 5 10 20 50 100 200]))

(deftest test1 (is (= (coin-sums-count 200) 73682)))

(time (run-tests 'euler.volume_1.task31))
