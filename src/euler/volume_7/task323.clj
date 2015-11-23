; Topic: probability theory
; Idea: The probability of at least one 1 in m experiments is
;       (1 - 1 / 2 ^ m), so the probability of N = i is
;       (1 - 1 / 2 ^ i) ^ n - (1 - 1 / 2 ^ (i - 1)) ^ n, and
;       finally the answer is
;       sum (i = 2 .. infty : ((1 - 1 / 2 ^ i) ^ n -
;                              (1 - 1 / 2 ^ (i - 1)) ^ n) * i)

(ns
  ^{:author raptor_MVK}
  euler.volume_7.task323
  (:use tools.math)
  (:use clojure.test))

(defn expected-max-distinct-bits
  "Given N, returns the expected value of M such, that x(i)=2^N-1 for all i>=M, where
  x(0)=0, x(i)=x(i-1) | y(i-1) and y(0),y(1)... is a sequence of uniformly distributed
  random unsigned N bit integers, rounded to 10 digits after the decimal point"
  [n]
  (let [m-zeroes-prob (fn [m] (Math/pow (- 1 (/ 1 (nat-pow 2 m))) n))]
    (loop [i 2
           res (m-zeroes-prob 1)]
      (let [new-res (* (- (m-zeroes-prob i) (m-zeroes-prob (dec i))) i)]
        (if (< new-res 1e-12)
          (round-to-fixed res 10)
          (recur (inc i) (+ res new-res)))))))

(deftest test1 (is (= (expected-max-distinct-bits 32) 6.3551758451)))

(time (run-tests 'euler.volume_7.task323))
