; Topic: number theory

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task45
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math))

(defn triangle-pentagonal-hexagonal
  "Given N, returns N-th triangle number that is also pentagonal and hexagonal"
  [n]
  (let [hexagonal-nums (map #(* % (dec (* 2 %))) (rrange))]
    (nth (filter #(and (pentagonal? %) (triangle? %)) hexagonal-nums) n)))

(deftest test1 (is (= (triangle-pentagonal-hexagonal 1) 40755)))

(deftest test2 (is (= (triangle-pentagonal-hexagonal 2) 1533776805)))

(time (run-tests 'euler.volume_1.task45))
