; Topic: elementary
; Idea: For obtaining 1000000-th digit, it is enough to take 185200 integers

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task40
  (:use clojure.test))

(defn champernowne-prod
  "Returns, d1 * d10 * d100 * d1000 * d10000 * d100000 * d1000000, where di - ith digit
   of an irrational decimal fraction is created by concatenating the positive integers"
  []
  (let [champernowne (apply str (range 185200))
        indices (take 7 (iterate #(* % 10) 1))]
    (reduce #(* %1 (- (int %2) (int \0))) 1 (map #(nth champernowne %) indices))))

(deftest test1 (is (= (champernowne-prod) 210)))

(time (run-tests 'euler.volume_1.task40))
