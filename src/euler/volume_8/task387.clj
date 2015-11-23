; Topic: elementary
; Idea: use probabilistic prime test

(ns
  ^{:author raptor_MVK}
  euler.volume_8.task387
  (:import [java.math BigInteger])
  (:use tools.conversions)
  (:use tools.factorization)
  (:use tools.core)
  (:use clojure.test))

(defn sum-strong-right-trunc-harshad-primes
  "Given N, returns sum of the strong, right truncatable Harshad primes less than 10^N"
  [n]
  (let [harshad? #(= 0 (rem % (reduce + (num2seq %))))
        add-digit (fn [x] (map #(+' (*' x 10) %) (range 10)))
        add-prime-digit (fn [x] (map #(+' (*' x 10) %) [1 3 7 9]))
        trunc-harshad-nums (loop [i 2
                                  cur-nums (rrange 10)
                                  res cur-nums]
                             (if (= i n)
                               res
                               (let [next-nums (filter harshad?
                                                 (flatten (map add-digit cur-nums)))]
                                 (recur (inc i) next-nums (concat res next-nums)))))
        strong-harshad-nums (filter #(prime? (/ % (reduce + (num2seq %))))
                              trunc-harshad-nums)]
    (reduce +' (filter probable-prime?
                 (flatten (map add-prime-digit strong-harshad-nums))))))

(deftest test1 (is (= (sum-strong-right-trunc-harshad-primes 4) 90619)))

(deftest test2 (is (= (sum-strong-right-trunc-harshad-primes 14) 696067597313468)))

(time (run-tests 'euler.volume_8.task387))
