(ns
  ^{:author raptor_MVK}
  euler.volume_1.task7
  (:use clojure.test)
  (:use tools.seqs))

(defn nth-prime
  "Given N, returns Nth prime number"
  [n]
  (nth (prime-seq) (dec n)))

(deftest test1 (is (= (nth-prime 6) 13)))

(deftest test2 (is (= (nth-prime 10001) 104743)))

(time (run-tests 'euler.volume_1.task7))