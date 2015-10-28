; Topic: prime numbers

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task3
  (:use clojure.test)
  (:use tools.math))

(defn max-prime-factor
  "Given N, returns largest prime factor of N"
  [n]
  (last (prime-factors n)))

(deftest test1 (is (= (max-prime-factor 13195) 29)))

(deftest test2 (is (= (max-prime-factor 600851475143) 6857)))

(time (run-tests 'euler.volume_1.task3))