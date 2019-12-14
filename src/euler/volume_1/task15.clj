; Topic: combinatorics
; Idea: answer is number of partial permutations of n down moves from 2 * n moves

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task15
  (:use clojure.test)
  (:use tools.comb))

(defn paths-count
  "Given N, K, returns number of routes on a NxK grid, starting in the top left corner,
  and only being able to move to the right and down"
  [n k]
  (part-perms-count (+ n k) n))

(deftest test1 (is (= (paths-count 2 2) 6)))

(deftest test2 (is (= (paths-count 20 20) 137846528820)))

(time (run-tests 'euler.volume_1.task15))