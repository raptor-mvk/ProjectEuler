; Topic: number theory
; Idea: n / phi(n) is maximal for the number with most distinct prime factors

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task69
  (:use clojure.test)
  (:use tools.factorization))

(defn max-n-div-totient
  "Given N, returns value k <= N for which k / phi(k) is a maximum, where phi(k) is the
   Euler's totient function"
  [n]
  (last (take-while #(<= % n) (reductions * (prime-seq)))))

(deftest test1 (is (= (max-n-div-totient 10) 6)))

(deftest test2 (is (= (max-n-div-totient 1000000) 510510)))

(time (run-tests 'euler.volume_2.task69))
