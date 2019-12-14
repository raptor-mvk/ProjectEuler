; Topic: elementary

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task25
  (:use clojure.test)
  (:use tools.seqs)
  (:use tools.math))

(defn first-fib-len
  "Given N, returns index of the first term in the Fibonacci sequence to contain N digits"
  [n]
  (let [limit (nat-pow 10 (dec n))]
    (inc (count (take-while #(< % limit) (fib-seq))))))

(deftest test1 (is (= (first-fib-len 3) 12)))

(deftest test2 (is (= (first-fib-len 1000) 4782)))

(time (run-tests 'euler.volume_1.task25))
