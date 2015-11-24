; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task65
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math)
  (:use tools.conversions))

(defn sum-digits-numerator
  "Given N, returns the sum of digits in the numerator of the Nth convergent of the
  continued fraction for e"
  [n]
  (let [e-frac (concat [2] (flatten (map #(vector 1 (* 2 %) 1) (rrange))))]
    (reduce + (num2seq (numerator (cont-frac n e-frac))))))

(deftest test1 (is (= (sum-digits-numerator 100) 272)))

(time (run-tests 'euler.volume_2.task65))
