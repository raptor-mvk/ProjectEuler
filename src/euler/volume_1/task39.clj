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
                                     (count (filter #(apply pythagorean-triplet? %)
                                              (for [a (rrange+ (quot (- n 2) 3))
                                                    b (range+ (inc a) (quot (- n a 1) 2))]
                                                (vector a b (- n a b))))))]
    (first (apply max-key second (map-indexed vector
                                   (pmap pythagorean-triplets-count (range+ n)))))))

(deftest test1 (is (= (max-rigth-triangle-perimeters-count 1000) 840)))

(time (run-tests 'euler.volume_1.task39))

(shutdown-agents)