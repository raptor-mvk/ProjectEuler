(ns
  ^{:author raptor_MVK}
  euler.task3
  (:use clojure.test)
  (:use tools.seqs))

(defn max-prime-factor
  "Given N, returns largest prime factor of N"
  [n] (last (prime-factors-seq n)))

(deftest test1 (is (= (max-prime-factor 13195) 29)))

(deftest test2 (is (= (max-prime-factor 600851475143) 6857)))

(time (run-tests 'euler.task3))