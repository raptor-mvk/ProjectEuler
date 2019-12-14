; Topic: elementary

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task36
  (:use clojure.test)
  (:use tools.core)
  (:use tools.conversions))

(defn double-base-palindrome-sum
  "Given N, returns sum of all numbers, less than N, which are palindromic in base 10
  and base 2"
  [n]
  (let [palindrome? (fn [s] (= s (reverse s)))]
    (reduce + (filter #(every? palindrome? [(num2seq % 2) (num2seq % 10)]) (rrange n)))))

(deftest test1 (is (= (double-base-palindrome-sum 1000000) 872187)))

(time (run-tests 'euler.volume_1.task36))
