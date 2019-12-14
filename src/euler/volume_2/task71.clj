; Topic: elementary
; Idea: the answer is max proper fraction from list of i * r values, where i = 2..n
;       (except denominator of r)

(ns
  ^{:author "raptor_MVK"}
  euler.volume_2.task71
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math))

(defn get-left-numerator
  "Given N, R, returns the numerator of the fraction immediately to the left of R in
  the list of reduced proper fractions, for which denominator <= N, sorted in ascending
  order"
  [n r]
  (let [left-fractions (map #(vector % (int (Math/floor (* r %))))
                         (remove #(= % (denominator r)) (range+ 2 n)))
        proper-left-fractions (map #(vector (last %) (/ (last %) (first %)))
                                (filter #(= (gcd (first %) (last %)) 1) left-fractions))]
    (first (apply max-key last proper-left-fractions))))

(deftest test1 (is (= (get-left-numerator 8 3/7) 2)))

(deftest test2 (is (= (get-left-numerator 1000000 3/7) 428570)))

(time (run-tests 'euler.volume_2.task71))
