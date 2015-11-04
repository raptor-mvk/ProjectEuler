; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task37
  (:use clojure.test)
  (:use tools.core)
  (:use tools.factorization)
  (:use tools.conversions))

(defn trunc-primes-sum
  "Returns the sum of the first eleven primes that are both truncatable from left to right
   and right to left"
  []
  (let [transform (fn [x f] (let [xseq (num2seq x)
                                  n (count xseq)]
                              (map #(seq2num (f % xseq)) (rrange n))))
        trunc-nums (fn [x] (concat (transform x take) (transform x drop)))]
    (reduce + (take 11 (filter #(every? prime? (trunc-nums %))
                         (drop-while #(< % 10) (prime-seq)))))))

(deftest test1 (is (= (trunc-primes-sum) 748317)))

(time (run-tests 'euler.volume_1.task37))
