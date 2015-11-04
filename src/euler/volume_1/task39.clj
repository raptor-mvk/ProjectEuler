; Topic: elementary

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task39
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math)
  (:use tools.conversions))

(defn max-rigth-triangle-perimeters-count
  "Given N, returns p <= N such, that if p is the perimeter of a right angle triangle
  with integral length sides, the number of solutions is maximized"
  [n]
  (let [pythagorean-triplets-count (fn [n]
                                     (count (filter #(and (apply pythagorean-triplet? %)
                                                       (< (first %) (second %))
                                                       (< (second %) (last %)))
                                              (for [a (range 1 (quot (+ n 2) 3))
                                                    b (range (inc a) (quot (inc n) 2))]
                                                (vector a b (- n a b))))))]
    (max-key pythagorean-triplets-count (rrange+ n))))

(deftest test1 (is (= (max-rigth-triangle-perimeters-count 100) 840)))

(time (run-tests 'euler.volume_1.task39))
