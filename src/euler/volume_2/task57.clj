; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task57
  (:use tools.conversions)
  (:use clojure.test))

(defn longer-numberator-count
  "Given N, returns number of fractions contain a numerator with more digits than
  denominator in the process of expansion the square root of two as an infinite continued
  fraction to N iterations"
  [n]
  (let [expansions (map dec (take n (rest (iterate #(+ 2 (/ 1 %)) 2))))
        numerator-lengths (map #(count (num2seq (numerator %))) expansions)
        denominator-lengths (map #(count (num2seq (denominator %))) expansions)]
    (count (filter true? (map #(> %1 %2) numerator-lengths denominator-lengths)))))

(deftest test1 (is (= (longer-numberator-count 1000) 153)))

(time (run-tests 'euler.volume_2.task57))
