; Topic: dynamic programming

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task77
  (:use clojure.test)
  (:use tools.core)
  (:use tools.factorization)
  (:use tools.dynprog))

(defn min-num-with-prime-sums-count
  "Given N, returns the first number which can be written as the sum of primes in over
  N different ways"
  [n]
  (first (first (drop-while #(< (last %) n) (map #(vector % (sums-count % (prime-seq)))
                                              (drop 2 (range)))))))

(deftest test1 (is (= (min-num-with-prime-sums-count 5) 10)))

(deftest test2 (is (= (min-num-with-prime-sums-count 5000) 71)))

(time (run-tests 'euler.volume_2.task77))
