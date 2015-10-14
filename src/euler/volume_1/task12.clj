; Topic: figurate numbers

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task12
  (:use clojure.test)
  (:use tools.seqs))

(defn divisible-triangle-num
  "Given N, returns the value of the first triangle number to have over N divisors"
  [n]
  (let [tri-seq (map #(reduce +' (range (inc %))) (range))
        prime-factors (filter (complement empty?)
                        (map #(vector % (frequencies (prime-factors-seq %))) tri-seq))
        factors-count (fn [coll] (vector (first coll)
                                   (reduce #(*' %1 (inc (last %2))) 1 (last coll))))]
    (first (first (drop-while #(<= (last %) n) (map factors-count prime-factors))))))

(deftest test1 (is (= (divisible-triangle-num 5) 28)))

(deftest test2 (is (= (divisible-triangle-num 500) 76576500)))

(time (run-tests 'euler.volume_1.task12))