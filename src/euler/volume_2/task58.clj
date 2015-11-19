; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task58
  (:use tools.math)
  (:use tools.factorization)
  (:use clojure.test))

(defn min-spiral-prime-ratio
  "Given X, returns the minimal size of the spiral formed starting with the number 1 and
  moving to the right in an anticlockwise direction such, that a ratio of prime numbers
  lying along both diagonals to all numbers on these diagonals is less than X"
  [x]
  (loop [cur 3
         primes-count 0]
    (let [new-primes-count (+ primes-count
                             (count (filter prime?
                                      (take 3 (rest (iterate #(- % (dec cur))
                                                      (sqr cur)))))))]
      (if (< (/ new-primes-count (dec (* cur 2))) x)
        cur
        (recur (+ cur 2) new-primes-count)))))

(deftest test1 (is (= (min-spiral-prime-ratio 0.1) 26241)))

(time (run-tests 'euler.volume_2.task58))
