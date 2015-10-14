(ns
  ^{:author raptor_MVK}
  euler.volume_1.task2
  (:use clojure.test)
  (:use tools.seqs))

(defn even-fib-sum
  "Given N, returns the sum of all even-valued fibonnaci numbers, whose values are less
  than N"
  [n]
  (reduce + (filter even? (take-while #(< % n) (fib-seq)))))

(deftest test1 (is (= (even-fib-sum 100) 44)))

(deftest test2 (is (= (even-fib-sum 4000000) 4613732)))

(time (run-tests 'euler.volume_1.task2))