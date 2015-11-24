; Topic: number theory

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task35
  (:use clojure.test)
  (:use tools.core)
  (:use tools.factorization)
  (:use tools.conversions))

(defn circ-primes-count
  "Given N, returns number of circular primes less than N"
  [n]
  (let [circ-nums (fn [x] (let [xseq (num2seq x)
                                n (count xseq)]
                            (map #(seq2num (switch % xseq)) (rrange n))))]
    (count (filter #(every? prime? (circ-nums %)) (take-while #(< % n) (prime-seq))))))

(deftest test1 (is (= (circ-primes-count 100) 13)))

(deftest test2 (is (= (circ-primes-count 1000000) 55)))

(time (run-tests 'euler.volume_1.task35))