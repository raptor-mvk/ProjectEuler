; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task56
  (:use tools.core)
  (:use tools.math)
  (:use tools.conversions)
  (:use clojure.test))

(defn max-power-digit-sum
  "Given N, K, returns maximal digtal sum of a^b, where a < N, b < K"
  [n k]
  (let [powers (fn [n] (map #(nat-pow n %) (rrange k)))
        nums (map powers (range 2 n))]
    (apply max (map #(reduce + (num2seq %)) (flatten nums)))))

(deftest test1 (is (= (max-power-digit-sum 100 100) 972)))

(time (run-tests 'euler.volume_2.task56))
