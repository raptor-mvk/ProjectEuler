; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task20
  (:use clojure.test)
  (:use tools.math)
  (:use tools.conversions))

(defn fact-digits-sum
  "Given N, returns sum of digits of the number N!"
  [n]
  (reduce +' (num2seq (fact n))))

(deftest test1 (is (= (fact-digits-sum 10) 27)))

(deftest test2 (is (= (fact-digits-sum 100) 648)))

(time (run-tests 'euler.volume_1.task20))