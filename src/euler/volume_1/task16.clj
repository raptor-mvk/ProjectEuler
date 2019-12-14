; Topic: elementary

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task16
  (:use clojure.test)
  (:use tools.math)
  (:use tools.conversions))

(defn pow-digit-sum
  "Given N, K, returns sum of digits of the number N^K"
  [n k]
  (reduce +' (num2seq (nat-pow n k))))

(deftest test1 (is (= (pow-digit-sum 2 15) 26)))

(deftest test2 (is (= (pow-digit-sum 2 1000) 1366)))

(time (run-tests 'euler.volume_1.task16))