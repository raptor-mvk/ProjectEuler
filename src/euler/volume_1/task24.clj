; Topic: combinatorics

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task24
  (:use clojure.test)
  (:use tools.comb)
  (:use tools.conversions))

(defn nth-perm
  "Given N, returns Nth lexicographical permutation of digits 0 .. 9"
  [n]
  (seq2num (perm-gen (dec n) (range 10))))

(deftest test1 (is (= (nth-perm 1000000) 2783915460)))

(time (run-tests 'euler.volume_1.task24))
