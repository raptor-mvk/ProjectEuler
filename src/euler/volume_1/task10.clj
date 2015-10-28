; Topic: prime numbers

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task10
  (:use clojure.test)
  (:use tools.math))

(defn prime-sum
  "Given N, returns the sum of all primes, which are less than N"
  [n] (reduce + (take-while #(< % n) (prime-seq))))

(deftest test1 (is (= (prime-sum 10) 17)))

(deftest test2 (is (= (prime-sum 2000000) 142913828922)))

(time (run-tests 'euler.volume_1.task10))