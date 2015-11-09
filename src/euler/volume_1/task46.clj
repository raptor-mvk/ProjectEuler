; Topic: elementary
; Idea: use sieve technique, storing for each sum all pairs [prime, n],
;       which produce this sum = prime + 2 * n ^ 2

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task46
  (:use clojure.test)
  (:use tools.core)
  (:use tools.factorization)
  (:use tools.math))

(defn min-goldbach-counterexample
  "Returns the smallest odd composite that cannot be written as the sum of a prime and
  twice a square"
  []
  (let [upd (fn [sieve prime n]
              (update-in sieve [(+ prime (* 2 (sqr n)))] conj [prime n]))]
    (loop [sieve {}
           n 3]
      (if-let [cur (get sieve n)]
        (recur (reduce #(upd %1 (first %2) (inc (last %2))) (dissoc sieve n) cur)
          (if (prime? n) n (+ n 2)))
        (if (prime? n)
          (recur (upd sieve n 1) (+ n 2))
          n)))))

(deftest test1 (is (= (min-goldbach-counterexample) 5777)))

(time (run-tests 'euler.volume_1.task46))
