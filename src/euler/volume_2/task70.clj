; Topic: number theory
; Idea: n / phi(n) is minimal for numbers with least distinct prime factors
;       phi(n) = n - 1, where n is prime, so it could not be permutation of n

(ns
  ^{:author "raptor_MVK"}
  euler.volume_2.task70
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math)
  (:use tools.conversions)
  (:use tools.factorization))

(defn min-permut-n-div-totient
  "Given N, Returns value k < 10^N for which phi(k) is a permutation of k and k / phi(k)
  is a minimum, where phi(k) is the Euler's totient function"
  [n]
  (let [half-n (quot n 2)
        possible-prime-factors (take-drop-while #(< % (nat-pow 10 half-n))
                                 #(< % (nat-pow 10 (inc half-n))) (prime-seq))
        nums (filter #(< (first %) (nat-pow 10 n))
               (for [x possible-prime-factors
                     y possible-prime-factors]
                 (vector (* x y) (* (dec x) (dec y)))))
        sort-digits #(sort (num2seq %))]
    (first (apply min-key last
             (map #(vector (first %) (/ (first %) (last %)))
               (filter #(= (sort-digits (first %)) (sort-digits (last %))) nums))))))

(deftest test1 (is (= (min-permut-n-div-totient 7) 8319823)))

(time (run-tests 'euler.volume_2.task70))
