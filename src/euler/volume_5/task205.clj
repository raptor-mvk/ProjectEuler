; Topic: probability theory

(ns
  ^{:author raptor_MVK}
  euler.volume_5.task205
  (:use clojure.test)
  (:use tools.math)
  (:use tools.conversions))

(defn first-win-prob
  "Given A, N, B, M, return probability of getting more points rolling A N-sided dices
  over B M-sided dices, rounded to 7 decimal places"
  [a n b m]
  (let [probs (fn [a n]
                (let [total (nat-pow n a)]
                  (map #(vector (+ (first %) a) (/ (count (last %)) total))
                    (group-by #(reduce + %) (map #(num2seq % n a) (range total))))))
        first-probs (probs a n)
        second-probs (probs b m)
        first-win (fn [value prob]
                    (* (reduce #(+ %1 (last %2)) 0 (filter #(< (first %) value)
                                                     second-probs)) prob))]
    (round-to-fixed (reduce + (map #(first-win (first %) (last %)) first-probs)) 7)))

(deftest test1 (is (= (first-win-prob 9 4 6 6) 0.5731441)))

(time (run-tests 'euler.volume_5.task205))
