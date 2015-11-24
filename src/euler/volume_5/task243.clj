; Topic: number theory
; Idea: Euler's totient function

(ns
  ^{:author raptor_MVK}
  euler.volume_5.task243
  (:use clojure.test)
  (:use tools.factorization))

(defn min-denominator
  "Given R, returns the smallest denominator, for which ratio of proper
  fractions with this denominator, that cannot be cancelled down, less, than R"
  [r]
  (let [ratio (fn [n] (/ (totient n) (dec n)))
        first-approx (loop [n 6
                            cur 3
                            primes (drop 2 (prime-seq))]
                       (if (< (ratio n) r)
                         (quot n cur)
                         (recur (*' n (first primes)) (first primes) (rest primes))))]
    (loop [n 2
           cur (*' first-approx n)]
      (if (< (ratio cur) r)
        cur
        (recur (inc n) (*' first-approx (inc n)))))))

(deftest test1 (is (= (min-denominator 15499/94744) 892371480)))

(time (run-tests 'euler.volume_5.task243))
