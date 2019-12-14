; Topic: number theory
; Idea: for each prime calculate sums of consecutive primes less than N,
;       while prime * length of the current longest sequence < N

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task50
  (:use clojure.test)
  (:use tools.core)
  (:use tools.factorization))

(defn max-conseq-primes-sum-prime
  "Given N, returns prime below N, that can be written as the sum of the most consecutive
  primes"
  [n]
  (let [primes-sum (fn [coll k] (map #(vector %2 %1)
                                  (take-while #(< % n) (drop k (reductions + coll)))
                                  (drop k (rrange))))]
    (loop [coll (prime-seq)
           answer [2 5]]
      (if (> (first coll) (quot n (first answer)))
        (last answer)
        (recur (rest coll) (apply max-key last
                             (concat [answer] (filter #(prime? (last %))
                                                (primes-sum coll (first answer))))))))))

(deftest test1 (is (= (max-conseq-primes-sum-prime 100) 41)))

(deftest test2 (is (= (max-conseq-primes-sum-prime 1000) 953)))

(deftest test3 (is (= (max-conseq-primes-sum-prime 1000000) 997651)))

(time (run-tests 'euler.volume_1.task50))