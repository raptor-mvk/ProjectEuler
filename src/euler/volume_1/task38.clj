; Topic: elementary
; Idea: maximal integer, that can form 9-digit concatenated product with n > 1, is 9999

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task38
  (:use clojure.test)
  (:use tools.core)
  (:use tools.conversions))

(defn max-pandigital-multiple
  "Returns the largest 1 to 9 pandigital 9-digit number that can be formed as the
  concatenated product of an integer with (1,2, ... , n)"
  []
  (let [concat-prod (fn [x] (reduce #(if (>= (count %1) 9)
                                       (reduced %1)
                                       (concat %1 (num2seq %2)))
                              "" (map #(* % x) (range))))]
    (apply max (map #(seq2num %)
                 (filter #(and (nil? (some #{0} %)) (= 9 (count %) (count (distinct %))))
                   (map concat-prod (rrange 10000)))))))

(deftest test1 (is (= (max-pandigital-multiple) 932718654)))

(time (run-tests 'euler.volume_1.task38))