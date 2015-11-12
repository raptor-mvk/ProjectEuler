; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task47
  (:use clojure.test)
  (:use tools.factorization))

(defn first-distinct-prime-factors-seq
  "Given N, returns first of N first consecutive integers to have N distinct prime
  factors"
  [n]
  (let [prime-factors-count (pmap #(vector % (count (distinct (prime-factors %))))
                              (drop 2 (range)))
        n-prime-factors-nums (filter #(= n (last %)) prime-factors-count)
        differences (pmap #(vector (first %1) (- (first %2) (first %1)))
                      n-prime-factors-nums (drop (dec n) n-prime-factors-nums))]
    (some #(and (= (dec n) (last %)) (first %)) differences)))

(deftest test1 (is (= (first-distinct-prime-factors-seq 2) 14)))

(deftest test2 (is (= (first-distinct-prime-factors-seq 3) 644)))

(deftest test3 (is (= (first-distinct-prime-factors-seq 4) 134043)))

(time (run-tests 'euler.volume_1.task47))

(shutdown-agents)