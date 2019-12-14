; Topic: elementary

(ns
  ^{:author "raptor_MVK"}
  euler.volume_2.task73
  (:use clojure.test)
  (:use tools.math)
  (:use tools.core))

(defn fractions-count
  "Given N, Q, R, returns the number of the fractions between Q and R in the sorted
  list of reduced proper fractions, for which denominator <= N"
  [n q r]
  (let [numerator-ranges (map #(vector (range+ (int (Math/ceil (* q %)))
                                         (int (Math/floor (* r %)))) %) (range+ 2 n))
        make-fraction (fn [v] (filter #(= (gcd (first %) (last %)) 1)
                                (map #(vector % (last v)) (first v))))]
    (- (count (apply concat (map make-fraction numerator-ranges))) 2)))

(deftest test1 (is (= (fractions-count 8 1/3 1/2) 3)))

(deftest test2 (is (= (fractions-count 12000 1/3 1/2) 7295372)))

(time (run-tests 'euler.volume_2.task73))
