; Topic: Collatz sequence

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task14
  (:use clojure.test)
  (:use tools.seqs))

(defn max-collatz-seq
  "Given N, returns starting number, under N, produces the longest Collatz sequence"
  [n]
  (first (reduce #(if (> (last %2) (last %1)) %2 %1)
           (map #(vector % (count (collatz-seq %))) (range 2 (inc n))))))

(deftest test2 (is (= (max-collatz-seq 1000000) 837799)))

(time (run-tests 'euler.volume_1.task14))