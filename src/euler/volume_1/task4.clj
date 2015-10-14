; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task4
  (:use clojure.test)
  (:use tools.conversions))

(defn max-palindrome
  "Given M and N, returns largest palindrome made from product of two numbers from
  interval [M, N)"
  [m n]
  (apply max (filter is-palindrome?
               (for [a (range m n)
                     b (range a n)]
                 (* a b)))))

(deftest test1 (is (= (max-palindrome 10 100) 9009)))

(deftest test2 (is (= (max-palindrome 100 1000) 906609)))

(time (run-tests 'euler.volume_1.task4))