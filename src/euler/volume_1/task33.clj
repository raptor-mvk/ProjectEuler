; Topic: elementary
; Idea: possible combinations are 1-digit * 4-digit = 4-digit and
;       2-digit * 3-digit = 4-digit

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task33
  (:use tools.core)
  (:use clojure.test)
  (:use tools.math))

(defn digit-cancelling-fractions
  "Returns denominator of the product of four non-trivial curiously 'cancellable'
  fractions (in its lowest common term), less than one in value, and containing two
  digits in the numerator and denominator"
  []
  (let [calc-frac (fn [num1 num2 denom1 denom2]
                    (/ (+ (* 10 num1) num2) (+ (* 10 denom1) denom2)))
        digits (rrange 10)
        test (fn [[num denom]]
               (let [frac-val (/ num denom)]
                 ((complement empty?) (filter #(= frac-val %)
                                        (concat (map #(calc-frac % num denom %) digits)
                                          (map #(calc-frac num % % denom) digits))))))
        fracs (for [x digits
                    y (drop-while #(<= % x) digits)]
                [x y])]
    (denominator (reduce #(* %1 (/ (first %2) (last %2))) 1 (filter test fracs)))))

(deftest test1 (is (= (digit-cancelling-fractions) 100)))

(time (run-tests 'euler.volume_1.task33))