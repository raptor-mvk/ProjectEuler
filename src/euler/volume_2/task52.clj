; Topic: elementary
; Idea: the answer has at least 6 digits

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task52
  (:use tools.core)
  (:use tools.math)
  (:use tools.conversions)
  (:use clojure.test))

(defn min-permuted-6-multiple
  "Returns the smallest positive integer, x, such that 2 * x, 3 * x, 4 * x, 5 * x, and
  6 * x, contain the same digits"
  []
  (let [start-n 5
        make-nums (fn [n] (range (nat-pow 10 n) (nat-pow 10 (inc n))))
        make-multiples (fn [n coll] (map #(* n %) coll))
        same-digits? (fn [coll] (apply = (map #(sort (num2seq %)) coll)))]
    (loop [n start-n
           nums (make-nums start-n)]
      (let [multiples (map #(make-multiples % (rrange+ 6)) nums)
            filtered-multiples (filter same-digits? multiples)]
        (if (empty? filtered-multiples)
          (recur (inc n) (make-nums (inc n)))
          (apply min (map first filtered-multiples)))))))

(deftest test1 (is (= (min-permuted-6-multiple) 142857)))

(time (run-tests 'euler.volume_2.task52))
