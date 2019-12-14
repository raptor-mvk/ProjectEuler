; Topic: elementary

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task34
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math)
  (:use tools.conversions))

(defn digit-fact-sums
  "Returns sum of all numbers, that can be written as the sum of factorials of
   their digits"
  []
  (let [max-sum (second (keep-indexed #(when (>= %1 (digits-count %2)) %2)
                          (iterate #(+ % (fact 9)) 0)))
        nums (range+ 3 max-sum)
        digit-fact-sum (fn [s] (reduce #(+ %1 (fact %2)) 0 s))]
    (reduce + (filter #(= % (digit-fact-sum (num2seq %))) nums))))

(deftest test1 (is (= (digit-fact-sums) 40730)))

(time (run-tests 'euler.volume_1.task34))