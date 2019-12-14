; Topic: elementary

(ns
  ^{:author "raptor_MVK"}
  euler.volume_2.task97
  (:use clojure.test)
  (:use tools.math))

(defn prime-tail
  "Returns last 10 digits of non-Mersenne prime number 28433*2^7830457+1"
  []
  (let [divider (nat-pow 10 10)]
    (rem (inc (* 28433 (nat-pow-mod 2 7830457 divider))) divider)))

(deftest test1 (is (= (prime-tail) 8739992577)))

(time (run-tests 'euler.volume_2.task97))
