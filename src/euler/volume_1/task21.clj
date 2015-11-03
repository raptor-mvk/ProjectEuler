; Topic: amicable numbers

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task21
  (:use clojure.test)
  (:use tools.factorization))

(defn amicable-sum
  "Given N, returns sum of all the amicable numbers under N"
  [n]
  (let [nums (range n)
        factors-sums (map #(reduce + (factors %)) nums)
        amicable-candidates (map #(if (< % n) (nth factors-sums %) nil) factors-sums)]
    (reduce + (filter #(not (nil? %))
                (map #(if (and (= %2 %3) (not (= %1 %3))) %3 nil)
                  factors-sums amicable-candidates nums)))))

(deftest test1 (is (= (amicable-sum 10000) 31626)))

(time (run-tests 'euler.volume_1.task21))