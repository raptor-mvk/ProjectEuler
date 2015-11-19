; Topic: combinatorics

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task53
  (:use tools.comb)
  (:use tools.core)
  (:use clojure.test))

(defn greater-part-perms-count
  "Given M, K, returns the number values of C[n,k] (not distinct), for 1 <= n <= K,
  which are greater than M"
  [m k]
  (let [greater-part-perms-count (fn [n]
                                   (count (filter #(> % m)
                                            (map #(part-perms-count n %) (range+ n)))))]
    (reduce +' (map greater-part-perms-count (rrange+ k)))))

(deftest test1 (is (= (greater-part-perms-count 1000000 100) 4075)))

(time (run-tests 'euler.volume_2.task53))
