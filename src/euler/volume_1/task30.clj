; Topic: elementary

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task30
  (:use clojure.test)
  (:use tools.core)
  (:use tools.conversions)
  (:use tools.math))

(defn digit-powers-sums
  "Given N, returns sum of all numbers, that can be written as the sum of Nth powers of
   their digits"
  [n]
  (let [max-sum (second (keep-indexed #(when (>= %1 (digits-count %2)) %2)
                          (iterate #(+ % (nat-pow 9 n)) 0)))
        nums (range+ 2 max-sum)
        digit-power-sum (fn [s] (reduce #(+ %1 (nat-pow %2 n)) 0 s))]
    (reduce + (filter #(= % (digit-power-sum (num2seq %))) nums))))

(deftest test1 (is (= (digit-powers-sums 4) 19316)))

(deftest test2 (is (= (digit-powers-sums 5) 443839)))

(time (run-tests 'euler.volume_1.task30))
