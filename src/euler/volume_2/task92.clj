; Topic: dynamic programming
; Idea: define A[i, N] = the number of numbers less than 10^N with sum of digits equal to
;       i, then A[i, N + 1] = sum (A[i - k ^ 2; N]; k = 0 .. 9);
;       B[i] = 0, if i becomes 1, and 1 if i becomes 89;
;       the answer is sum (A[i, N] * B[i]; i = 2 .. 9 ^ 2 * N)

(ns
  ^{:author raptor_MVK}
  euler.volume_2.task92
  (:use clojure.test)
  (:use tools.math)
  (:use tools.conversions)
  (:use tools.core))

(defn digit-square-sum-chain-89
  "Given N, returns the number of starting numbers below 10^N, which will arrive at 89
  while continuously adding the square of the digits in a number to form a new number
  until it has been seen before."
  [n]
  (let [digit-squares (map sqr (rrange 10))
        digit-square-sum (fn [n] (reduce + (map #(* % %) (num2seq n))))
        chain-89? (fn [n] (reduce #(if (= %2 1)
                                     (reduced 0)
                                     (if (= %2 89)
                                       (reduced 1)
                                       %1)) n (iterate digit-square-sum n)))
        sum-res (fn [x y] (map #(+ %1 %2) x y))
        sums-count (* (sqr 9) n)
        digit-square-sums-count (drop 2
                                  (loop [i 0
                                         res (concat [1] (repeat sums-count 0))]
                                    (if (= i n)
                                      res
                                      (recur (inc i)
                                        (reduce #(sum-res %1 %2) res
                                          (map #(zero-shift % res) digit-squares))))))]
    (reduce + (map #(* %1 %2) (map chain-89? (drop 2 (range+ sums-count)))
                digit-square-sums-count))))

(deftest test1 (is (= (digit-square-sum-chain-89 7) 8581146)))

(time (run-tests 'euler.volume_2.task92))
