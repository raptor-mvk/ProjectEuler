; Topic: combinatorics
; Idea: the answer could not contain more, than 7 digits, because 1 + ... + 8 = 36 and so
;       it is divisible by 3

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task41
  (:use clojure.test)
  (:use tools.comb)
  (:use tools.factorization)
  (:use tools.conversions))

(defn max-pandigital-prime
  "Returns largest pandigital prime"
  []
  (some #(and (prime? %) %) (map #(seq2num (conj % 7)) (all-perms [6 5 4 3 2 1]))))

(deftest test1 (is (= (max-pandigital-prime) 7652413)))

(time (run-tests 'euler.volume_1.task41))
